package com.example.desk_reservation_app.dto.mappers.user;

import com.example.desk_reservation_app.dto.api.admin.UserDto;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                .password(user.getPassword())
                .build();
    }

    public static User userRequestToUser(UserRequest userRequest, PasswordEncoder passwordEncoder) {
        boolean[] booleans = new boolean[]{false, true};
        return User.builder()
                .userId(userRequest.getUserId())
                .active(booleans[userRequest.getIsActive()])
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .middleName(userRequest.getMiddleName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(userRequest.getRole())
                .build();
    }
}
