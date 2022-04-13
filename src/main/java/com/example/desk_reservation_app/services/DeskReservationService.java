package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Floor;
import com.example.desk_reservation_app.models.Reservation;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.models.enums.ReservationStatus;
import com.example.desk_reservation_app.repositories.DeskRepository;
import com.example.desk_reservation_app.repositories.FloorRepository;
import com.example.desk_reservation_app.repositories.ReservationsRepository;
import com.example.desk_reservation_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        Floor floor = floorRepository.findById(1L).get();
        List<RoomDto> roomDtoList = floor.getRooms().stream()
                .map(ReservationsMapper::RoomToRoomDto).collect(Collectors.toList());
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

    public void reserveTableOrUpdateReservation(ReservationRequest reservationRequest) {
        Desk desk = this.deskRepository.getById(reservationRequest.getDeskId());
        User user = this.userRepository.getById(reservationRequest.getUserId());
        this.reservationsRepository.save(ReservationsMapper.reservationRequestToReservation(reservationRequest, desk, user));
    }

    public ReservationsDto getUserReservationByDate(LocalDate date, Long userId) {
        Optional<Reservation> optionalReservations = this.reservationsRepository.findReservationsByDateAndUserUserIdAndReservationStatusIsNull(date, userId);
        return optionalReservations.map(ReservationsMapper::ReservationToReservationDto).orElse(null);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = this.reservationsRepository.findById(id).get();
        reservation.setReservationStatus(ReservationStatus.CANCELED);
        this.reservationsRepository.save(reservation);
    }

    public List<ReservationsDto> getAllReservationsByUserId(Long id) {
        List<Reservation> allUserReservations = this.reservationsRepository.findReservationsByUserUserId(id);
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
}
