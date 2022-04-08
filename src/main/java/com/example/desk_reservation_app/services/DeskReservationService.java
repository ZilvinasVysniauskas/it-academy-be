package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.ReservationsAdminDto;
import com.example.desk_reservation_app.dto.api.desks.ReservationsUserDto;
import com.example.desk_reservation_app.dto.mappers.desk.DeskMapper;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.models.Floor;
import com.example.desk_reservation_app.models.Reservations;
import com.example.desk_reservation_app.repositories.FloorRepository;
import com.example.desk_reservation_app.repositories.ReservationsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeskReservationService {

    private final FloorRepository floorRepository;
    private final ReservationsRepository reservationsRepository;

    public DeskReservationService(FloorRepository floorRepository, ReservationsRepository reservationsRepository) {
        this.floorRepository = floorRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public List<RoomDto> getTablesByDate(LocalDate date) {
        //todo user can reserve only one table per day
        Floor floor = floorRepository.findById(1L).get();
        List<RoomDto> roomDtoList = floor.getRooms().stream()
                .map(DeskMapper::RoomToRoomDto).collect(Collectors.toList());
        List<Reservations> reservations = reservationsRepository.findAllByDate(date);
        reservations.forEach(res -> {
            int roomId = Math.toIntExact(res.getDesk().getRoom().getId());
            int deskId = Math.toIntExact(res.getDesk().getId());
            roomDtoList.stream()
                    .filter(a -> a.getRoomId() == roomId)
                    .findFirst().get().getDeskDtoList().stream()
                    .filter(d -> d.getId() == deskId)
                    .findFirst().get().reserveTable(res.getUser().getFirstName(), res.getUser().getLastName());
        });
        return roomDtoList;
    }

    public List<ReservationsUserDto> getUserReservations() {
        return this.reservationsRepository.findReservationsByUserUserId(12345678L)
                .stream().map(DeskMapper::ReservationToReservationUserDto)
                .collect(Collectors.toList());
    }

    public List<ReservationsAdminDto> getAllReservationsForAdmin() {
        return this.reservationsRepository.findAll().stream()
                .map(DeskMapper::ReservationToReservationAdminDto)
                .collect(Collectors.toList());
    }
}
