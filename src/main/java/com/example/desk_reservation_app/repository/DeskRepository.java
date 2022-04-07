package com.example.desk_reservation_app.repository;

import com.example.desk_reservation_app.models.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {
}
