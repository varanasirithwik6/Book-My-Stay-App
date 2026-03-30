import java.util.HashMap;
import java.util.Map;

abstract class Room {
    private int beds;
    private int size;
    private double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayInfo() {
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
    }

}

class SingleRoom extends Room {


    public SingleRoom() {
        super(1, 250, 1500.0);
    }

}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

public class Bookmystay{
    public static void main(String[] args) {
        // Initialize Inventory (System State)
        Map<String, Integer> availability = new HashMap<>();
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);

        System.out.println("--- Room Search Results ---\n");

        // Logic to check and display availability without modifying the map
        if (availability.get("Single") > 0) {
            System.out.println("Single Room:");
            new SingleRoom().displayInfo();
            System.out.println("Available: " + availability.get("Single"));
        }

        if (availability.get("Double") > 0) {
            System.out.println("\nDouble Room:");
            new DoubleRoom().displayInfo();
            System.out.println("Available: " + availability.get("Double"));
        }

        if (availability.get("Suite") > 0) {
            System.out.println("\nSuite Room:");
            new SuiteRoom().displayInfo();
            System.out.println("Available: " + availability.get("Suite"));
        }
    }
}