package com.senibo.moviereservation.model;

import java.util.ArrayList;
import java.util.List;

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
  private List<ShowTime> showTimes = new ArrayList<>();
}
