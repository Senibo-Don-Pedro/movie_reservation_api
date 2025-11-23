package com.senibo.moviereservation.model;

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
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Seat extends BaseEntity {

  @Column(nullable = false)
  private Character row;

  @Column(nullable = false)
  private Integer seatNumber;

  // many rooms, one seat
  @ManyToOne
  @JoinColumn(name = "room_id") // foreign key
  private Room room;
}
