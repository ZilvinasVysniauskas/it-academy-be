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
        System.out.println(userRequest);
        this.userService.editUser(userRequest);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody UserRequest userRequest) {
        this.userService.addUser(userRequest);
    }


    @GetMapping("/reservations")
    @CrossOrigin
    public List<ReservationsAdminDto> getAllReservations() {
        return deskReservationService.getAllReservationsForAdmin();
    }

    @GetMapping( "/user/{id}")
    public UserAdminDto getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/user")
    public List<UserAdminDto> getAllUsers() {
        return userService.getAllUsers();
    }

}
