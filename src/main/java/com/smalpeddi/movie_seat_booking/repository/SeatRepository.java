package com.smalpeddi.movie_seat_booking.repository;

import com.smalpeddi.movie_seat_booking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
