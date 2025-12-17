import com.hotel.ui.MainMenu;
import com.hotel.ui.AdminMenu;
import com.hotel.service.ReservationService;
import com.hotel.service.CustomerService;
import com.hotel.model.*;
import java.time.LocalDate;

public class HotelApplication {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    HOTEL RESERVATION APPLICATION");
        System.out.println("=========================================\n");

        // Initialize with some sample data
        initializeSampleData();

        // Create menu instances
        MainMenu mainMenu = new MainMenu();
        AdminMenu adminMenu = new AdminMenu();

        boolean exitApplication = false;

        while (!exitApplication) {
            // Show main menu
            mainMenu.showMenu();

            // After main menu returns, check if user wants admin
            System.out.print("\nEnter 'admin' for Admin Menu or 'exit' to quit: ");
            String input = System.console().readLine().trim().toLowerCase();

            if (input.equals("admin")) {
                // Show admin menu
                adminMenu.showMenu();
            } else if (input.equals("exit")) {
                exitApplication = true;
            }
        }

        System.out.println("\nThank you for using the Hotel Reservation System!");
        System.out.println("Goodbye!");
    }

    private static void initializeSampleData() {
        System.out.println("Initializing sample data...\n");

        try {
            // Get services
            CustomerService customerService = CustomerService.getInstance();
            ReservationService reservationService = ReservationService.getInstance();

            // Add sample customers
            customerService.addCustomer("john@email.com", "John", "Doe");
            customerService.addCustomer("jane@email.com", "Jane", "Smith");
            customerService.addCustomer("admin@hotel.com", "Admin", "User");

            // Add sample rooms
            Room room101 = new Room("101", 99.99, RoomType.DOUBLE);
            Room room102 = new Room("102", 79.99, RoomType.SINGLE);
            Room room103 = new Room("103", 129.99, RoomType.DOUBLE);
            FreeRoom room201 = new FreeRoom("201", RoomType.SINGLE);
            FreeRoom room202 = new FreeRoom("202", RoomType.DOUBLE);

            reservationService.addRoom(room101);
            reservationService.addRoom(room102);
            reservationService.addRoom(room103);
            reservationService.addRoom(room201);
            reservationService.addRoom(room202);

            // Add sample reservation
            Customer john = customerService.getCustomer("john@email.com");
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(2);

            reservationService.reserveARoom(john, room101, today, tomorrow);

            System.out.println("✓ Sample data loaded successfully!");
            System.out.println("  - 3 customers");
            System.out.println("  - 5 rooms (2 free, 3 paid)");
            System.out.println("  - 1 sample reservation\n");

        } catch (Exception e) {
            System.out.println("✗ Error loading sample data: " + e.getMessage());
        }
    }
}