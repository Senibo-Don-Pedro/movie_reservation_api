package com.senibo.moviereservation.exception;

public class RoomOccupiedException extends RuntimeException {
  public RoomOccupiedException(String message) {
    super(message);
  }
}
