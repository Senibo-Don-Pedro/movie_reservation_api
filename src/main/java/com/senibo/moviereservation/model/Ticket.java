package com.senibo.moviereservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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


/**
 * The "Join Table" between a Reservation and a physical Seat.
 *
 * Key Logic:
 * - Locks a specific {@link Seat} for a specific {@link Reservation}.
 * - Preventing double-booking happens here (Unique Constraint on ShowTime + Seat).
 */
@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Ticket extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "reservation_id")
  @JsonIgnore
  private Reservation reservation;

  @ManyToOne
  @JoinColumn(name = "seat_id")
  private Seat seat;
}
