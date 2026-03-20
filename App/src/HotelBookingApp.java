/**
 * MAIN CLASS UseCase2RoomInitialization
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Version 2.1
 */
public class HotelBookingApp {

    public static void main(String[] args) {

        // Initialize room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Display details of each room
        System.out.println("===== Hotel Room Details =====");
        singleRoom.displayRoomDetails();
        doubleRoom.displayRoomDetails();
        suiteRoom.displayRoomDetails();

        // Static availability (for demonstration only)
        int singleAvailable = 10;
        int doubleAvailable = 5;
        int suiteAvailable = 2;

        System.out.println("\n===== Room Availability =====");
        System.out.println("Single Rooms available: " + singleAvailable);
        System.out.println("Double Rooms available: " + doubleAvailable);
        System.out.println("Suite Rooms available: " + suiteAvailable);

        // Application ends
        System.out.println("\nUse Case 2 execution completed.");
    }
}

/**
 * ABSTRACT CLASS Room
 * Defines common attributes and behavior for all room types.
 */
abstract class Room {

    protected int numberOfBeds;      // Number of beds
    protected int squareFeet;         // Room size
    protected double pricePerNight;   // Price per night

    // Constructor to initialize attributes
    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    // Abstract method to display room details
    public abstract void displayRoomDetails();
}

/**
 * Concrete room classes
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 200, 1000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Single Room: " + numberOfBeds + " bed, " +
                squareFeet + " sq ft, Rs " + pricePerNight + " per night");
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 300, 1500.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Double Room: " + numberOfBeds + " beds, " +
                squareFeet + " sq ft, Rs " + pricePerNight + " per night");
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 500, 3000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Suite Room: " + numberOfBeds + " beds, " +
                squareFeet + " sq ft, Rs " + pricePerNight + " per night");
    }
}