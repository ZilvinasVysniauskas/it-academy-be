package com.example.desk_reservation_app.controller;


import com.example.desk_reservation_app.dto.api.admin.UserAdminDto;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserAdminDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping()
    public void editUser(@Valid @RequestBody UserRequest userRequest) {
        System.out.println(userRequest);
        this.userService.editUser(userRequest);
    }

    @PostMapping()
    public void addUser(@Valid @RequestBody UserRequest userRequest) {
        this.userService.addUser(userRequest);
    }


    //TODO change validations logic
    @GetMapping("/id/{userId}")
    public boolean checkIfUserIdExists(@PathVariable Long userId) {
        return userService.checkIfUserIdExists(userId);
    }

    @GetMapping("/email/{email}")
    public boolean checkIfUserIdExists(@PathVariable String  email) {
        return userService.checkIfEmailExists(email);
    }


}
