package com.senibo.moviereservation.service;

import com.senibo.moviereservation.dto.ShowTimeRequestDTO;
import com.senibo.moviereservation.exception.RoomOccupiedException;
import com.senibo.moviereservation.model.Movie;
import com.senibo.moviereservation.model.Room;
import com.senibo.moviereservation.model.ShowTime;
import com.senibo.moviereservation.repository.MovieRepository;
import com.senibo.moviereservation.repository.RoomRepository;
import com.senibo.moviereservation.repository.ShowTimeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowtimeServiceTest {

  @Mock
  private ShowTimeRepository showTimeRepository;
  @Mock
  private MovieRepository movieRepository;
  @Mock
  private RoomRepository roomRepository;

  @InjectMocks
  private ShowtimeService showtimeService; // Make sure this class name matches your file exactly

  @Test
  void createShowTime_ShouldThrowException_WhenRoomIsOccupied() {
    // 1. ARRANGE
    UUID movieId = UUID.randomUUID();
    UUID roomId = UUID.randomUUID();
    LocalDateTime start = LocalDateTime.now();

    // The Request
    ShowTimeRequestDTO request = new ShowTimeRequestDTO(movieId, roomId, start);

    // The Mock Entities
    Movie movie = Movie.builder().id(movieId).durationInMinutes(120).build();
    Room room = Room.builder().id(roomId).build();

    // TEACH THE MOCKS:
    // "Find the movie? Sure, here it is."
    when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
    // "Find the room? Sure, here it is."
    when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

    // "Check for overlap? YES, IT IS OCCUPIED (return true)."
    when(showTimeRepository.existsByRoomIdAndTimeOverlap(any(), any(), any()))
        .thenReturn(true);

    // 2. ACT & ASSERT
    // We expect this specific exception to be thrown
    assertThrows(RoomOccupiedException.class, () -> {
      showtimeService.createShowTime(request);
    });

    // VERIFY
    // Ensure we NEVER called save() because the room was busy
    verify(showTimeRepository, never()).save(any());
  }

  @Test
  void createShowTime_ShouldSave_WhenRoomIsFree() {
    // 1. ARRANGE
    UUID movieId = UUID.randomUUID();
    UUID roomId = UUID.randomUUID();
    LocalDateTime start = LocalDateTime.now();

    ShowTimeRequestDTO request = new ShowTimeRequestDTO(movieId, roomId, start);
    Movie movie = Movie.builder().id(movieId).durationInMinutes(120).build();
    Room room = Room.builder().id(roomId).build();

    // Expected result from save (just a dummy object)
    ShowTime savedShowTime = ShowTime.builder().movie(movie).room(room).build();

    when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
    when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

    // "Check for overlap? NO, IT IS FREE (return false)."
    when(showTimeRepository.existsByRoomIdAndTimeOverlap(any(), any(), any()))
        .thenReturn(false);

    // "Save? Sure, return the object."
    when(showTimeRepository.save(any(ShowTime.class))).thenReturn(savedShowTime);

    // 2. ACT
    ShowTime result = showtimeService.createShowTime(request);

    // 3. ASSERT
    assertNotNull(result);
    verify(showTimeRepository, times(1)).save(any(ShowTime.class));
  }
}