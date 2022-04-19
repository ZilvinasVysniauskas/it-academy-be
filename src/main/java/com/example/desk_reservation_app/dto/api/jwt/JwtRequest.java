package com.example.desk_reservation_app.dto.api.jwt;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class JwtRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String password;

}
