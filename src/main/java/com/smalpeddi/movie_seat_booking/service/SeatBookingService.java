package com.smalpeddi.movie_seat_booking.service;

import com.smalpeddi.movie_seat_booking.model.Seat;
import com.smalpeddi.movie_seat_booking.repository.SeatRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeatBookingService {
    private final SeatRepository seatRepository;

    public SeatBookingService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void bookSeatOptimistic(Long seatId) {
        try {
            Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found"));

            System.out.println(Thread.currentThread().getName() + " fetched the seat with version " + seat.getVersion());

            if (seat.getBooked()) {
                throw new RuntimeException("Seat already booked");
            }
            seat.setBooked(true);
            seatRepository.save(seat);
        } catch (OptimisticLockException e) {
            // Handle conflict, e.g., log the issue or notify the user
            System.err.println("Optimistic Locking conflict for seatId: " + seatId);
            throw new RuntimeException("Unable to book seat due to concurrency conflict, please try again later");
        }
    }

    public void bookSeatPessimistic(Long seatId) {
        // Lock the seat for writing
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found"));

        System.out.println(Thread.currentThread().getName() + " used pessimistic locking to fetch the seat with version " + seat.getVersion());

        // Check if seat is already booked
        if (seat.getBooked()) {
            throw new RuntimeException("Seat is already booked");
        }

        // Book the seat
        seat.setBooked(true);
        seatRepository.save(seat);
    }
}
