package com.senibo.moviereservation.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.senibo.moviereservation.dto.ApiErrorResponse;

/**
 * Global exception handler for the MovieReservation application.
 * 
 * This class centralizes the handling of all custom and framework-level exceptions,
 * ensuring consistent error response formatting across all controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors thrown by @Valid (MethodArgumentNotValidException).
     *
     * @param ex The thrown MethodArgumentNotValidException
     * @return A structured ApiErrorResponse with status 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {

        // Extract all validation error messages and map them to: "field: message"
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                "Validation Failed",
                errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles authentication failures (e.g. wrong password).
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentials(BadCredentialsException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                "Authentication Failed",
                List.of("Invalid email or password"));

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); // Returns 401
    }

    /**
     * Handles custom NotFoundException—used when an entity or resource is not found.
     *
     * @param ex The thrown NotFoundException
     * @return A structured ApiErrorResponse with status 404
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                List.of("Resource not found"));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles RoomOccupiedException—thrown when trying to book a room that is already taken.
     *
     * @param ex The thrown RoomOccupiedException
     * @return A structured ApiErrorResponse with status 409 (Conflict)
     */
    @ExceptionHandler(RoomOccupiedException.class)
    public ResponseEntity<ApiErrorResponse> handleRoomOccupied(RoomOccupiedException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                List.of("Room is currently occupied"));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
    * Handles cases where a resource (e.g., user) already exists.
    */
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleAlreadyExists(AlreadyExistsException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                ex.getMessage(),
                null);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT); // Returns 409
    }

    /**
     * Fallback exception handler for any unhandled errors.
     * Prevents leaking internal stack traces to the client.
     *
     * @param ex The thrown exception
     * @return A generic error response with status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                false,
                "Internal server error",
                List.of("An unexpected error occurred"));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
