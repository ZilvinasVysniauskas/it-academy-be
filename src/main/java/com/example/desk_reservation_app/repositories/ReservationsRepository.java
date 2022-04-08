package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    List<Reservations> findAllByDate(LocalDate date);
    List<Reservations> findReservationsByUserUserId(Long id);
}
