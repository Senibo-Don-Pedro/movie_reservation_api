package com.senibo.moviereservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senibo.moviereservation.dto.ApiSuccessResponse;
import com.senibo.moviereservation.dto.ShowTimeRequestDTO;
import com.senibo.moviereservation.model.ShowTime;
import com.senibo.moviereservation.service.ShowtimeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/showtimes")
@RequiredArgsConstructor
public class ShowTimeController {

  private final ShowtimeService showtimeService;

  @PostMapping
  public ResponseEntity<ApiSuccessResponse<ShowTime>> createShowtime(
      @Valid @RequestBody ShowTimeRequestDTO request) {

    ShowTime savedShowTime = showtimeService.createShowTime(request);

    ApiSuccessResponse<ShowTime> response = new ApiSuccessResponse<>(
        true,
        "Showtime Scheduled Successfully",
        savedShowTime);

    return ResponseEntity.status(201).body(response);
  }
}
