package com.hotel.api;

import com.hotel.model.Customer;
import com.hotel.model.IRoom;
import com.hotel.model.Reservation;
import com.hotel.service.CustomerService;
import com.hotel.service.ReservationService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class HotelResource {
    // Static reference (Facade pattern)
    private static final HotelResource INSTANCE = new HotelResource();

    // Service dependencies
    private final CustomerService customerService;
    private final ReservationService reservationService;

    private HotelResource() {
        this.customerService = CustomerService.getInstance();
        this.reservationService = ReservationService.getInstance();
    }

    public static HotelResource getInstance() {
        return INSTANCE;
    }

    // 1. Get customer by email
    public Customer getCustomer(String email) {
        Customer customer = customerService.getCustomer(email);
        if (customer == null) {
            System.out.println("No customer found with email: " + email);
        }
        return customer;
    }

    // 2. Create a new customer account
    public void createACustomer(String email, String firstName, String lastName) {
        try {
            customerService.addCustomer(email, firstName, lastName);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            throw e; // Re-throw for the calling code to handle
        }
    }

    // 3. Get room by room number
    public IRoom getRoom(String roomNumber) {
        IRoom room = reservationService.getARoom(roomNumber);
        if (room == null) {
            System.out.println("No room found with number: " + roomNumber);
        }
        return room;
    }

    // 4. Book a room
    public Reservation bookARoom(String customerEmail, IRoom room,
                                 LocalDate checkInDate, LocalDate checkOutDate) {
        // Get customer
        Customer customer = getCustomer(customerEmail);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found: " + customerEmail);
        }

        // Check if room exists
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }

        // Book the room through service
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    // 5. Get customer's reservations
    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        if (customer == null) {
            System.out.println("Customer not found: " + customerEmail);
            return new ArrayList<>(); // Return empty list
        }
        return reservationService.getCustomersReservation(customer);
    }

    // 6. Find available rooms
    public Collection<IRoom> findARoom(LocalDate checkIn, LocalDate checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}