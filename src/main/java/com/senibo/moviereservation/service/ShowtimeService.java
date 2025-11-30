package com.senibo.moviereservation.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.senibo.moviereservation.dto.ShowTimeRequestDTO;
import com.senibo.moviereservation.exception.NotFoundException;
import com.senibo.moviereservation.exception.RoomOccupiedException;
import com.senibo.moviereservation.model.Movie;
import com.senibo.moviereservation.model.Room;
import com.senibo.moviereservation.model.ShowTime;
import com.senibo.moviereservation.repository.MovieRepository;
import com.senibo.moviereservation.repository.RoomRepository;
import com.senibo.moviereservation.repository.ShowTimeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowtimeService {

  private final ShowTimeRepository showTimeRepository;
  private final MovieRepository movieRepository;
  private final RoomRepository roomRepository;

  public ShowTime createShowTime(ShowTimeRequestDTO request) {
    Movie currentMovie = movieRepository.findById(request.movieId()).orElseThrow(
        () -> new NotFoundException("Movie not found"));

    Room currentRoom = roomRepository.findById(request.roomId()).orElseThrow(
        () -> new NotFoundException("Room not found"));

    LocalDateTime endTime = request.startTime().plusMinutes(currentMovie.getDurationInMinutes());

    boolean guardClause = showTimeRepository.existsByRoomIdAndTimeOverlap(
        request.roomId(), request.startTime(), endTime);

    if (guardClause) {
      throw new RoomOccupiedException("Room is occupied");
    }

    ShowTime newShowTime = ShowTime.builder()
      .movieStartTime(request.startTime())
      .movieEndTime(endTime)
      .movie(currentMovie)
      .room(currentRoom)
      .build();
    
    return showTimeRepository.save(newShowTime);


  }
}