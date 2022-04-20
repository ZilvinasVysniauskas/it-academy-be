package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.DeskRequest;
import com.example.desk_reservation_app.services.DesksService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/desks")
public class DesksController {

    private final DesksService desksService;

    public DesksController(DesksService desksService) {
        this.desksService = desksService;
    }

    @GetMapping()
    public List<RoomDto> getAllDesks() {
        return desksService.getAllRoomsByFloor();
    }

    @GetMapping("/{date}")
    public List<RoomDto> getReservations(@PathVariable String date) {
        return desksService.getAllDesksWithReservationsByDate(LocalDate.parse(date));
    }

    @DeleteMapping("/{id}")
    public void deleteDeskById(@PathVariable String id) {
        desksService.deleteDeskById(Long.parseLong(id));
    }

    @PostMapping()
    public void addDesk(@RequestBody DeskRequest deskRequest) {
        desksService.addNewDesk(deskRequest);
    }
}
