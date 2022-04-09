package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.user.UserAdminDto;
import com.example.desk_reservation_app.dto.mappers.user.UserMapper;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserAdminDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::userToUserAdminDto)
                .collect(Collectors.toList());
    }

    public UserAdminDto getUserById(Long id) {
        return UserMapper.userToUserAdminDto(userRepository.getById(id));
    }

    public void editUser(UserRequest userRequest) {
        this.userRepository.save(UserMapper.userRequestToUser(userRequest));
    }
}
