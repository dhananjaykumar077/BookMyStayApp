import java.util.*;

// Reservation class (Represents a booking request)
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

    public void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

// Booking Request Queue (FIFO structure)
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    // View next request (without removing)
    public Reservation peekNextRequest() {
        return queue.peek();
    }

    // Process next request (remove from queue)
    public Reservation processNextRequest() {
        return queue.poll();
    }

    // Display all requests
    public void displayQueue() {
        System.out.println("\n--- Booking Request Queue ---");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main Class
public class HotelBookingApp {

    public static void main(String[] args) {

        // Initialize queue
        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Simulate booking requests (FIFO order)
        requestQueue.addRequest(new Reservation("Alice", "Single"));
        requestQueue.addRequest(new Reservation("Bob", "Double"));
        requestQueue.addRequest(new Reservation("Charlie", "Suite"));

        // Display queue
        requestQueue.displayQueue();

        // Peek next request
        System.out.println("\nNext Request to Process:");
        Reservation next = requestQueue.peekNextRequest();
        if (next != null) {
            next.display();
        }

        // Process requests (FIFO)
        System.out.println("\nProcessing Requests:");
        while (requestQueue.peekNextRequest() != null) {
            Reservation processed = requestQueue.processNextRequest();
            System.out.print("Processing -> ");
            processed.display();
        }

        // Final state
        requestQueue.displayQueue();
    }
}