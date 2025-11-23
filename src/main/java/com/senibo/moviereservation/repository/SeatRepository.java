package com.senibo.moviereservation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senibo.moviereservation.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {

  List<Seat> findByRoomIdOrderBySeatRowAscSeatNumberAsc(UUID roomId);
}