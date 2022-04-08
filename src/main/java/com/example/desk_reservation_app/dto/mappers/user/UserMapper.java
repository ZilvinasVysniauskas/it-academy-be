package com.example.desk_reservation_app.dto.mappers.user;

import com.example.desk_reservation_app.dto.api.user.UserDto;
import com.example.desk_reservation_app.models.User;

public class UserMapper {

    public static UserDto userToUserDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

}
