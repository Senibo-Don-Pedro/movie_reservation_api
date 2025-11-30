package com.senibo.moviereservation.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senibo.moviereservation.dto.ReservationRequestDTO;
import com.senibo.moviereservation.exception.InsufficientSeatsException;
import com.senibo.moviereservation.exception.NotFoundException;
import com.senibo.moviereservation.exception.RoomOccupiedException;
import com.senibo.moviereservation.model.PaymentStatus;
import com.senibo.moviereservation.model.Reservation;
import com.senibo.moviereservation.model.Seat;
import com.senibo.moviereservation.model.ShowTime;
import com.senibo.moviereservation.model.Ticket;
import com.senibo.moviereservation.model.User;
import com.senibo.moviereservation.repository.ReservationRepository;
import com.senibo.moviereservation.repository.SeatRepository;
import com.senibo.moviereservation.repository.ShowTimeRepository;
import com.senibo.moviereservation.repository.TicketRepository;
import com.senibo.moviereservation.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final TicketRepository ticketRepository;
  private final ShowTimeRepository showTimeRepository;
  private final SeatRepository seatRepository;
  private final UserRepository userRepository;

  private final static int SEAT_PRICE = 10;

  public Reservation createReservation(ReservationRequestDTO request) {

    User user = userRepository.findById(request.userId())
        .orElseThrow(() -> new NotFoundException("User not found"));

    ShowTime showTime = showTimeRepository.findById(request.showtimeId())
        .orElseThrow(() -> new NotFoundException("Showtime not found"));

    List<Seat> seats = seatRepository.findAllById(request.seatIds());

    if (seats.size() < request.seatIds().size()) {
      throw new InsufficientSeatsException("Insufficient seats");
    }

    boolean roomOccupied = ticketRepository.existsByShowTimeAndSeats(showTime.getId(), request.seatIds());

    if (roomOccupied) {
      throw new RoomOccupiedException("Room is occupied");
    }

    int totalPrice = SEAT_PRICE * seats.size();

    Reservation reservation = Reservation.builder()
        .totalPrice(BigDecimal.valueOf(totalPrice))
        .bookingDate(LocalDateTime.now())
        .paymentStatus(PaymentStatus.PENDING)
        .user(user)
        .showTime(showTime)
        .tickets(new ArrayList<>())
        .build();

    // 4. Build Tickets and link them bi-directionally
    // We Map the seats to tickets and add them to the reservation's list
    List<Ticket> tickets = seats.stream().map(seat -> Ticket.builder()
        .reservation(reservation) // Link Child to Parent
        .seat(seat)
        .build()).collect(Collectors.toList());

    // Link Parent to Children (CRITICAL for Cascade)
    reservation.setTickets(tickets);

    // 5. Save Parent (Cascade handles the Children automatically)
    return reservationRepository.save(reservation);

  }
}
