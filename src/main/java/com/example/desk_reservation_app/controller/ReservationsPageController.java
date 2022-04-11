package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.desks.ReservationsUserDto;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.services.DeskReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reservations")
public class ReservationsPageController {

    private DeskReservationService deskReservationService;

    public ReservationsPageController(DeskReservationService deskReservationService) {
        this.deskReservationService = deskReservationService;
    }

    @GetMapping("/{date}")
    @CrossOrigin
    public List<RoomDto> getReservations(@PathVariable String date){
        return deskReservationService.getTablesByDate(LocalDate.parse(date));
    }

    @PostMapping
    @CrossOrigin
    public void reserveTableOrUpdateReservation(@RequestBody ReservationRequest reservationRequest){
        deskReservationService.reserveTableOrUpdateReservation(reservationRequest);
    }

}
