package app.models.Restaurant;
import app.models.Order.Review;
import app.models.Restaurant_menu.menuItems;
import app.models.fileHandling.fileHandle;
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
        ArrayList<Review> reviews = fileHandle.readReviewFromFile(getChosenRestaurantName());
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
        System.out.println("\n>>> Reviews");
        for (Review review : reviews){
            System.out.println("\nCostumer name: "+review.getCustomerName());
            System.out.println("Review: "+review.getComment());
            System.out.println("Rating: "+review.getRating());
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
                System.out.println("\njava.java.models.Restaurant not found. Please enter a valid name.");
            }
        }
        return restId;
    }
    public static String getChosenRestaurantName() {
        return ChosenRestaurantName;
    }
}
