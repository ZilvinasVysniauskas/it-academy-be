package com.example.desk_reservation_app.controller;
import com.example.desk_reservation_app.dto.api.admin.ReservationsDto;
import com.example.desk_reservation_app.services.DeskReservationService;
import com.example.desk_reservation_app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/user")
public class UserPageController {

    private final DeskReservationService deskReservationService;

    public UserPageController(DeskReservationService deskReservationService) {
        this.deskReservationService = deskReservationService;
    }

    @GetMapping("reservations/{id}")
    @CrossOrigin
    public List<ReservationsDto> getAllUserReservations(@PathVariable Long id) {
        return deskReservationService.getAllReservationsByUserId(id);
    }

    @DeleteMapping("reservations/{id}")
    public void cancelReservationUserPage(@PathVariable Long id) {
        deskReservationService.cancelReservation(id);
    }
}
