package com.senibo.moviereservation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senibo.moviereservation.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID>{
  List<Reservation> findByUserId(UUID userId);
}