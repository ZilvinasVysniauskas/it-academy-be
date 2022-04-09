package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.ReservationsAdminDto;
import com.example.desk_reservation_app.dto.api.desks.ReservationsUserDto;
import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Floor;
import com.example.desk_reservation_app.models.Reservations;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.repositories.DeskRepository;
import com.example.desk_reservation_app.repositories.FloorRepository;
import com.example.desk_reservation_app.repositories.ReservationsRepository;
import com.example.desk_reservation_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeskReservationService {

    private final FloorRepository floorRepository;
    private final ReservationsRepository reservationsRepository;
    private final DeskRepository deskRepository;
    private final UserRepository userRepository;

    public DeskReservationService(FloorRepository floorRepository, ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository) {
        this.floorRepository = floorRepository;
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
    }

    public List<RoomDto> getTablesByDate(LocalDate date) {
        //todo user can reserve only one table per day
        //todo hardcoded floor
        Floor floor = floorRepository.findById(1L).get();
        List<RoomDto> roomDtoList = floor.getRooms().stream()
                .map(ReservationsMapper::RoomToRoomDto).collect(Collectors.toList());
        List<Reservations> reservations = reservationsRepository.findAllByDate(date);
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

    public List<ReservationsUserDto> getUserReservations(Long id) {
        //todo hardcoded userId
        return this.reservationsRepository.findReservationsByUserUserId(id)
                .stream().map(ReservationsMapper::ReservationToReservationUserDto)
                .collect(Collectors.toList());
    }

    public List<ReservationsAdminDto> getAllReservationsForAdmin() {
        return this.reservationsRepository.findAll().stream()
                .map(ReservationsMapper::ReservationToReservationAdminDto)
                .collect(Collectors.toList());
    }

    public void reserveTableOrUpdateReservation(ReservationRequest reservationRequest) {
        //todo unable multiple reservations per day
        Desk desk = this.deskRepository.getById(reservationRequest.getDeskId());
        User user = this.userRepository.getById(reservationRequest.getUserId());
        this.reservationsRepository.save(ReservationsMapper.reservationRequestToReservation(reservationRequest, desk, user));
    }


}
