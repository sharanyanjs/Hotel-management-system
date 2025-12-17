package com.hotel.api;

import com.hotel.model.Customer;
import com.hotel.model.IRoom;
import com.hotel.model.Room;
import com.hotel.model.RoomType;
import com.hotel.service.CustomerService;
import com.hotel.service.ReservationService;
import java.util.Collection;
import java.util.Scanner;

public class AdminResource {
    // Singleton instance
    private static final AdminResource INSTANCE = new AdminResource();

    // Service dependencies
    private final CustomerService customerService;
    private final ReservationService reservationService;

    private AdminResource() {
        this.customerService = CustomerService.getInstance();
        this.reservationService = ReservationService.getInstance();
    }

    public static AdminResource getInstance() {
        return INSTANCE;
    }

    // 1. Get a customer by email
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    // 2. Add a room
    public void addRoom(IRoom room) {
        reservationService.addRoom(room);
    }

    // 3. Add a room with user input
    public void addRoomWithInput(Scanner scanner) {
        try {
            System.out.println("\n=== ADD A NEW ROOM ===");

            // Get room number
            System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine().trim();

            // Check if room already exists
            if (reservationService.getARoom(roomNumber) != null) {
                System.out.println("Room " + roomNumber + " already exists!");
                return;
            }

            // Get price
            System.out.print("Enter price per night: $");
            double price;
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Using default $0.00 (Free Room)");
                price = 0.0;
            }

            // Get room type
            System.out.println("Room types:");
            System.out.println("1. SINGLE");
            System.out.println("2. DOUBLE");
            System.out.print("Select room type (1 or 2): ");
            String typeChoice = scanner.nextLine().trim();

            RoomType roomType;
            if (typeChoice.equals("1")) {
                roomType = RoomType.SINGLE;
            } else if (typeChoice.equals("2")) {
                roomType = RoomType.DOUBLE;
            } else {
                System.out.println("Invalid choice. Defaulting to SINGLE.");
                roomType = RoomType.SINGLE;
            }

            // Create and add room
            IRoom room;
            if (price == 0.0) {
                room = new com.hotel.model.FreeRoom(roomNumber, roomType);
                System.out.println("Free room created!");
            } else {
                room = new Room(roomNumber, price, roomType);
                System.out.println("Paid room created!");
            }

            addRoom(room);
            System.out.println("✓ Room " + roomNumber + " added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }

    // 4. Get all rooms
    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    // 5. Get all customers
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // 6. Display all reservations
    public void displayAllReservations() {
        reservationService.printAllReservation();
    }

    // 7. Display all rooms
    public void displayAllRooms() {
        Collection<IRoom> rooms = getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("\nNo rooms in the system.");
            return;
        }

        System.out.println("\n=== ALL ROOMS ===");
        System.out.println("Total rooms: " + rooms.size());
        System.out.println("-----------------");

        int count = 1;
        for (IRoom room : rooms) {
            String freeMarker = room.isFree() ? " (FREE)" : "";
            System.out.println(count + ". Room " + room.getRoomNumber() +
                    " - $" + room.getRoomPrice() + "/night" +
                    " [" + room.getRoomType() + "]" + freeMarker);
            count++;
        }
    }

    // 8. Display all customers
    public void displayAllCustomers() {
        customerService.printAllCustomers();
    }
}