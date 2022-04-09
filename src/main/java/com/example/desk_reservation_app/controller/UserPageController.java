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

    @GetMapping("/reservations/{id}")
    @CrossOrigin
    public List<ReservationsUserDto> getRoomsWithDesks(@PathVariable Long id){
        return deskReservationService.getUserReservations(id);
    }

    @GetMapping("reservations/cancel/{id}")
    //todo Zymante: delete reservation by id
    public void toDoFunction () {}

}
