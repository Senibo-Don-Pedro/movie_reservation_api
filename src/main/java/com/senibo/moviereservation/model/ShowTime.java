package com.senibo.moviereservation.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "show_times")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class ShowTime extends BaseEntity {

  @Column(nullable = false)
  private LocalDateTime movieStartTime;

  @ManyToOne // Logic: Many showtimes can act on ONE movie
  @JoinColumn(name = "movie_id", nullable = false)
  private Movie movie;

  @ManyToOne // Logic: Many showtimes can happen in ONE room
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;

}