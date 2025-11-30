package com.senibo.moviereservation.dto;

import com.senibo.moviereservation.model.MovieCategory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieRequestDTO(
    @NotBlank(message = "Title is required") String title,

    @NotBlank(message = "Description is required") String description,

    @NotNull(message = "Movie category must be provided") MovieCategory movieCategory,

    @NotNull(message = "Duration is required") @Min(value = 1, message = "Duration must be at least 1 minute") Integer durationInMinutes,

    String imageURL) {

}
