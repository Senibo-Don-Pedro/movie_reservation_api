package com.senibo.moviereservation.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


/**
 * Represents the "Metadata" of a film.
 * This entity holds static details about a movie.
 *
 * Key Logic:
 * - Does not contain schedule information (see {@link ShowTime}).
 * - durationInMinutes is critical for scheduling logic to prevent overlapping shows.
 */

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Movie extends BaseEntity {

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Integer durationInMinutes;

  @Column
  private String imageUrl;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MovieCategory movieCategory;

  @Builder.Default
  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  @JsonIgnore // <--- ADD THIS
  private List<ShowTime> showTimes = new ArrayList<>();
}
