package com.senibo.moviereservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senibo.moviereservation.dto.ApiSuccessResponse;
import com.senibo.moviereservation.dto.MovieRequestDTO;
import com.senibo.moviereservation.model.Movie;
import com.senibo.moviereservation.service.MovieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

  private final MovieService movieService;

  @PostMapping
  public ResponseEntity<ApiSuccessResponse<Movie>> createMovie(
      @Valid @RequestBody MovieRequestDTO request) {

    Movie savedMovie = movieService.addMovie(request);

    ApiSuccessResponse<Movie> response = new ApiSuccessResponse<>(
        true,
        "Movie Created Successfully",
        savedMovie);

    return ResponseEntity.status(201).body(response);
  }
}
