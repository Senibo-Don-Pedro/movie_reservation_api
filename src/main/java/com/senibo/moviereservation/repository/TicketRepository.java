package com.senibo.moviereservation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senibo.moviereservation.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

  // JPQL Magic: "Check if ANY ticket exists for this showtime and these seats"
  @Query("""
          SELECT COUNT(t) > 0
          FROM Ticket t
          WHERE t.reservation.showTime.id = :showTimeId
          AND t.seat.id IN :seatIds
      """)
  boolean existsByShowTimeAndSeats(
      @Param("showTimeId") UUID showTimeId,
      @Param("seatIds") List<UUID> seatIds);
}
