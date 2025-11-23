package com.senibo.moviereservation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


/**
 * The "Event" entity. Represents a specific Movie playing in a specific Room at a specific Time.
 *
 * Key Logic:
 * - Acts as the bridge between Metadata (Movie) and Physical Space (Room).
 * - This ID is what users actually book against.
 */
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

  @Builder.Default
  @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
  private List<Reservation> reservations = new ArrayList<>();

  @ManyToOne // Logic: Many showtimes can act on ONE movie
  @JoinColumn(name = "movie_id", nullable = false)
  private Movie movie;

  @ManyToOne // Logic: Many showtimes can happen in ONE room
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;



}