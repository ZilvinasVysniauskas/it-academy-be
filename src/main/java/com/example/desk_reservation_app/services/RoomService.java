package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.mappers.desk.ReservationsMapper;
import com.example.desk_reservation_app.dto.requests.RoomRequest;
import com.example.desk_reservation_app.models.Room;
import com.example.desk_reservation_app.repositories.FloorRepository;
import com.example.desk_reservation_app.repositories.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final FloorRepository floorRepository;

    public RoomService(RoomRepository roomRepository, FloorRepository floorRepository) {
        this.roomRepository = roomRepository;
        this.floorRepository = floorRepository;
    }

    public void addNewRoom(RoomRequest roomRequest) {
        this.roomRepository.save(ReservationsMapper.RoomRequestToRoom(roomRequest, floorRepository));
    }

    public void editRoom(RoomRequest roomRequest) {
        Room room = this.roomRepository.getById(roomRequest.getId());
        room.setRoomName(roomRequest.getRoomName());
        this.roomRepository.save(room);
    }

}
