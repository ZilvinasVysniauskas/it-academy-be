package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.requests.RoomRequest;
import com.example.desk_reservation_app.services.DesksService;
import com.example.desk_reservation_app.services.ReservationsService;
import com.example.desk_reservation_app.services.RoomService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final ReservationsService reservationsService;
    private final RoomService roomService;

    public RoomController(ReservationsService reservationsService, RoomService roomService) {
        this.reservationsService = reservationsService;
        this.roomService = roomService;
    }

    @PostMapping()
    public void addRoom(@RequestBody RoomRequest roomRequest) {
        this.roomService.addNewRoom(roomRequest);
    }

    @DeleteMapping("/{id}")
    public void addRoom(@PathVariable Long id) {
        this.reservationsService.deleteRoom(id);
    }

    @PutMapping()
    public void editRoom(@RequestBody RoomRequest roomRequest) {
        this.roomService.editRoom(roomRequest);
    }

}
