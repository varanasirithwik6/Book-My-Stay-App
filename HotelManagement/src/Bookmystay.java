import java.util.LinkedList;
import java.util.Queue;

// Model representing a guest's reservation intent
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

// Manager for the FIFO queue
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation request) {
        queue.add(request);
    }

    public boolean hasPendingRequests() {
        return !queue.isEmpty();
    }

    public Reservation processNextRequest() {
        return queue.poll();
    }
}

public class Bookmystay {
    public static void main(String[] args) {
        System.out.println("Booking Request Queue\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests (Arrival order: Abhi, Subha, Vanmathi)
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests to the queue (FIFO - First In, First Out)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queued booking requests in the order they arrived
        int position = 1;
        while (bookingQueue.hasPendingRequests()) {
            Reservation current = bookingQueue.processNextRequest();
            System.out.println("Processing Request #" + (position++) + ":");
            System.out.println("Guest: " + current.getGuestName());
            System.out.println("Room Type: " + current.getRoomType());
            System.out.println("---------------------------");
        }
    }
}