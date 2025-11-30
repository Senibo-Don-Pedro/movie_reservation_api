package com.senibo.moviereservation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomRequestDTO(
    @NotBlank(message = "Room name is required")
    String name,

    @NotNull
    @Min(value = 1, message = "Must have at least 1 row")
    Integer rowCount, // e.g., 10 rows (A-J)

    @NotNull
    @Min(value = 1, message = "Must have at least 1 seat per row")
    Integer seatsPerRow // e.g., 10 seats (1-10)
) {}