package com.example.desk_reservation_app.controller;


import com.example.desk_reservation_app.dto.api.jwt.JwtRequest;
import com.example.desk_reservation_app.dto.api.jwt.JwtResponse;
import com.example.desk_reservation_app.services.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
public class LoginController {

    private final JwtService jwtService;

    public LoginController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @PostMapping()
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

}
