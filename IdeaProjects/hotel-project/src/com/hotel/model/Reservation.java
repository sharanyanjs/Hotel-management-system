package com.hotel.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        // Validate dates
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return customer.equals(that.customer) &&
                room.equals(that.room) &&
                checkInDate.equals(that.checkInDate) &&
                checkOutDate.equals(that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }

    @Override
    public String toString() {
        return "Reservation {" +
                "\n  Customer: " + customer.getFirstName() + " " + customer.getLastName() +
                "\n  Email: " + customer.getEmail() +
                "\n  Room: " + room.getRoomNumber() +
                " (" + room.getRoomType() + ")" +
                "\n  Price: $" + room.getRoomPrice() +
                "\n  Dates: " + checkInDate + " to " + checkOutDate +
                "\n}";
    }
}