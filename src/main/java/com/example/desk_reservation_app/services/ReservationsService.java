package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
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

    public ReservationsService(ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository, RoomRepository roomRepository, FloorRepository floorRepository, BuildingRepository buildingRepository, JwtUtil jwtUtil) {
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
        this.buildingRepository = buildingRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<ReservationsDto> getUserReservationByDate(LocalDate date, String  auth) {
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
                .forEach(reservation -> this.cancelReservation(reservation.getId()));
//        reservationsToCancel.forEach(reservation -> {
//            reservation.setReservationStatus(ReservationStatus.CANCELED);
//            reservationsRepository.save(reservation);
//        });
        Desk desk = deskRepository.getById(id);
        desk.setDeskDeleted(true);
        deskRepository.save(desk);
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.getById(id);
        List<Long> idsToDelete = new java.util.ArrayList<>(List.of());
        room.getDesks().forEach(desk -> idsToDelete.add(desk.getId()));
        idsToDelete.forEach(this::deleteDeskById);
        room.setRoomDeleted(true);
        roomRepository.save(room);
    }

    public void deleteFloorById(Long floorId) {
        Floor floor = floorRepository.getById(floorId);
        List<Long> idsToDelete = new java.util.ArrayList<>(List.of());
        floor.getRooms().forEach(room -> idsToDelete.add(room.getId()));
        idsToDelete.forEach(this::deleteRoom);
        floor.setFloorDeleted(true);
        floorRepository.save(floor);
    }

    public void deleteBuildingById(Long buildingId) {
        Building building = buildingRepository.getById(buildingId);
        List<Long> idsToDelete = new java.util.ArrayList<>(List.of());
        building.getFloors().forEach(floor -> idsToDelete.add(floor.getId()));
        idsToDelete.forEach(this::deleteFloorById);
        building.setBuildingDeleted(true);
        buildingRepository.save(building);
    }


}
