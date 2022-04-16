package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Reservation;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.models.enums.ReservationStatus;
import com.example.desk_reservation_app.repositories.DeskRepository;
import com.example.desk_reservation_app.repositories.ReservationsRepository;
import com.example.desk_reservation_app.repositories.UserRepository;
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

    public ReservationsService(ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository) {
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
    }


    public ReservationsDto getUserReservationByDate(LocalDate date, Long userId) {
        Optional<Reservation> optionalReservations = this.reservationsRepository.findReservationsByDateAndUserUserIdAndReservationStatusIsNull(date, userId);
        return optionalReservations.map(ReservationsMapper::ReservationToReservationDto).orElse(null);
    }

    public List<ReservationsDto> getAllReservationsByUserId(Long id) {
        List<Reservation> allUserReservations = this.reservationsRepository.findReservationsByUserUserId(id);
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

    public void placeReservation(ReservationRequest reservationRequest) {
        Desk desk = this.deskRepository.getById(reservationRequest.getDeskId());
        User user = this.userRepository.getById(reservationRequest.getUserId());
        this.reservationsRepository.save(ReservationsMapper.reservationRequestToReservation(reservationRequest, desk, user));
    }

    public void cancelReservation(Long id) {
        Reservation reservation = this.reservationsRepository.findById(id).get();
        reservation.setReservationStatus(ReservationStatus.CANCELED);
        this.reservationsRepository.save(reservation);
    }


}
