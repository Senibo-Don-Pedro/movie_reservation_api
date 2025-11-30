package com.senibo.moviereservation.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record ReservationRequestDTO(
    @NotNull(message = "User Id is required") UUID userId,

    @NotNull(message = "Showtime Id is required") UUID showtimeId,

    @NotNull(message = "Seat Ids are required") List<UUID> seatIds) {

}
