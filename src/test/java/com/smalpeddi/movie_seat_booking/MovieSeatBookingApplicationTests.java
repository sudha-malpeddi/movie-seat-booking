package com.smalpeddi.movie_seat_booking;

import com.smalpeddi.movie_seat_booking.model.Seat;
import com.smalpeddi.movie_seat_booking.repository.SeatRepository;
import com.smalpeddi.movie_seat_booking.service.SeatBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = MovieSeatBookingApplication.class)
class MovieSeatBookingApplicationTests {

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private SeatBookingService seatBookingService;

	@BeforeEach
	public void setUp() {
		// Ensure there's at least one seat to test
		Seat seat = new Seat(false);  // booked = false
		seatRepository.save(seat);
	}

	@Test
	public void testOptimisticLocking() throws InterruptedException {
		Long seatId = 1L;

		// Simulate two concurrent threads booking the same seat
		Thread user1 = new Thread(() -> {
			try {
				seatBookingService.bookSeatOptimistic(seatId);
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName()+" failed to book: " + e.getMessage());
			}
		});

		Thread user2 = new Thread(() -> {
			try {
				seatBookingService.bookSeatOptimistic(seatId);
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName()+" failed to book: " + e.getMessage());
			}
		});

		user1.start();
		user2.start();

		user1.join();
		user2.join();

		// Assert seat is booked
		Seat bookedSeat = seatRepository.findById(seatId).orElseThrow();
		assert bookedSeat.getBooked();
	}

}
