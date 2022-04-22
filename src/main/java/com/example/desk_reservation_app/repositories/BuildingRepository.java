package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findAllByBuildingDeletedFalse();
}
