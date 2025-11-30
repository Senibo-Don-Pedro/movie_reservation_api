package com.senibo.moviereservation.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senibo.moviereservation.model.ShowTime;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, UUID> {
  List<ShowTime> findByMovieId(UUID movieID);

  @Query(
    """
    SELECT COUNT(s) > 0 
    FROM ShowTime s 
    WHERE s.room.id = :roomId
    AND s.movieStartTime < :newEndTime
    AND s.movieEndTime > :newStartTime

    """
  )
  boolean existsByRoomIdAndTimeOverlap(
      @Param("roomId") UUID roomId,
      @Param("newStartTime") LocalDateTime newStartTime,
      @Param("newEndTime") LocalDateTime newEndTime);
}