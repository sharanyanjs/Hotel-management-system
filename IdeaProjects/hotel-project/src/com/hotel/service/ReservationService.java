package com.hotel.service;

import com.hotel.model.Customer;
import com.hotel.model.IRoom;
import java.time.LocalDateTime;
import com.hotel.model.Reservation;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {
    // Static reference (Singleton pattern)
    private static final ReservationService INSTANCE = new ReservationService();

    // Collections to store data
    private final Map<String, IRoom> rooms; // roomNumber -> Room
    private final Set<Reservation> reservations;

    // Private constructor
    private ReservationService() {
        rooms = new HashMap<>();
        reservations = new HashSet<>();
    }

    // Get singleton instance
    public static ReservationService getInstance() {
        return INSTANCE;
    }

    // 1. Add a room
    public void addRoom(IRoom room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        rooms.put(room.getRoomNumber(), room);
        System.out.println("Room added: " + room.getRoomNumber());
    }

    // 2. Get a room by room ID
    public IRoom getARoom(String roomId) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Room ID cannot be empty");
        }
        return rooms.get(roomId);
    }

    // 3. Reserve a room
    public Reservation reserveARoom(Customer customer, IRoom room,
                                    LocalDate checkInDate, LocalDate checkOutDate) {
        // Validate inputs
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        // Check if room exists
        if (!rooms.containsKey(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room does not exist: " + room.getRoomNumber());
        }

        // Check if room is available
        if (!isRoomAvailable(room, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Room " + room.getRoomNumber() +
                    " is not available from " + checkInDate + " to " + checkOutDate);
        }

        // Create and store reservation
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);

        System.out.println("Reservation created successfully!");
        return reservation;
    }

    // 4. Find available rooms for given dates
    public Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        // Validate dates
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        List<IRoom> availableRooms = new ArrayList<>();

        for (IRoom room : rooms.values()) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    // 5. Get all reservations for a customer
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        List<Reservation> customerReservations = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    // 6. Print all reservations
    public void printAllReservation() {
        if (reservations.isEmpty()) {
            System.out.println("\n=== No reservations found ===");
            return;
        }

        System.out.println("\n=== ALL RESERVATIONS ===");
        System.out.println("Total reservations: " + reservations.size());
        System.out.println("------------------------");

        int count = 1;
        for (Reservation reservation : reservations) {
            System.out.println("Reservation #" + count);
            System.out.println(reservation);
            System.out.println("------------------------");
            count++;
        }
    }

    // 7. Get all rooms (optional helper method)
    public Collection<IRoom> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }

    // 8. Check if room exists
    public boolean roomExists(String roomId) {
        return rooms.containsKey(roomId);
    }

    // 9. Get total number of rooms
    public int getTotalRooms() {
        return rooms.size();
    }

    // 10. Get total number of reservations
    public int getTotalReservations() {
        return reservations.size();
    }

    // Helper method: Check room availability
    private boolean isRoomAvailable(IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        for (Reservation reservation : reservations) {
            // If this reservation is for the same room
            if (reservation.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                // Check if dates overlap
                if (datesOverlap(
                        reservation.getCheckInDate(), reservation.getCheckOutDate(),
                        checkInDate, checkOutDate)) {
                    return false; // Room is booked
                }
            }
        }
        return true; // Room is available
    }

    // Helper method: Check if two date ranges overlap
    private boolean datesOverlap(LocalDate start1, LocalDate end1,
                                 LocalDate start2, LocalDate end2) {
        // Two date ranges overlap if:
        // range1 starts before range2 ends AND range1 ends after range2 starts

        // Note: end dates are exclusive (check-out day is not occupied)
        return start1.isBefore(end2) && end1.isAfter(start2);
    }
}