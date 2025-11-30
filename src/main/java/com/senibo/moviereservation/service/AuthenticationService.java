package com.senibo.moviereservation.service;

import com.senibo.moviereservation.dto.LoginRequest;
import com.senibo.moviereservation.dto.RegisterRequest;
import com.senibo.moviereservation.exception.AlreadyExistsException;
import com.senibo.moviereservation.model.Role;
import com.senibo.moviereservation.model.User;
import com.senibo.moviereservation.repository.UserRepository;
import com.senibo.moviereservation.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        // Check if email exists
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new AlreadyExistsException("A user with this email already exists"); // Replace with specific exception if you want
        }

        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        // Return JWT immediately so they don't have to login again
        return jwtService.generateToken(user);
    }

    public String login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()));

        // If we get here, the user is authenticated
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();

        return jwtService.generateToken(user);
    }
}