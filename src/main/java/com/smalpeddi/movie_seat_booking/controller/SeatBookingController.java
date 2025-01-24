package com.smalpeddi.movie_seat_booking.controller;

import com.smalpeddi.movie_seat_booking.service.SeatBookingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class SeatBookingController {
    private final SeatBookingService seatBookingService;

    public SeatBookingController(SeatBookingService seatBookingService) {
        this.seatBookingService = seatBookingService;
    }

    @PostMapping("/{id}/book/optimistic")
    public String bookSeatOptimistic(@PathVariable Long id) {
        seatBookingService.bookSeatOptimistic(id);
        return "Seat booked successfully using optimistic locking!";
    }
}
