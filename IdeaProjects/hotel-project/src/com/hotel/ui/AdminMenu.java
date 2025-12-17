package com.hotel.ui;

import com.hotel.api.AdminResource;
import java.util.Scanner;

public class AdminMenu {
    private final AdminResource adminResource;
    private final Scanner scanner;

    public AdminMenu() {
        this.adminResource = AdminResource.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Please select an option (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addRoom();
                    break;
                case "5":
                    System.out.println("Returning to Main Menu...");
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
        }
    }

    private void seeAllCustomers() {
        adminResource.displayAllCustomers();
    }

    private void seeAllRooms() {
        adminResource.displayAllRooms();
    }

    private void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private void addRoom() {
        adminResource.addRoomWithInput(scanner);
    }
}