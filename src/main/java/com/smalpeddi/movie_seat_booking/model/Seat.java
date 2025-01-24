package com.smalpeddi.movie_seat_booking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean booked;

    @Version
    private int version;

    public Seat() {
    }

    public Seat(boolean booked) {
        this.booked = booked;
    }

    public Long getId() {
        return id;
    }

    public boolean getBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", isBooked=" + booked +
                ", version=" + version +
                '}';
    }
}
