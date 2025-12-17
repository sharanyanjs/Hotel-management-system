import com.hotel.model.*;
import com.hotel.service.CustomerService;
import com.hotel.service.ReservationService;
import com.hotel.api.HotelResource;
import java.time.LocalDate;
import java.util.Collection;

public class Driver {
    public static void main(String[] args) {
        System.out.println("=== HOTEL RESERVATION SYSTEM - COMPLETE TEST ===\n");

        try {
            // PART 1: BASIC MODEL TESTS
            System.out.println("PART 1: TESTING MODEL CLASSES");
            System.out.println("===============================\n");

            // Test Customer email validation
            System.out.println("1. CUSTOMER EMAIL VALIDATION");
            System.out.println("-----------------------------");
            testCustomerEmailValidation();

            // Test equals() and hashCode()
            System.out.println("\n2. EQUALS() AND HASHCODE() TESTS");
            System.out.println("----------------------------------");
            testEqualsAndHashCode();

            // PART 2: SERVICE LAYER TESTS
            System.out.println("\n\nPART 2: TESTING SERVICE LAYER");
            System.out.println("==============================\n");

            // Test ReservationService
            System.out.println("3. RESERVATION SERVICE TESTS");
            System.out.println("-----------------------------");
            testReservationService();

            // Test CustomerService
            System.out.println("\n4. CUSTOMER SERVICE TESTS");
            System.out.println("--------------------------");
            testCustomerService();

            // PART 3: API LAYER TESTS
            System.out.println("\n\nPART 3: TESTING API LAYER (HOTEL RESOURCE)");
            System.out.println("============================================\n");

            System.out.println("5. HOTEL RESOURCE TESTS");
            System.out.println("------------------------");
            testHotelResource();

            // PART 4: INTEGRATION TEST
            System.out.println("\n\nPART 4: INTEGRATION TEST");
            System.out.println("=========================\n");

            System.out.println("6. COMPLETE FLOW TEST");
            System.out.println("----------------------");
            testCompleteFlow();

            System.out.println("\n=== ALL TESTS COMPLETED SUCCESSFULLY ===");

        } catch (Exception e) {
            System.err.println("\n!!! TEST FAILED !!!");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void testEnhancedRoomFeatures() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TESTING ENHANCED ROOM FEATURES");
        System.out.println("=".repeat(50));

        // Create enhanced rooms with different features
        Room premiumSuite = new Room("301", 299.99, RoomType.DOUBLE,
                3, true, true); // floor 3, balcony, sea view

        Room standardDouble = new Room("202", 149.99, RoomType.DOUBLE,
                2, false, true); // floor 2, sea view, no balcony

        Room basicSingle = new Room("101", 89.99, RoomType.SINGLE,
                1, false, false); // floor 1, basic

        FreeRoom freeDeluxe = new FreeRoom("401", RoomType.DOUBLE,
                4, true, true); // top floor free room

        System.out.println("\n1. PREMIUM SUITE:");
        System.out.println(premiumSuite);

        System.out.println("\n2. STANDARD DOUBLE WITH SEA VIEW:");
        System.out.println(standardDouble);

        System.out.println("\n3. BASIC SINGLE:");
        System.out.println(basicSingle.toSummaryString());

        System.out.println("\n4. FREE DELUXE ROOM:");
        System.out.println(freeDeluxe);

        System.out.println("\n5. ROOM CATEGORIES:");
        System.out.println("Premium Suite category: " + premiumSuite.getRoomCategory());
        System.out.println("Standard Double category: " + standardDouble.getRoomCategory());
        System.out.println("Basic Single category: " + basicSingle.getRoomCategory());

        System.out.println("\n6. ROOM RATINGS (1-5 stars):");
        System.out.println("Premium Suite: " + "⭐".repeat(premiumSuite.getRoomRating()));
        System.out.println("Standard Double: " + "⭐".repeat(standardDouble.getRoomRating()));
        System.out.println("Basic Single: " + "⭐".repeat(basicSingle.getRoomRating()));

        System.out.println("\n7. AMENITIES CHECK:");
        System.out.println("Premium Suite amenities (" + premiumSuite.getAmenities().size() + "):");
        premiumSuite.getAmenities().forEach(amenity -> System.out.println("  • " + amenity));

        System.out.println("\n8. CLEANING STATUS:");
        System.out.println("Premium Suite needs cleaning? " + premiumSuite.needsCleaning());
        System.out.println("Cleaning priority: " + premiumSuite.getCleaningPriority());

        System.out.println("\n9. ROOM STATUS MANAGEMENT:");
        premiumSuite.setStatus(Room.RoomStatus.MAINTENANCE);
        System.out.println("Premium suite status: " + premiumSuite.getStatus().getDescription());

        premiumSuite.markAsCleaned();
        System.out.println("After cleaning: " + premiumSuite.getStatus().getDescription());

        System.out.println("\n10. ROOM QUICK INFO:");
        System.out.println("Quick info: " + premiumSuite.getQuickInfo());
    }
    private static void testCustomerEmailValidation() {
        // Valid emails
        System.out.println("Valid emails:");
        try {
            Customer c1 = new Customer("John", "Doe", "john@email.com");
            System.out.println("  ✓ " + c1.getEmail());
        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }

        try {
            Customer c2 = new Customer("Jane", "Smith", "jane.smith+work@company.co.uk");
            System.out.println("  ✓ " + c2.getEmail());
        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }

        // Invalid emails
        System.out.println("\nInvalid emails:");
        String[] invalidEmails = {"invalid", "john@", "@email.com", "john email@test.com"};
        for (String email : invalidEmails) {
            try {
                new Customer("Test", "User", email);
                System.out.println("  ✗ " + email + " (should have failed)");
            } catch (IllegalArgumentException e) {
                System.out.println("  ✓ " + email + " correctly rejected");
            }
        }
    }

    private static void testEqualsAndHashCode() {
        // Customer equality test
        Customer c1 = new Customer("John", "Doe", "john@email.com");
        Customer c2 = new Customer("John", "Doe", "john@email.com"); // Same email
        Customer c3 = new Customer("Jane", "Smith", "jane@email.com"); // Different email

        System.out.println("Customer equality:");
        System.out.println("  c1.equals(c2): " + c1.equals(c2) + " (expected: true)");
        System.out.println("  c1.equals(c3): " + c1.equals(c3) + " (expected: false)");
        System.out.println("  c1.hashCode() == c2.hashCode(): " +
                (c1.hashCode() == c2.hashCode()) + " (expected: true)");

        // Room equality test
        Room r1 = new Room("101", 100.0, RoomType.DOUBLE);
        Room r2 = new Room("101", 150.0, RoomType.SINGLE); // Same room number, different price/type
        Room r3 = new Room("102", 100.0, RoomType.DOUBLE); // Different room number

        System.out.println("\nRoom equality:");
        System.out.println("  r1.equals(r2): " + r1.equals(r2) + " (expected: true)");
        System.out.println("  r1.equals(r3): " + r1.equals(r3) + " (expected: false)");
    }

    private static void testReservationService() {
        ReservationService service = ReservationService.getInstance();

        // Clear any existing data
        // Note: In real app, we'd have a clear() method, but for now we'll work with existing

        System.out.println("Adding rooms...");
        Room room101 = new Room("101", 99.99, RoomType.DOUBLE);
        Room room102 = new Room("102", 79.99, RoomType.SINGLE);
        FreeRoom free201 = new FreeRoom("201", RoomType.SINGLE);

        service.addRoom(room101);
        service.addRoom(room102);
        service.addRoom(free201);

        System.out.println("Total rooms: " + service.getTotalRooms());

        // Test finding available rooms
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        System.out.println("\nFinding available rooms from " + today + " to " + tomorrow);
        Collection<IRoom> available = service.findRooms(today, tomorrow);
        System.out.println("Available rooms: " + available.size());

        // Test reservation
        Customer customer = new Customer("Test", "User", "test@email.com");
        System.out.println("\nMaking reservation...");
        Reservation reservation = service.reserveARoom(customer, room101, today, tomorrow);
        System.out.println("Reservation created for room: " + reservation.getRoom().getRoomNumber());

        // Test finding available rooms after reservation
        System.out.println("\nFinding available rooms after reservation...");
        available = service.findRooms(today, tomorrow);
        System.out.println("Available rooms now: " + available.size());

        // Print all reservations
        System.out.println("\nPrinting all reservations:");
        service.printAllReservation();
    }

    private static void testCustomerService() {
        CustomerService service = CustomerService.getInstance();

        System.out.println("Adding customers...");
        service.addCustomer("john@email.com", "John", "Doe");
        service.addCustomer("jane@email.com", "Jane", "Smith");

        System.out.println("Total customers: " + service.getTotalCustomers());

        // Test getting customer
        System.out.println("\nGetting customer john@email.com:");
        Customer john = service.getCustomer("john@email.com");
        System.out.println("Found: " + john.getFirstName() + " " + john.getLastName());

        // Test duplicate email
        System.out.println("\nTrying to add duplicate email...");
        try {
            service.addCustomer("john@email.com", "Johnny", "Doe");
            System.out.println("  ✗ Should have failed!");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Correctly rejected: " + e.getMessage());
        }

        // Print all customers
        System.out.println("\nAll customers:");
        service.printAllCustomers();
    }

    private static void testHotelResource() {
        HotelResource hotel = HotelResource.getInstance();

        System.out.println("1. Creating customer accounts...");
        hotel.createACustomer("alice@email.com", "Alice", "Johnson");
        hotel.createACustomer("bob@email.com", "Bob", "Williams");

        System.out.println("\n2. Getting customer information...");
        Customer alice = hotel.getCustomer("alice@email.com");
        System.out.println("Alice: " + alice.getFirstName() + " " + alice.getLastName());

        System.out.println("\n3. Finding available rooms...");
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(2);
        Collection<IRoom> rooms = hotel.findARoom(today, tomorrow);
        System.out.println("Found " + rooms.size() + " available rooms");

        if (!rooms.isEmpty()) {
            System.out.println("\n4. Booking a room for Alice...");
            IRoom firstRoom = rooms.iterator().next();
            try {
                Reservation booking = hotel.bookARoom("alice@email.com", firstRoom, today, tomorrow);
                System.out.println("Booking successful!");
                System.out.println("Room: " + booking.getRoom().getRoomNumber());
                System.out.println("Price: $" + booking.getRoom().getRoomPrice());
                System.out.println("Dates: " + booking.getCheckInDate() + " to " + booking.getCheckOutDate());
            } catch (Exception e) {
                System.out.println("Booking failed: " + e.getMessage());
            }
        }

        System.out.println("\n5. Getting Alice's reservations...");
        Collection<Reservation> aliceReservations = hotel.getCustomersReservations("alice@email.com");
        System.out.println("Alice has " + aliceReservations.size() + " reservation(s)");

        // Try to get non-existent customer
        System.out.println("\n6. Trying to get non-existent customer...");
        Customer ghost = hotel.getCustomer("ghost@email.com");
        if (ghost == null) {
            System.out.println("Correctly returned null for non-existent customer");
        }
    }

    private static void testCompleteFlow() {
        System.out.println("Testing complete user flow...");
        System.out.println("-----------------------------");

        HotelResource hotel = HotelResource.getInstance();
        ReservationService resService = ReservationService.getInstance();
        CustomerService custService = CustomerService.getInstance();

        // Step 1: Customer creates account
        System.out.println("\nSTEP 1: Customer registration");
        System.out.println("-----------------------------");
        hotel.createACustomer("guest@hotel.com", "Mike", "Taylor");

        // Step 2: Customer searches for rooms
        System.out.println("\nSTEP 2: Search for rooms");
        System.out.println("-------------------------");
        LocalDate checkIn = LocalDate.now().plusDays(3);
        LocalDate checkOut = checkIn.plusDays(2);

        System.out.println("Searching rooms from " + checkIn + " to " + checkOut);
        Collection<IRoom> availableRooms = hotel.findARoom(checkIn, checkOut);

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for selected dates.");
            // Try alternative dates
            checkIn = checkIn.plusDays(1);
            checkOut = checkOut.plusDays(1);
            System.out.println("Trying alternative dates: " + checkIn + " to " + checkOut);
            availableRooms = hotel.findARoom(checkIn, checkOut);
        }

        System.out.println("Found " + availableRooms.size() + " available rooms:");
        for (IRoom room : availableRooms) {
            String freeMarker = room.isFree() ? " (FREE!)" : "";
            System.out.println("  - Room " + room.getRoomNumber() +
                    ": $" + room.getRoomPrice() +
                    " [" + room.getRoomType() + "]" + freeMarker);
        }

        // Step 3: Customer books a room
        if (!availableRooms.isEmpty()) {
            System.out.println("\nSTEP 3: Book a room");
            System.out.println("--------------------");
            IRoom selectedRoom = availableRooms.iterator().next();

            try {
                Reservation booking = hotel.bookARoom("guest@hotel.com", selectedRoom, checkIn, checkOut);
                System.out.println("✓ Booking confirmed!");
                System.out.println("  Reservation ID: " + booking.hashCode());
                System.out.println("  Room: " + booking.getRoom().getRoomNumber());
                System.out.println("  Total cost: $" +
                        (booking.getRoom().getRoomPrice() *
                                (checkOut.getDayOfYear() - checkIn.getDayOfYear())));
            } catch (Exception e) {
                System.out.println("✗ Booking failed: " + e.getMessage());
            }
        }

        // Step 4: View booking
        System.out.println("\nSTEP 4: View reservations");
        System.out.println("--------------------------");
        Collection<Reservation> reservations = hotel.getCustomersReservations("guest@hotel.com");
        System.out.println("You have " + reservations.size() + " reservation(s):");
        for (Reservation res : reservations) {
            System.out.println(res);
        }

        // Step 5: System summary
        System.out.println("\nSYSTEM SUMMARY");
        System.out.println("---------------");
        System.out.println("Total customers: " + custService.getTotalCustomers());
        System.out.println("Total rooms: " + resService.getTotalRooms());
        System.out.println("Total reservations: " + resService.getTotalReservations());
    }
}