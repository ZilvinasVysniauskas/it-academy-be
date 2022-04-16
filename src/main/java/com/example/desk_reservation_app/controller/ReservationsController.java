package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.dto.requests.ReservationRequest;
import com.example.desk_reservation_app.services.ReservationsService;
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

    @GetMapping("/{date}/{userId}")
    public ReservationsDto getReservationOnGivenDate(@PathVariable String date, @PathVariable Long userId) {
        return reservationsService.getUserReservationByDate(LocalDate.parse(date), userId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the updated credit agreement application", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiErrorBadRequest.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
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
