package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.services.DeskReservationService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reservations")
public class ReservationsPageController {

    private final DeskReservationService deskReservationService;









    public ReservationsPageController(DeskReservationService deskReservationService) {
        this.deskReservationService = deskReservationService;
    }

    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long id) {
        deskReservationService.cancelReservation(id);
    }

    @PostMapping
    public void reserveTableOrUpdateReservation(@RequestBody ReservationRequest reservationRequest) {
        deskReservationService.reserveTableOrUpdateReservation(reservationRequest);
    }

}
