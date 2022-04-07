package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.RoomRequest;
import com.example.desk_reservation_app.models.Desk;
import com.example.desk_reservation_app.models.Room;
import com.example.desk_reservation_app.repository.DeskRepository;
import com.example.desk_reservation_app.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class DesksController {

    @Autowired
    private RoomService roomService;

    @PostMapping(value = "room")
    public void addRoom(@RequestBody RoomRequest roomRequest){
        roomService.addRoom(roomRequest);
    }

    @GetMapping(value = "room")
    public List<Room> getRooms(){
        return roomService.findAllRooms();
    }

    @GetMapping(value = "desk")
    public List<Desk> getDesks(){
        return roomService.findAllDesks();
    }
}
