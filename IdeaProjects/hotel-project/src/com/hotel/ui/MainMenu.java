package com.hotel.ui;

import com.hotel.api.HotelResource;
import com.hotel.model.Customer;
import com.hotel.model.IRoom;
import com.hotel.model.Reservation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class MainMenu {
    private final HotelResource hotelResource;
    private final Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public MainMenu() {
        this.hotelResource = HotelResource.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.print("Please select an option (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    // Will be handled by HotelApplication
                    System.out.println("Switching to Admin Menu...");
                    return; // Return to HotelApplication
                case "5":
                    System.out.println("Thank you for using our Hotel Reservation System!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
        }
        scanner.close();
    }

    private void findAndReserveRoom() {
        System.out.println("\n=== FIND AND RESERVE A ROOM ===");

        // Check if customer has account
        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim().toLowerCase();

        Customer customer = hotelResource.getCustomer(email);
        if (customer == null) {
            System.out.println("No account found with email: " + email);
            System.out.println("Please create an account first.");
            return;
        }

        // Get dates
        LocalDate checkIn = getDateInput("Enter check-in date (MM/DD/YYYY): ");
        LocalDate checkOut = getDateInput("Enter check-out date (MM/DD/YYYY): ");

        if (checkIn == null || checkOut == null) {
            return;
        }

        // Find available rooms
        System.out.println("\nSearching for available rooms...");
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for those dates.");

            // Suggest alternative dates (7 days later)
            LocalDate altCheckIn = checkIn.plusDays(7);
            LocalDate altCheckOut = checkOut.plusDays(7);
            System.out.println("Would you like to see rooms available from " +
                    altCheckIn.format(DATE_FORMATTER) + " to " +
                    altCheckOut.format(DATE_FORMATTER) + "? (yes/no)");

            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("yes") || response.equals("y")) {
                availableRooms = hotelResource.findARoom(altCheckIn, altCheckOut);
                checkIn = altCheckIn;
                checkOut = altCheckOut;
            } else {
                return;
            }
        }

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for alternative dates either.");
            return;
        }

        // Display available rooms
        System.out.println("\n=== AVAILABLE ROOMS ===");
        int count = 1;
        for (IRoom room : availableRooms) {
            String freeMarker = room.isFree() ? " (FREE!)" : "";
            System.out.println(count + ". Room " + room.getRoomNumber() +
                    " - $" + room.getRoomPrice() + "/night" +
                    " [" + room.getRoomType() + "]" + freeMarker);
            count++;
        }

        // Select a room
        System.out.print("\nEnter room number to book (or '0' to cancel): ");
        String roomNumber = scanner.nextLine().trim();

        if (roomNumber.equals("0")) {
            System.out.println("Booking cancelled.");
            return;
        }

        // Find the selected room
        IRoom selectedRoom = null;
        for (IRoom room : availableRooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room number.");
            return;
        }

        // Confirm booking
        System.out.println("\n=== BOOKING SUMMARY ===");
        System.out.println("Room: " + selectedRoom.getRoomNumber());
        System.out.println("Type: " + selectedRoom.getRoomType());
        System.out.println("Price: $" + selectedRoom.getRoomPrice() + " per night");
        System.out.println("Dates: " + checkIn.format(DATE_FORMATTER) + " to " +
                checkOut.format(DATE_FORMATTER));

        int nights = (int) (checkOut.toEpochDay() - checkIn.toEpochDay());
        double total = selectedRoom.getRoomPrice() * nights;
        System.out.println("Total: $" + total + " for " + nights + " night(s)");

        System.out.print("\nConfirm booking? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes") || confirm.equals("y")) {
            try {
                Reservation reservation = hotelResource.bookARoom(email, selectedRoom, checkIn, checkOut);
                System.out.println("\n✓ Booking confirmed!");
                System.out.println("Reservation ID: " + reservation.hashCode());
            } catch (Exception e) {
                System.out.println("\n✗ Booking failed: " + e.getMessage());
            }
        } else {
            System.out.println("Booking cancelled.");
        }
    }

    private void seeMyReservations() {
        System.out.println("\n=== MY RESERVATIONS ===");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim().toLowerCase();

        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);

        if (reservations.isEmpty()) {
            System.out.println("No reservations found for " + email);
        } else {
            System.out.println("You have " + reservations.size() + " reservation(s):\n");
            int count = 1;
            for (Reservation reservation : reservations) {
                System.out.println("Reservation #" + count);
                System.out.println(reservation);
                System.out.println("-------------------");
                count++;
            }
        }
    }

    private void createAccount() {
        System.out.println("\n=== CREATE ACCOUNT ===");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim().toLowerCase();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("\n✓ Account created successfully!");
            System.out.println("Welcome, " + firstName + "!");
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ Account creation failed: " + e.getMessage());
        }
    }

    private LocalDate getDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String dateStr = scanner.nextLine().trim();

            try {
                return LocalDate.parse(dateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use MM/DD/YYYY (e.g., 12/25/2024)");
                System.out.print("Try again? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("yes") && !response.equals("y")) {
                    return null;
                }
            }
        }
    }
}