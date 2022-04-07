package com.example.desk_reservation_app.service;

import com.example.desk_reservation_app.dto.api.RoomRequest;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Room;
import com.example.desk_reservation_app.repository.DeskRepository;
import com.example.desk_reservation_app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    public DeskRepository deskRepository;

    public void addRoom(RoomRequest roomRequest) {

        Room room = Room.builder()
                .roomName(roomRequest.getRoomName())
                .roomNumber(roomRequest.getRoomNumber())
                .desks(List.of(new Desk(23)))
                .build();
        roomRepository.save(room);
    }

    public List<Room> findAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        System.out.println(rooms);
        return rooms;
    }

    public List<Desk> findAllDesks() {
        return deskRepository.findAll();
    }
}
