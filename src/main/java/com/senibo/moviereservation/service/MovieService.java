package com.senibo.moviereservation.service;

import org.springframework.stereotype.Service;

import com.senibo.moviereservation.dto.MovieRequestDTO;
import com.senibo.moviereservation.model.Movie;
import com.senibo.moviereservation.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;

  public Movie addMovie(MovieRequestDTO request) {

    Movie movie = Movie.builder()
        .title(request.title())
        .description(request.description())
        .durationInMinutes(request.durationInMinutes())
        .imageUrl(request.imageURL() != null ? request.imageURL() : "")
        .movieCategory(request.movieCategory())
        .build();

    Movie savedMovie = movieRepository.save(movie);

    return savedMovie;
  }
}
