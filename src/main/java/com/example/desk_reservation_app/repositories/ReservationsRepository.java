package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findReservationsByDateAndDeskIdAndReservationStatusIsNull(LocalDate date, Long id);
    List<Reservation> findReservationsByUserUserId(Long id);
    List<Reservation> findReservationsByDeskIdAndDateGreaterThanEqual(Long id, LocalDate date);
    Optional<Reservation> findReservationsByDateAndUserUserIdAndReservationStatusIsNull(LocalDate date, Long userId);
}
