package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.admin.UserDto;
import com.example.desk_reservation_app.dto.mappers.user.UserMapper;
import com.example.desk_reservation_app.dto.requests.PasswordChangeRequest;
import com.example.desk_reservation_app.dto.requests.UserRequest;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.repositories.UserRepository;
import com.example.desk_reservation_app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public void editUser(UserRequest userRequest) {
        String password;
        if (userRequest.getPassword() == null) {
            password = userRepository.getById(userRequest.getUserId()).getPassword();
        } else {
            password = this.passwordEncoder.encode(userRequest.getPassword());
        }
        this.userRepository.save(UserMapper.userRequestToUser(userRequest, password));
    }

    public void addUser(UserRequest userRequest) {
        Optional<User> optionalUser = this.userRepository.findById(userRequest.getUserId());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("user already exists");
        }
        userRepository.save(UserMapper.userRequestToUser(userRequest, this.passwordEncoder.encode(userRequest.getPassword())));
    }

    public boolean checkIfUserIdExists(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent();
    }

    public boolean checkIfEmailExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public ResponseEntity<String> changePassword(PasswordChangeRequest passwordChangeRequest, String auth) {
        User user = this.userRepository.getById(this.jwtUtil.getSubject(auth));
        boolean currentPasswordMatch = passwordEncoder.matches(passwordChangeRequest.getCurrentPassword(), user.getPassword());
        if (currentPasswordMatch) {
            if (passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getNewPasswordRepeat())){
                if (passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getCurrentPassword())){
                    return new ResponseEntity<>("new password can not be the same as old password", HttpStatus.BAD_REQUEST);
                }
                user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
                this.userRepository.save(user);
                return new ResponseEntity<>("password changed successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("passwords does not match", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
    }
}
