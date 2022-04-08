package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.desks.ReservationsUserDto;
import com.example.desk_reservation_app.services.DeskReservationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/user")
public class UserPageController {

    private final DeskReservationService deskReservationService;

    public UserPageController(DeskReservationService deskReservationService) {
        this.deskReservationService = deskReservationService;
    }

    @GetMapping("/reservations")
    @CrossOrigin
    public List<ReservationsUserDto> getRoomsWithDesks(){
        return deskReservationService.getUserReservations();
    }

}
