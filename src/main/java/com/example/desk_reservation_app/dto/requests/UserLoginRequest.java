package com.example.desk_reservation_app.dto.requests;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class UserLoginRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String password;

}
