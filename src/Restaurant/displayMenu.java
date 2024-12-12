package Restaurant;
import fileHandling.fileHandle;
import Restaurant_menu.*;
import java.util.ArrayList;
import java.util.Scanner;

public class displayMenu {
    static Scanner input = new Scanner(System.in);

    private static String ChosenRestaurantName;

    private static void displayMenu_attributes(menuItems item){
        System.out.println("\nItem name: "+item.getName());
        System.out.println("Description: "+item.getDescription());
        System.out.println("Price: "+item.getPrice());
    }
    public static void displayMenuOfRestaurant(int Rest_id) {
        ArrayList<menuItems> menu = fileHandle.readMenuItemsFromFile(Rest_id);
        System.out.println("\n>>> Main Item");
        for (menuItems item : menu) {
            if (item.getCategory().equals("Main Item")) {
                displayMenu_attributes(item);
            }
        }
        System.out.println("\n>>> Sides");
        for (menuItems item : menu) {
            if (item.getCategory().equals("Sides")) {
                displayMenu_attributes(item);
            }
        }
        System.out.println("\n>>> Drinks");
        for (menuItems item : menu) {
            if (item.getCategory().equals("Beverages")) {
                displayMenu_attributes(item);
            }
        }
    }
    public static int chooseRestaurant() {
        // Delegate to the parameterized method
        ArrayList<restaurant> restaurants = displayRestaurants.getRestaurants();
        return chooseRestaurant(restaurants);
    }

    public static int chooseRestaurant(ArrayList<restaurant> restaurants) {
        Scanner input = new Scanner(System.in); // Ensure Scanner is initialized
        int restId = -1;
        boolean found = false;

        while (!found) {
            System.out.println("\nChoose a restaurant: ");
            String restName = input.nextLine();
            ChosenRestaurantName = restName;
            for (restaurant restaurant : restaurants) {
                if (restaurant.getName().equalsIgnoreCase(restName)) { // Case-insensitive match
                    found = true;
                    restId = restaurant.getId();
                    break;
                }
            }
            if (!found) {
                System.out.println("\nRestaurant not found. Please enter a valid name.");
            }
        }
        return restId;
    }
    public static String saveRestaurantName() {
        return ChosenRestaurantName;
    }
}
