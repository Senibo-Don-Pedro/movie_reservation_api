package com.senibo.moviereservation.controller;

import com.senibo.moviereservation.dto.ApiSuccessResponse;
import com.senibo.moviereservation.dto.LoginRequest;
import com.senibo.moviereservation.dto.RegisterRequest;
import com.senibo.moviereservation.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiSuccessResponse<Object>> register(@Valid @RequestBody RegisterRequest request) {
        String token = authService.register(request);
        return ResponseEntity.ok(new ApiSuccessResponse<>(true, "User registered successfully", Map.of(
                "bearer_token", token)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiSuccessResponse<Object>> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new ApiSuccessResponse<>(true, "Login successful", Map.of(
                "bearer_token", token)));
    }
}