package com.senibo.moviereservation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class User extends BaseEntity {
  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  // For JWT authentication - can be null for OAuth-only users
  @Column
  @JsonIgnore
  private String password;

  @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
  private List<Reservation> reservations;

}
