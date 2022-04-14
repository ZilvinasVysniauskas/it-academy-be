package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.services.ReservationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationsController {

    private final ReservationsService reservationsService;

    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping("/{date}")
    public ResponseEntity<ReservationsDto> getReservationOnGivenDateTEST(@PathVariable String date, @RequestHeader("Authorization") String auth) {
       return reservationsService.getUserReservationByDate(LocalDate.parse(date), auth);
    }


    @GetMapping()
    public List<ReservationsDto> getAllUserReservations(@RequestHeader("Authorization") String auth) {
        return reservationsService.getAllReservationsByUserId(auth);
    }

    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long id) {
        reservationsService.cancelReservation(id);
    }

    @PostMapping
    public void placeReservation(@RequestHeader("Authorization") String auth, @RequestBody ReservationRequest reservationRequest) {
        reservationsService.placeReservation(reservationRequest, auth);
    }
}
