package com.senibo.moviereservation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senibo.moviereservation.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

}
