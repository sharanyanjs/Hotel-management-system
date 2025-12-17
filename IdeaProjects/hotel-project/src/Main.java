import com.hotel.model.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hotel Reservation System ===\n");

        // Test Customer
        Customer customer = new Customer("John", "Doe", "john.doe@email.com");
        System.out.println("1. Customer created: " + customer);

        // Test Room
        Room room = new Room("101", 99.99, RoomType.DOUBLE);
        System.out.println("2. Room created: " + room);
        System.out.println("   Is free? " + room.isFree());

        // Test FreeRoom
        FreeRoom freeRoom = new FreeRoom("201", RoomType.SINGLE);
        System.out.println("3. FreeRoom created: " + freeRoom);
        System.out.println("   Is free? " + freeRoom.isFree());

        // Test Reservation with LocalDate
        System.out.println("4. Creating Reservation...");
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        try {
            Reservation reservation = new Reservation(customer, room, today, tomorrow);
            System.out.println("   " + reservation);
        } catch (IllegalArgumentException e) {
            System.out.println("   Error: " + e.getMessage());
        }
    }
}