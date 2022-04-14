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
    private final JwtUtil jwtUtil;

    public ReservationsService(ReservationsRepository reservationsRepository, DeskRepository deskRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.reservationsRepository = reservationsRepository;
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
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
        System.out.println(user);
        this.reservationsRepository.save(ReservationsMapper.reservationRequestToReservation(reservationRequest, desk, user));
    }

    public void cancelReservation(Long id) {
        Reservation reservation = this.reservationsRepository.findById(id).get();
        reservation.setReservationStatus(ReservationStatus.CANCELED);
        this.reservationsRepository.save(reservation);
    }


}
