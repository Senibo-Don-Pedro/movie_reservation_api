package com.senibo.moviereservation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.CascadeType;


/**
 * Represents a physical auditorium or screen within the cinema.
 *
 * Key Logic:
 * - Acts as the parent constraint for {@link Seat} generation.
 * - Total seats are cached here for performance, but actual availability
 * must be calculated via {@link ShowTime} and {@link Ticket} logic.
 */

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Room extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer totalSeats;

  // One room, many seats
  @Builder.Default
  @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Seat> seats = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
  private List<ShowTime> showTimes = new ArrayList<>();
}
