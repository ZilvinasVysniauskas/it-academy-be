package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.api.places.DeskDto;
import com.example.desk_reservation_app.dto.api.places.RoomDto;
import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.models.*;
import com.example.desk_reservation_app.models.enums.ReservationStatus;
import com.example.desk_reservation_app.repositories.*;
import com.example.desk_reservation_app.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;
    private final DeskRepository deskRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final FloorRepository floorRepository;
    private final BuildingRepository buildingRepository;
    private final JwtUtil jwtUtil;
    private final NotificationsRepository notificationsRepository;

    public ReservationsService(ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository, RoomRepository roomRepository, FloorRepository floorRepository, BuildingRepository buildingRepository, JwtUtil jwtUtil, NotificationsRepository notificationsRepository) {
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.buildingRepository = buildingRepository;
        this.jwtUtil = jwtUtil;
        this.notificationsRepository = notificationsRepository;
    }

    public ResponseEntity<ReservationsDto> getUserReservationByDate(LocalDate date, String auth) {
        Optional<Reservation> optionalReservations = this.reservationsRepository.findReservationsByDateAndUserUserIdAndReservationStatusIsNull(date, this.jwtUtil.getSubject(auth));
        return optionalReservations.map(reservation -> new ResponseEntity<>(ReservationsMapper.ReservationToReservationDto(reservation), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    public List<ReservationsDto> getAllReservationsByUserId(String auth) {
        List<Reservation> allUserReservations = this.reservationsRepository.findReservationsByUserUserId(this.jwtUtil.getSubject(auth));
        allUserReservations.sort(Comparator.comparing(Reservation::getDate));
        allUserReservations.forEach(reservation -> {
            if (reservation.getReservationStatus() == null) {
                reservation.setReservationStatus(ReservationStatus.ACTIVE);
            }
            if (reservation.getDate().isBefore(LocalDate.now())) {
                reservation.setReservationStatus(ReservationStatus.EXPIRED);
            }
        });
        return allUserReservations.stream()
                .map(ReservationsMapper::ReservationToReservationDto)
                .collect(Collectors.toList());
    }

    public void placeReservation(ReservationRequest reservationRequest, String auth) {
        Desk desk = this.deskRepository.getById(reservationRequest.getDeskId());
        User user = this.userRepository.getById(this.jwtUtil.getSubject(auth));
        this.reservationsRepository.save(ReservationsMapper.reservationRequestToReservation(reservationRequest, desk, user));
    }

    public void cancelReservation(Long id) {
        Reservation reservation = this.reservationsRepository.findById(id).get();
        reservation.setReservationStatus(ReservationStatus.CANCELED);
        this.reservationsRepository.save(reservation);
    }

    public void deleteDeskById(Long id) {
        LocalDate date = LocalDate.now();
        this.reservationsRepository.findReservationsByDeskIdAndDateGreaterThanEqual(id, date)
                .forEach(reservation -> {
                    this.cancelReservation(reservation.getId());
                    this.notificationsRepository.save(formNotification(reservation));
                });
        Desk desk = deskRepository.getById(id);
        desk.setDeskDeleted(true);
        deskRepository.save(desk);
    }

    private String cancelReservationMessage(Reservation reservation) {
        return "your reservation on desk " + reservation.getDesk().getDeskName() + " in room "
                + reservation.getDesk().getRoom().getRoomName() + " on " + reservation.getDate()
                + " has been canceled, what you ganna do about it?";
    }
    private Notification formNotification(Reservation reservation){
        return Notification.builder()
                .date(LocalDate.now())
                .message(cancelReservationMessage(reservation))
                .userId(reservation.getUser().getUserId())
                .build();
    }

    public void deleteRoom(Long id) {
        //TODO išsiaiškink kam reikia idsToDelete
        Room room = roomRepository.getById(id);
        List<Long> idsToDelete = new java.util.ArrayList<>(List.of());
        room.getDesks().forEach(desk -> idsToDelete.add(desk.getId()));
        idsToDelete.forEach(this::deleteDeskById);
        room.setRoomDeleted(true);
        roomRepository.save(room);
    }

    public void deleteFloorById(Long floorId, Long replaceFloorId) {
        Floor floor = floorRepository.getById(floorId);
        changeDefaultFloorsForUsers(floorId, replaceFloorId);
        List<Long> idsToDelete = new java.util.ArrayList<>(List.of());
        floor.getRooms().forEach(room -> idsToDelete.add(room.getId()));
        idsToDelete.forEach(this::deleteRoom);
        floor.setFloorDeleted(true);
        floorRepository.save(floor);
    }

    private void changeDefaultFloorsForUsers(Long floorId, Long replaceFloorId) {
        List<User> userList = this.userRepository.findAllByDefaultFloorId(floorId);
        userList.forEach(user -> user.setDefaultFloorId(replaceFloorId));
        this.userRepository.saveAll(userList);
    }

    public List<RoomDto> getAllDesksWithReservationsByDate(Long floorId, LocalDate date) {
        return placeReservations(date, getAllRoomsByFloor(floorId));
    }

    public List<RoomDto> getAllRoomsByFloor(Long floorId) {
        List<Room> rooms = roomRepository.findAllByFloorIdAndRoomDeletedFalse(floorId);
        List<RoomDto> roomDtoList = rooms.stream()
                .map(ReservationsMapper::RoomToRoomDto).toList();
        addDesksToRooms(roomDtoList);
        return roomDtoList;
    }

    public void addDesksToRooms(List<RoomDto> roomDtoList) {
        roomDtoList.forEach(a-> a.setDesks(
                deskRepository.findDeskByRoomIdAndDeskDeletedFalse(a.getRoomId()).stream()
                        .map(ReservationsMapper::DeskToDeskToDto)
                        .collect(Collectors.toList())
        ));
    }

    private List<RoomDto> placeReservations(LocalDate date, List<RoomDto> roomDtoList) {
        roomDtoList.forEach(roomDto -> {
                    List<DeskDto> deskDtoList = new java.util.ArrayList<>(List.of());
                    List<DeskDto> desksOfRoom = this.deskRepository.findDeskByRoomIdAndDeskDeletedFalse(roomDto.getRoomId())
                            .stream().map(ReservationsMapper::DeskToDeskToDto).toList();
                    desksOfRoom.forEach(desk -> {
                        this.reservationsRepository.findReservationsByDateAndDeskIdAndReservationStatusIsNull(date, desk.getId())
                                .ifPresent(reservation -> desk.reserveTable(reservation.getUser().getFirstName(), reservation.getUser().getLastName()));
                        deskDtoList.add(desk);
                    });
                    roomDto.setDesks(deskDtoList);
                }
        );
        return roomDtoList;
    }

}
