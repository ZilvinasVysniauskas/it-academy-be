package com.example.desk_reservation_app.dto.api.admin;

import com.example.desk_reservation_app.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserAdminDto {

    private Long userId;

    private String firstName;

    private String middleName;

    private String lastName;

    private boolean isActive;

    private String email;

    private String password;

    private Role role;

}