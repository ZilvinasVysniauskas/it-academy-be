package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
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

    @GetMapping("/{date}")
    public List<RoomDto> getReservations(@PathVariable String date) {
        return desksService.getTablesByDate(LocalDate.parse(date));
    }

    @DeleteMapping("/{id}")
    public void deleteDeskById(@PathVariable String id) {
        desksService.deleteDeskById(Long.parseLong(id));
    }
}
