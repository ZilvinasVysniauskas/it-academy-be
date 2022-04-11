package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.desks.ReservationsUserDto;
import com.example.desk_reservation_app.dto.api.desks.RoomDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.services.DeskReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/v1/reservations")
public class ReservationsPageController {

    private DeskReservationService deskReservationService;

    public ReservationsPageController(DeskReservationService deskReservationService) {
        this.deskReservationService = deskReservationService;
    }

    @GetMapping("/{date}")
    @CrossOrigin
    public List<RoomDto> getReservations(@PathVariable String date) {
        return deskReservationService.getTablesByDate(LocalDate.parse(date));
    }

    @GetMapping("/{date}/{userId}")
    @CrossOrigin
    public ReservationsUserDto getReservationOnGivenDate(@PathVariable String date, @PathVariable Long userId) {
        return deskReservationService.getUserReservationByDate(LocalDate.parse(date), userId);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void cancelReservation (@PathVariable Long id) {
        deskReservationService.cancelReservation(id);
    }

    @PostMapping
    @CrossOrigin
    public void reserveTableOrUpdateReservation(@RequestBody ReservationRequest reservationRequest) {
        deskReservationService.reserveTableOrUpdateReservation(reservationRequest);
    }

}
