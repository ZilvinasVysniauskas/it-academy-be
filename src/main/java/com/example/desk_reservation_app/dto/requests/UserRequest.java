package com.example.desk_reservation_app.dto.requests;

import com.example.desk_reservation_app.models.enums.Role;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class UserRequest {

    private Long userId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String password;

    private int isActive;

    private String email;

    private Role role;

}
