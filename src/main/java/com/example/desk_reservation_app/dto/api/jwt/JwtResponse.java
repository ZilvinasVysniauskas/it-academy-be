package com.example.desk_reservation_app.dto.api.jwt;

import com.example.desk_reservation_app.dto.api.admin.UserDto;
import lombok.Data;

@Data
public class JwtResponse {

    private UserDto user;

    private String jwtToken;

    public JwtResponse(UserDto user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }
}
