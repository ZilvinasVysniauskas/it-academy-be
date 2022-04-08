package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.user.UserDto;
import com.example.desk_reservation_app.services.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping( "/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

}
