import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void restoreRoom(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class CancellationService {
    private Stack<String> rollbackHistory = new Stack<>();

    public void cancelBooking(String reservationId, String roomType, RoomInventory inventory) {
        inventory.restoreRoom(roomType);

        String historyRecord = "Released Reservation ID: " + reservationId +
                "\nUpdated " + roomType + " Room Availability: " + inventory.getAvailability(roomType);
        rollbackHistory.push(historyRecord);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");
        while (!rollbackHistory.isEmpty()) {
            System.out.println(rollbackHistory.pop());
        }
    }
}

public class Bookmystay {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        cancellationService.cancelBooking("Single-1", "Single", inventory);

        cancellationService.showRollbackHistory();
    }
}