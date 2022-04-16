package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.services.ReservationsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
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

//    @GetMapping("/{date}/{userId}")
//    public ReservationsDto getReservationOnGivenDate(@PathVariable String date, @PathVariable Long userId) {
//        return reservationsService.getUserReservationByDate(LocalDate.parse(date), userId);
//    }
    @GetMapping("/{date}/{userId}")
    public ResponseEntity<ReservationsDto> getReservationOnGivenDateTEST(@PathVariable String date, @PathVariable Long userId) {
       return reservationsService.getUserReservationByDate(LocalDate.parse(date), userId);
    }


    @GetMapping("/{userId}")
    public List<ReservationsDto> getReservationOnGivenDate(@PathVariable Long userId) {
        return reservationsService.getAllReservationsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long id) {
        reservationsService.cancelReservation(id);
    }

    @PostMapping
    public void placeReservation(@RequestBody ReservationRequest reservationRequest) {
        reservationsService.placeReservation(reservationRequest);
    }
}
