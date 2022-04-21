package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.requests.RoomRequest;
import com.example.desk_reservation_app.services.DesksService;
import com.example.desk_reservation_app.services.ReservationsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final DesksService desksService;
    private final ReservationsService reservationsService;

    public RoomController(DesksService desksService, ReservationsService reservationsService) {
        this.desksService = desksService;
        this.reservationsService = reservationsService;
    }

    @PostMapping()
    public void addRoom(@RequestBody RoomRequest roomRequest) {
        this.desksService.addNewRoom(roomRequest);
    }

    @DeleteMapping("/{id}")
    public void addRoom(@PathVariable Long id) {
        this.reservationsService.deleteRoom(id);
    }

    @PutMapping()
    public void editRoom(@RequestBody RoomRequest roomRequest) {
        this.desksService.editRoom(roomRequest);
    }

}
