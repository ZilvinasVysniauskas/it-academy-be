package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.UserDto;
import com.example.desk_reservation_app.dto.mappers.user.UserMapper;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public void editUser(UserRequest userRequest) {
        this.userRepository.save(UserMapper.userRequestToUser(userRequest, passwordEncoder));
    }

    public void addUser(UserRequest userRequest) {
        Optional<User> optionalUser = this.userRepository.findById(userRequest.getUserId());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("user already exists");
        }
        userRepository.save(UserMapper.userRequestToUser(userRequest, passwordEncoder));
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
