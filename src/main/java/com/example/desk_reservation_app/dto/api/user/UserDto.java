package com.example.desk_reservation_app.dto.api.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class UserDto {

    private Long userId;

    private String firstName;

    private String lastName;

}
