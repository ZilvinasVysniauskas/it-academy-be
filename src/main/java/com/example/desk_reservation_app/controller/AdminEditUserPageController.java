package com.example.desk_reservation_app.controller;

import com.example.desk_reservation_app.dto.api.admin.UserAdminDto;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminEditUserPageController {


    private final UserService userService;

    public AdminEditUserPageController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public List<UserAdminDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user")
    public void editUser(@Valid @RequestBody UserRequest userRequest) {
        System.out.println(userRequest);
        this.userService.editUser(userRequest);
    }

    @PostMapping("/user")
    public void addUser(@Valid @RequestBody UserRequest userRequest) {
        this.userService.addUser(userRequest);
    }

    @GetMapping("/user/id/{userId}")
    public boolean checkIfUserIdExists(@PathVariable Long userId) {
        return userService.checkIfUserIdExists(userId);
    }

    @GetMapping("/user/email/{email}")
    public boolean checkIfUserIdExists(@PathVariable String  email) {
        return userService.checkIfEmailExists(email);
    }
}
