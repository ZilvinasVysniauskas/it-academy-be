package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.ReservationsAdminDto;
import com.example.desk_reservation_app.dto.api.user.UserAdminDto;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.services.DeskReservationService;
import com.example.desk_reservation_app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminPageController {

    private final DeskReservationService deskReservationService;
    private final UserService userService;

    public AdminPageController(DeskReservationService deskReservationService, UserService userService) {
        this.deskReservationService = deskReservationService;
        this.userService = userService;
    }

    @PutMapping("/user")
    public void editUser(@RequestBody UserRequest userRequest) {
        this.userService.editUser(userRequest);
    }

    @PostMapping("/user")
    //todo Sandra: check if in DB user exists, if exists - return string already exists, else save new user
    //su servisais ir mapperiais requestais request body etc


    @GetMapping("/reservations")
    @CrossOrigin
    public List<ReservationsAdminDto> getAllReservations() {
        return deskReservationService.getAllReservationsForAdmin();
    }

    @GetMapping( "/users/{id}")
    public UserAdminDto getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<UserAdminDto> getAllUsers() {
        return userService.getAllUsers();
    }

}
