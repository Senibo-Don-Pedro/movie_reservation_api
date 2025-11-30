package com.senibo.moviereservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;



/**
 * Represents a specific physical seat in a specific Room.
 *
 * ARCHITECTURAL NOTE:
 * This entity is STATELESS. It does not know if it is reserved.
 * Reservation status is derived by checking the existence of a {@link Ticket}
 * for a specific {@link ShowTime} linking to this seat.
 */
@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Seat extends BaseEntity {

  @Column(nullable = false)
  private Character seatRow;

  @Column(nullable = false)
  private Integer seatNumber;

  // many rooms, one seat
  @ManyToOne
  @JoinColumn(name = "room_id") // foreign key
  @JsonIgnore // <--- ADD THIS
  private Room room;

  @OneToMany(mappedBy = "seat")
  private List<Ticket> tickets;
}
