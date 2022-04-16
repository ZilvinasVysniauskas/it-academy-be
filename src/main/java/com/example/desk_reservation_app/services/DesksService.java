package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.models.Floor;
import com.example.desk_reservation_app.models.Reservation;
import com.example.desk_reservation_app.repositories.DeskRepository;
import com.example.desk_reservation_app.repositories.FloorRepository;
import com.example.desk_reservation_app.repositories.ReservationsRepository;
import com.example.desk_reservation_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DesksService {

    private final FloorRepository floorRepository;
    private final ReservationsRepository reservationsRepository;
    private final DeskRepository deskRepository;

    public DesksService(FloorRepository floorRepository, ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository) {
        this.floorRepository = floorRepository;
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
    }

    public List<RoomDto> getTablesByDate(LocalDate date) {
        Floor floor = floorRepository.findById(1L).get();
        List<RoomDto> roomDtoList = floor.getRooms().stream()
                .map(ReservationsMapper::RoomToRoomDto).collect(Collectors.toList());
        roomDtoList.forEach(a-> a.setDesks(
                deskRepository.findDeskByRoomIdAndRemovedIsFalse(a.getRoomId()).stream()
                        .map(ReservationsMapper::DeskToDeskToDto)
                        .collect(Collectors.toList())
        ));
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
        this.deskRepository.deleteById(id);
    }
}
