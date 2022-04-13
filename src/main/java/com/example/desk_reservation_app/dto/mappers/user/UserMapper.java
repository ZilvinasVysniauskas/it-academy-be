package com.example.desk_reservation_app.dto.mappers.user;

import com.example.desk_reservation_app.dto.api.admin.UserAdminDto;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.models.User;

public class UserMapper {

    public static UserAdminDto userToUserAdminDto(User user) {
        return UserAdminDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .isActive(user.isActive())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .userId(user.getUserId())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }

    public static User userRequestToUser(UserRequest userRequest) {
        boolean[] booleans = new boolean[]{false, true};
        return User.builder()
                .userId(userRequest.getUserId())
                .active(booleans[userRequest.getIsActive()])
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .middleName(userRequest.getMiddleName())
                .password(userRequest.getPassword())
                .role(userRequest.getRole())
                .build();
    }
}
