package com.example.desk_reservation_app.dto.api.user;

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

    private String password;

    private boolean isActive;

    private String email;

}