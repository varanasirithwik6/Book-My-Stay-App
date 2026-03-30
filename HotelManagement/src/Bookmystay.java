import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class RoomInventory {
    private Map<String, Integer> inventory = new LinkedHashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void setInventory(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public Map<String, Integer> getInventoryMap() {
        return inventory;
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));
    }
}

class FilePersistenceService {
    public void saveInventory(RoomInventory inventory, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map.Entry<String, Integer> entry : inventory.getInventoryMap().entrySet()) {
                writer.write(entry.getKey() + "-" + entry.getValue() + "\n");
            }
            System.out.println("\nInventory saved successfully.");
        } catch (IOException e) {
            System.out.println("\nError saving inventory.");
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    inventory.setInventory(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No valid inventory data found. Starting fresh.");
        }
    }
}

public class Bookmystay {
    public static void main(String[] args) {
        System.out.println("System Recovery\n");

        String filePath = "inventory_state.txt";

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        persistenceService.loadInventory(inventory, filePath);
        inventory.displayInventory();
        persistenceService.saveInventory(inventory, filePath);
    }
}