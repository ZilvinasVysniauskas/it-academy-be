package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {

    List<Desk> findDeskByRoomIdAndDeskDeletedFalse(Long roomId);
}

