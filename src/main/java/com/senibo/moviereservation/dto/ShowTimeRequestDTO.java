package com.senibo.moviereservation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record ShowTimeRequestDTO(
    @NotNull(message = "MovieId is required") UUID movieId,
    @NotNull(message = "roomId is required") UUID roomId,

    @NotNull(message = "start time is required") LocalDateTime startTime) {
}
