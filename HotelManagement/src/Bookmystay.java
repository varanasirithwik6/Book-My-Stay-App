import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Custom Exception for domain-specific validation errors.
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

/**
 * Validates booking requests before processing.
 */
class ReservationValidator {
    // Note: Case-sensitive as required by the use case
    private final List<String> validRooms = Arrays.asList("Single", "Double", "Suite");

    public void validate(String guestName, String roomType) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }
        if (!validRooms.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}

/**
 * Component to manage room inventory (Stub for this use case).
 */
class RoomInventory {
    public RoomInventory() {}
}

/**
 * Component to manage booking queue (Stub for this use case).
 */
class BookingRequestQueue {
    public BookingRequestQueue() {}
}

/**
 * MAIN CLASS UseCase9ErrorHandlingValidation
 * Use Case 9: Error Handling & Validation
 */
public class Bookmystay {

    public static void main(String[] args) {
        // Display application header
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize required components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate user input
            validator.validate(guestName, roomType);

            System.out.println("Booking successfully validated and queued!");

        } catch (InvalidBookingException e) {
            // Handle domain-specific validation errors
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            // Ensure the scanner is closed to prevent resource leaks
            scanner.close();
        }
    }
}