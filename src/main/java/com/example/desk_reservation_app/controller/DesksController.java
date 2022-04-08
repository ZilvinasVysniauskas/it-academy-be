package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.desks.ReservationsUserDto;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.models.Reservations;
import com.example.desk_reservation_app.services.DeskReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/desks")
public class DesksController {

    private DeskReservationService deskReservationService;

    public DesksController(DeskReservationService deskReservationService) {
        this.deskReservationService = deskReservationService;
    }

    @GetMapping("/reservations")
    @CrossOrigin
    public List<ReservationsUserDto> getRoomsWithDesks(){
        return deskReservationService.getUserReservations();
    }

    @GetMapping("/{date}")
    @CrossOrigin
    public List<RoomDto> getReservations(@PathVariable String date){
        return deskReservationService.getTablesByDate(LocalDate.parse(date));
    }



}
