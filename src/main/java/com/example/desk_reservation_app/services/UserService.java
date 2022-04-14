package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.UserAdminDto;
import com.example.desk_reservation_app.dto.mappers.user.UserMapper;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void editUser(UserRequest userRequest) {
        this.userRepository.save(UserMapper.userRequestToUser(userRequest));
    }

    public void addUser(UserRequest userRequest) {
        Optional<User> optionalUser = this.userRepository.findById(userRequest.getUserId());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("user already exists");
        }
        userRepository.save(UserMapper.userRequestToUser(userRequest));
    }

    public boolean checkIfUserIdExists(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent();
    }

    public boolean checkIfEmailExists(String  email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}
