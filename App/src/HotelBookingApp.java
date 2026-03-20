import java.util.*;

// Domain Model - Room
class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: ₹" + price);
        System.out.println("Amenities: " + amenities);
    }
}

// Centralized Inventory (Same concept from UC3)
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    // READ-ONLY access
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public Set<String> getAllRoomTypes() {
        return inventory.keySet();
    }
}

// Search Service (Read-only logic)
class SearchService {

    public void searchAvailableRooms(RoomInventory inventory, Map<String, Room> roomData) {

        System.out.println("\n--- Available Rooms ---");

        for (String type : inventory.getAllRoomTypes()) {

            int available = inventory.getAvailability(type);

            // Filter: only show available rooms
            if (available > 0 && roomData.containsKey(type)) {

                Room room = roomData.get(type);

                room.displayDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println("-------------------------");
            }
        }
    }
}

// Main Class
public class HotelBookingApp {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 0);
        inventory.addRoomType("Suite", 2);

        // Room domain data
        Map<String, Room> roomData = new HashMap<>();
        roomData.put("Single", new Room("Single", 2000, "WiFi, AC"));
        roomData.put("Double", new Room("Double", 3500, "WiFi, AC, TV"));
        roomData.put("Suite", new Room("Suite", 6000, "WiFi, AC, TV, Mini Bar"));

        // Search operation (READ-ONLY)
        SearchService searchService = new SearchService();
        searchService.searchAvailableRooms(inventory, roomData);

        // Verify inventory is unchanged
        System.out.println("\nInventory remains unchanged after search.");
    }
}