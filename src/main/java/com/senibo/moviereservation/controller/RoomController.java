package com.senibo.moviereservation.controller;

import com.senibo.moviereservation.dto.ApiSuccessResponse;
import com.senibo.moviereservation.dto.RoomRequestDTO;
import com.senibo.moviereservation.model.Room;
import com.senibo.moviereservation.service.RoomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth") // This ensures Swagger shows the lock icon
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<Room>> createRoom(@Valid @RequestBody RoomRequestDTO request) {
        Room savedRoom = roomService.createRoom(request);
        
        return ResponseEntity.status(201).body(new ApiSuccessResponse<>(
                true,
                "Room created with " + savedRoom.getSeats().size() + " seats generated.",
                savedRoom
        ));
    }
}