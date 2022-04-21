package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> findAllByBuildingIdAndFloorDeletedFalse(Long buildingId);
}
