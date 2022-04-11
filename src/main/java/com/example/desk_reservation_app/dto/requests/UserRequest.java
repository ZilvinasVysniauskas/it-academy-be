package com.example.desk_reservation_app.dto.requests;

import com.example.desk_reservation_app.models.enums.Role;
import lombok.Data;

@Data
public class UserRequest {
    //TODO validations
    private Long userId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String password;

    private String passwordRepeat;

    private int isActive;

    private String email;

    private Role role;

}
