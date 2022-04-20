package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.dto.requests.DeskRequest;
import com.example.desk_reservation_app.dto.requests.RoomRequest;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Floor;
import com.example.desk_reservation_app.models.Reservation;
import com.example.desk_reservation_app.models.Room;
import com.example.desk_reservation_app.models.enums.ReservationStatus;
import com.example.desk_reservation_app.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DesksService {

    private final FloorRepository floorRepository;
    private final RoomRepository roomRepository;
    private final ReservationsRepository reservationsRepository;
    private final DeskRepository deskRepository;

    public DesksService(FloorRepository floorRepository, ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.floorRepository = floorRepository;
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
        this.roomRepository = roomRepository;
    }

    public List<RoomDto> getAllDesksWithReservationsByDate(LocalDate date) {
        return placeReservations(date, getAllRoomsByFloor());
    }

    public List<RoomDto> getAllRoomsByFloor() {
        List<Room> rooms = roomRepository.findAllByFloorIdAndRoomDeletedFalse(1L);
        List<RoomDto> roomDtoList = rooms.stream()
                .map(ReservationsMapper::RoomToRoomDto).collect(Collectors.toList());
        roomDtoList.forEach(a-> a.setDesks(
                deskRepository.findDeskByRoomIdAndDeskDeletedFalse(a.getRoomId()).stream()
                        .map(ReservationsMapper::DeskToDeskToDto)
                        .collect(Collectors.toList())
        ));
        return roomDtoList;
    }

    private List<RoomDto> placeReservations(LocalDate date, List<RoomDto> roomDtoList) {
        List<Reservation> reservations = reservationsRepository.findReservationsByDateAndReservationStatusIsNull(date);
        reservations.forEach(res -> {
            int roomId = Math.toIntExact(res.getDesk().getRoom().getId());
            int deskId = Math.toIntExact(res.getDesk().getId());
            roomDtoList.stream()
                    .filter(a -> a.getRoomId() == roomId)
                    .findFirst().get().getDesks().stream()
                    .filter(d -> d.getId() == deskId)
                    .findFirst().get().reserveTable(res.getUser().getFirstName(), res.getUser().getLastName());
        });
        return roomDtoList;
    }

    public void deleteDeskById(Long id) {
        LocalDate date = LocalDate.now();
        List<Reservation> reservationsToCancel = this.reservationsRepository.findReservationsByDeskIdAndDateGreaterThanEqual(id, date);
        reservationsToCancel.forEach(reservation -> {
            reservation.setReservationStatus(ReservationStatus.CANCELED);
            reservationsRepository.save(reservation);
        });
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
    public void addNewDesk(DeskRequest deskRequest) {
        Room room = roomRepository.getById(deskRequest.getRoomId());
        deskRepository.save(ReservationsMapper.DeskRequestToDesk(deskRequest, room));
    }

    public void addNewRoom(RoomRequest roomRequest) {
        Floor floor = floorRepository.getById(roomRequest.getFloorId());
        this.roomRepository.save(ReservationsMapper.RoomRequestToRoom(roomRequest, floor));
    }

    public void editRoom(RoomRequest roomRequest) {
        Room room = this.roomRepository.getById(roomRequest.getId());
        room.setRoomName(roomRequest.getRoomName());
        this.roomRepository.save(room);
    }

    public void editDesk(DeskRequest deskRequest) {
        Desk desk = this.deskRepository.getById(deskRequest.getId());
        desk.setDeskName(deskRequest.getDeskName());
        this.deskRepository.save(desk);
    }
}

