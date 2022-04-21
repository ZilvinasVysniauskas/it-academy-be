package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.places.RoomDto;
import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.dto.requests.DeskRequest;
import com.example.desk_reservation_app.dto.requests.RoomRequest;
import com.example.desk_reservation_app.models.*;
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
    private final BuildingRepository buildingRepository;

    public DesksService(FloorRepository floorRepository, ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository, RoomRepository roomRepository, BuildingRepository buildingRepository) {
        this.floorRepository = floorRepository;
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
        this.roomRepository = roomRepository;
        this.buildingRepository = buildingRepository;
    }

    public List<RoomDto> getAllDesksWithReservationsByDate(Long floorId, LocalDate date) {
        return placeReservations(date, getAllRoomsByFloor(floorId));
    }

    public List<RoomDto> getAllRoomsByFloor(Long floorId) {
        List<Room> rooms = roomRepository.findAllByFloorIdAndRoomDeletedFalse(floorId);
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





    public void addNewDesk(DeskRequest deskRequest) {
        deskRepository.save(ReservationsMapper.DeskRequestToDesk(deskRequest, roomRepository));
    }

    public void addNewRoom(RoomRequest roomRequest) {
        this.roomRepository.save(ReservationsMapper.RoomRequestToRoom(roomRequest, floorRepository));
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

