package com.senibo.moviereservation.service;

import com.senibo.moviereservation.dto.RoomRequestDTO;
import com.senibo.moviereservation.exception.RoomOccupiedException;
import com.senibo.moviereservation.model.Room;
import com.senibo.moviereservation.model.Seat;
import com.senibo.moviereservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public Room createRoom(RoomRequestDTO request) {

        if (roomRepository.existsByName(request.name())) {
            throw new RoomOccupiedException("Room name '" + request.name() + "' already exists");
            // Or create a new DuplicateResourceException if you prefer
        }
        // 1. Create the Room Shell
        Room room = Room.builder()
                .name(request.name())
                .totalSeats(request.rowCount() * request.seatsPerRow())
                .seats(new ArrayList<>()) // Initialize list
                .build();

        // 2. Generate Seats (Grid Layout)
        List<Seat> seats = new ArrayList<>();

        for (int r = 0; r < request.rowCount(); r++) {
            // Convert 0 -> 'A', 1 -> 'B', etc.
            char rowChar = (char) ('A' + r);

            for (int n = 1; n <= request.seatsPerRow(); n++) {
                Seat seat = Seat.builder()
                        .seatRow(rowChar) // Make sure your Entity uses 'seatRow' not 'row'
                        .seatNumber(n)
                        .room(room) // Link Child -> Parent
                        .build();
                seats.add(seat);
            }
        }

        // 3. Link Parent -> Children
        room.setSeats(seats);

        // 4. Save (Cascade will save all 50-100 seats automatically)
        return roomRepository.save(room);
    }
}