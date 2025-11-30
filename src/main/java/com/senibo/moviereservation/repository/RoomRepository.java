package com.senibo.moviereservation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senibo.moviereservation.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID>{
  boolean existsByName(String name);
}
