package com.example.desk_reservation_app.controller;


import com.example.desk_reservation_app.dto.api.admin.UserDto;
import com.example.desk_reservation_app.dto.requests.PasswordChangeRequest;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.services.UserService;
import org.springframework.http.ResponseEntity;
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
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public UserDto getUser(@RequestHeader("Authorization") String auth) {
        return userService.getUser(auth);
    }

    @PutMapping()
    public void editUser(@Valid @RequestBody UserRequest userRequest) {
        this.userService.editUser(userRequest);
    }

    @PutMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest, @RequestHeader("Authorization") String auth) {
        return this.userService.changePassword(passwordChangeRequest, auth);
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
