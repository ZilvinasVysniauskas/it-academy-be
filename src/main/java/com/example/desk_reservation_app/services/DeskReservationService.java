package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.ReservationInfo;
import com.example.desk_reservation_app.dto.api.DeskDto;
import com.example.desk_reservation_app.dto.mappers.DeskMapper;
import com.example.desk_reservation_app.dto.api.RoomDto;
import com.example.desk_reservation_app.models.Floor;
import com.example.desk_reservation_app.models.Reservations;
import com.example.desk_reservation_app.repositories.DeskRepository;
import com.example.desk_reservation_app.repositories.FloorRepository;
import com.example.desk_reservation_app.repositories.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Floor floor = floorRepository.findById(1L).get();
        List<RoomDto> roomDtoList = floor.getRooms().stream()
                .map(DeskMapper::RoomToRoomDto).collect(Collectors.toList());
        List<Reservations> reservations = reservationsRepository.findAllByDate(date);
        reservations.forEach(res -> {
            int roomId = Math.toIntExact(res.getDesk().getRoom().getId());
            int deskId = Math.toIntExact(res.getDesk().getId());
            roomDtoList.stream()
                    .filter(a -> a.getId() == roomId)
                    .findFirst().get().getDeskDtoList().stream()
                    .filter(d -> d.getId() == deskId)
                    .findFirst().get().reserveTable("userName", "additionalInfo");
        });
        return roomDtoList;
    }
}
