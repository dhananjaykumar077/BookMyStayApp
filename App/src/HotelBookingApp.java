import java.util.*;

// Reservation (same as UC5)
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Inventory Service (from UC3)
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    // Update after allocation
    public void reduceRoom(String type) {
        int current = inventory.getOrDefault(type, 0);
        if (current > 0) {
            inventory.put(type, current - 1);
        }
    }

    public void displayInventory() {
        System.out.println("\n--- Current Inventory ---");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

// Booking Queue (UC5)
class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll(); // FIFO
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

// Booking Service (UC6 Core Logic)
class BookingService {

    private Set<String> allocatedRoomIds; // ensures uniqueness
    private Map<String, Set<String>> roomAllocations; // type → room IDs
    private int roomCounter = 1;

    public BookingService() {
        allocatedRoomIds = new HashSet<>();
        roomAllocations = new HashMap<>();
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        String id;
        do {
            id = roomType.substring(0, 2).toUpperCase() + roomCounter++;
        } while (allocatedRoomIds.contains(id));

        allocatedRoomIds.add(id);
        return id;
    }

    // Process booking request
    public void processRequest(Reservation r, RoomInventory inventory) {

        String type = r.getRoomType();
        int available = inventory.getAvailability(type);

        System.out.println("\nProcessing request for " + r.getGuestName());

        if (available > 0) {

            // Generate unique room ID
            String roomId = generateRoomId(type);

            // Map room type → allocated IDs
            roomAllocations.putIfAbsent(type, new HashSet<>());
            roomAllocations.get(type).add(roomId);

            // Update inventory immediately
            inventory.reduceRoom(type);

            System.out.println("Booking Confirmed!");
            System.out.println("Guest: " + r.getGuestName());
            System.out.println("Room Type: " + type);
            System.out.println("Allocated Room ID: " + roomId);

        } else {
            System.out.println("Booking Failed! No rooms available for " + type);
        }
    }

    public void displayAllocations() {
        System.out.println("\n--- Room Allocations ---");
        for (String type : roomAllocations.keySet()) {
            System.out.println(type + " -> " + roomAllocations.get(type));
        }
    }
}

// Main Class
public class HotelBookingApp {

    public static void main(String[] args) {

        // Setup Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);

        // Setup Queue (UC5)
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Single"));
        queue.addRequest(new Reservation("Charlie", "Single")); // should fail
        queue.addRequest(new Reservation("David", "Double"));

        // Booking Service
        BookingService service = new BookingService();

        // Process requests FIFO
        while (queue.hasRequests()) {
            Reservation r = queue.getNextRequest();
            service.processRequest(r, inventory);
        }

        // Final state
        inventory.displayInventory();
        service.displayAllocations();
    }
}