package com.example.desk_reservation_app.dto.mappers.user;

import com.example.desk_reservation_app.dto.api.admin.UserDto;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.models.User;

public class UserMapper {

    public static UserDto userToUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .isActive(user.isActive())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .userId(user.getUserId())
                .role(user.getRole())
                .defaultFloorId(user.getDefaultFloorId())
                .build();
    }

    public static User userRequestToUser(UserRequest userRequest, String password) {
        boolean[] booleans = new boolean[]{false, true};
        return User.builder()
                .userId(userRequest.getUserId())
                .active(booleans[userRequest.getIsActive()])
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .middleName(userRequest.getMiddleName())
                .role(userRequest.getRole())
                .password(password)
                .defaultFloorId(userRequest.getDefaultFloorId())
                .build();
    }
}
