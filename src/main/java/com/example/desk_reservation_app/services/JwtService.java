package com.example.desk_reservation_app.services;

import com.example.desk_reservation_app.dto.api.jwt.JwtRequest;
import com.example.desk_reservation_app.dto.api.jwt.JwtResponse;
import com.example.desk_reservation_app.dto.mappers.user.UserMapper;
import com.example.desk_reservation_app.models.User;
import com.example.desk_reservation_app.repositories.UserRepository;
import com.example.desk_reservation_app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtService(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        Long userId = jwtRequest.getUserId();
        String userPassword = jwtRequest.getPassword();
        authenticate(userId, userPassword);
        UserDetails userDetails = loadUserByUsername(String.valueOf(userId));
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = userRepository.findById(userId).get();
        return new JwtResponse(UserMapper.userToUserDto(user), newGeneratedToken);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        return new HashSet<>();
    }

    private void authenticate(Long userId, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    String.valueOf(user.get().getUserId()),
                    user.get().getPassword(),
                    getAuthority(user.get())
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + userId);
        }
    }
}
