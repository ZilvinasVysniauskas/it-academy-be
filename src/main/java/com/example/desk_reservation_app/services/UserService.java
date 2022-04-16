package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.UserDto;
import com.example.desk_reservation_app.dto.mappers.user.UserMapper;
import com.example.desk_reservation_app.dto.requests.UserLoginRequest;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::userToUserDto)
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

    public ResponseEntity<UserDto> userLogin(UserLoginRequest loginRequest) {
        Optional<User> user = userRepository.findById(loginRequest.getUserId());
        if (user.isPresent()) {
            if (loginRequest.getPassword().equals(user.get().getPassword())) {
                return new ResponseEntity<>(UserMapper.userToUserDto(user.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
