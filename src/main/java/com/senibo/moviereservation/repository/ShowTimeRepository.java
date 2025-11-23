package com.senibo.moviereservation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senibo.moviereservation.model.ShowTime;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, UUID> {
  List<ShowTime> findByMovieId(UUID movieID);
}