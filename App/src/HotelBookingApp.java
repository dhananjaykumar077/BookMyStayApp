import java.util.HashMap;
import java.util.Map;

// Core Inventory Class (Version 3.0)
class RoomInventory {

    // HashMap to store room type and availability
    private HashMap<String, Integer> inventory;

    // Constructor - Initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register room type with count
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
        System.out.println(roomType + " added with " + count + " rooms.");
    }

    // Get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (increase/decrease)
    public void updateAvailability(String roomType, int change) {
        if (inventory.containsKey(roomType)) {
            int current = inventory.get(roomType);
            int updated = current + change;

            if (updated < 0) {
                System.out.println("Cannot reduce below zero for " + roomType);
            } else {
                inventory.put(roomType, updated);
                System.out.println("Updated " + roomType + " to " + updated);
            }
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Main Class to Run Use Case 3
public class HotelBookingApp {

    public static void main(String[] args) {

        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.addRoomType("Single", 10);
        inventory.addRoomType("Double", 5);
        inventory.addRoomType("Suite", 2);

        // Display inventory
        inventory.displayInventory();

        // Check availability
        System.out.println("\nAvailable Single Rooms: " + inventory.getAvailability("Single"));

        // Update inventory (simulate booking)
        inventory.updateAvailability("Single", -2);
        inventory.updateAvailability("Suite", -1);

        // Update inventory (simulate cancellation)
        inventory.updateAvailability("Double", +1);

        // Display updated inventory
        inventory.displayInventory();
    }
}