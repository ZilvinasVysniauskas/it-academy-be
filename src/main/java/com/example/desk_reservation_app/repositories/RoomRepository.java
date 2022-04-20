package com.example.desk_reservation_app.repositories;

import com.example.desk_reservation_app.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByFloorIdAndRoomDeletedFalse(Long id);
}
