package com.senibo.moviereservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senibo.moviereservation.dto.ApiSuccessResponse;
import com.senibo.moviereservation.dto.ReservationRequestDTO;
import com.senibo.moviereservation.model.Reservation;
import com.senibo.moviereservation.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<ApiSuccessResponse<Reservation>> newReservation(
      @Valid @RequestBody ReservationRequestDTO request) {

    Reservation reservation = reservationService.createReservation(request);

    ApiSuccessResponse<Reservation> response = new ApiSuccessResponse<>(
        true,
        "Reservation Reserved Successfully",
        reservation);

    return ResponseEntity.status(201).body(response);
  }
}
