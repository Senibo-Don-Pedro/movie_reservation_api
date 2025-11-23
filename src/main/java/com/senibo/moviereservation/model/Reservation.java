package com.senibo.moviereservation.model;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


/**
 * Represents the "Order Header" for a booking transaction.
 *
 * Key Logic:
 * - Aggregates the financial total.
 * - Manages the state of the transaction (PENDING -> PAID).
 * - A reservation is not valid without at least one {@link Ticket}.
 */
@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Reservation extends BaseEntity {

  @Column(nullable = false)
  private BigDecimal totalPrice;

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime bookingDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentStatus paymentStatus;

  @Builder.Default
  @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
  private List<Ticket> tickets = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "show_time_id")
  private ShowTime showTime;
}
