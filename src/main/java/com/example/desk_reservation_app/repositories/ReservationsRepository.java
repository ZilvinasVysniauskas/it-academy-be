package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationsByDateAndReservationStatusIsNull(LocalDate date);
    List<Reservation> findReservationsByUserUserId(Long id);
    Optional<Reservation> findReservationsByDateAndUserUserIdAndReservationStatusIsNull(LocalDate date, Long userId);
}
