package Order;
import main.foodsys.foodSys;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Restaurant.*;
import fileHandling.*;

public class Search_And_Filter {
    static Scanner input = new Scanner(System.in);

    public static void filterRestaurantsMenu(Scanner input) {
        System.out.println("Filter by:");
        System.out.println("1. Categories");
        System.out.println("2. Restaurant ratings");
        System.out.print("\nEnter your choice: ");

        int filterChoice = foodSys.getValidInt(input);

        switch (filterChoice) {
            case 1 -> Search_And_Filter.filterRestaurants_category();
            case 2 -> Search_And_Filter.filterRestaurants_Rating();
            default -> System.out.println("Invalid filter option. Please try again.");
        }
    }
    public static void filterAndOrderRestaurants(ArrayList<restaurant> filteredRestaurants) {
        if (filteredRestaurants.isEmpty()) {
            System.out.println("No restaurants found matching your criteria.");
            return;
        }

        int restChoice = displayMenu.chooseRestaurant(filteredRestaurants);
        displayMenu.displayMenuOfRestaurant(restChoice);

        boolean orderChoice = false;
        while (!orderChoice) {
            System.out.println("Enter the name of the item you would like to order:");
            String itemName = input.nextLine();

            // Perform the item selection logic
            order_procedure.selectItem(restChoice, itemName);

            // Ask the user if they want to order another item or finish
            System.out.println("Would you like to order another item?");
            System.out.println("1 - Yes");
            System.out.println("2 - Finish order");

            boolean validChoice = false;
            while (!validChoice) {
                try {
                    int orderChoiceInput = input.nextInt();
                    input.nextLine(); // Clear buffer
                    if (orderChoiceInput == 1) {
                        validChoice = true;
                    } else if (orderChoiceInput == 2) {
                        validChoice = true;
                        orderChoice = true;
                    } else {
                        System.out.println("Invalid choice. Please enter 1 (Yes) or 2 (Finish).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number (1 or 2).");
                    input.nextLine(); // Clear invalid input from the buffer
                }
            }
        }
    }
    public static void filterRestaurants_category() {
        System.out.println("Enter category:");
        String categoryChoice = input.nextLine();

        displayRestaurants displayRestaurants = new displayRestaurants();
        ArrayList<restaurant> filteredRestaurants = displayRestaurants.displayRestaurnats_category(categoryChoice);

        // Call reusable method
        filterAndOrderRestaurants(filteredRestaurants);
    }
    public static void filterRestaurants_Rating() {
        System.out.println("Enter the minimum rating you would like to see:");
        float ratingChoice = input.nextFloat();
        input.nextLine(); // Clear buffer

        displayRestaurants displayRestaurants = new displayRestaurants();
        ArrayList<restaurant> filteredRestaurants = displayRestaurants.displayRestaurnats_rating(ratingChoice);

        // Call reusable method
        filterAndOrderRestaurants(filteredRestaurants);
    }
}