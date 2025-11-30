package com.senibo.moviereservation.service;

import com.senibo.moviereservation.dto.MovieRequestDTO;
import com.senibo.moviereservation.model.Movie;
import com.senibo.moviereservation.model.MovieCategory;
import com.senibo.moviereservation.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository; // The "Fake" Database

    @InjectMocks
    private MovieService movieService; // The Service we are testing

    @Test
    void addMovie_ShouldReturnMovie_WhenRequestIsValid() {
        // 1. ARRANGE (Prepare the data)
        MovieRequestDTO request = new MovieRequestDTO(
            "Inception",
            "Dreams within dreams",
            MovieCategory.SCI_FI,
            148,
            "http://image.url"
        );

        // We create the Movie object that we EXPECT the repo to return
        Movie expectedMovie = Movie.builder()
            .title("Inception")
            .description("Dreams within dreams")
            .movieCategory(MovieCategory.SCI_FI)
            .durationInMinutes(148)
            .build();

        // --- MOCKITO MAGIC START ---
        // We tell the fake repository:
        // "When anyone calls .save() with ANY Movie object, strictly return 'expectedMovie'"
        when(movieRepository.save(any(Movie.class))).thenReturn(expectedMovie);
        // --- MOCKITO MAGIC END ---

        // 2. ACT (Run the actual code)
        Movie actualMovie = movieService.addMovie(request);

        // 3. ASSERT (Check the results)
        assertNotNull(actualMovie, "The returned movie should not be null");
        assertEquals("Inception", actualMovie.getTitle(), "The title should match");
        assertEquals(148, actualMovie.getDurationInMinutes(), "The duration should match");

        // VERIFY (Did the Service do its job?)
        // Check that repository.save() was called exactly 1 time
        verify(movieRepository, times(1)).save(any(Movie.class));
    }
}