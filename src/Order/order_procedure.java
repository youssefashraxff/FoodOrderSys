package Order;

import Restaurant.displayMenu;
import Restaurant_menu.menuItems;
import fileHandling.fileHandle;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class order_procedure {
    static Scanner input = new Scanner(System.in);

    private String ItemName;
    private String Description;
    private double Price;
    private int Quantity;

    private static ArrayList<order_procedure> cart_items = new ArrayList<>();

    public order_procedure(String ItemName, String Description, double Price, int Quantity) {
        this.ItemName = ItemName;
        this.Description = Description;
        this.Price = Price;
        this.Quantity = Quantity;
    }
    public static int order_items()
    {
        // Choosing a restaurant and placing an order
        int rest_choice = displayMenu.chooseRestaurant();
        displayMenu.displayMenuOfRestaurant(rest_choice);

        boolean order_choice = false;
        while (!order_choice) {
            System.out.println("Enter the name of the item you would like to order:");
            String itemName = input.nextLine();
            selectItem(rest_choice, itemName);

            System.out.println("\nWould you like to order another item?");
            System.out.println("1. Yes");
            System.out.println("2. Finish order");

            while (true) {
                int orderchoices = input.nextInt();
                input.nextLine(); // Clear buffer
                if (orderchoices == 1) {
                    order_choice = false;
                    break;
                } else if (orderchoices == 2) {
                    order_choice = true;
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }
        }
        return rest_choice;
    }
    // Static method to select an item and add it to the orderedItems list
    public static void selectItem(int restId, String itemName) {
        ArrayList<menuItems> allMenuItems = fileHandle.readMenuItemsFromFile(restId);
        boolean found = false;

        for (menuItems menuItem : allMenuItems) {
            if (menuItem.getName().trim().equalsIgnoreCase(itemName.trim())) {
                found = true;
                int quantity = 0;
                boolean validInput = false;
                // quantity
                while (!validInput) {
                    try {
                        System.out.println("Enter the quantity you would like to order: ");
                        quantity = input.nextInt();
                        input.nextLine(); // Clear buffer
                        if (quantity > 0) {
                            validInput = true;
                        } else {
                            System.out.println("Invalid quantity. Please enter a positive number.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        input.nextLine(); // Clear invalid input from the buffer
                    }
                }
                // Add the item to the cart
                cart_items.add(new order_procedure(
                        menuItem.getName(),
                        menuItem.getDescription(),
                        menuItem.getPrice(),
                        quantity));
                System.out.println("\n"+quantity + " x " + menuItem.getName() + " added to your cart.");
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found. Please check the menu and try again.");
        }
    }
    // Method to display all ordered items
    public static void displayCartItems() {
        System.out.println("\nYour Order:");
        for (order_procedure item : cart_items) {
            System.out.println("Item: " + item.getItemName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Price: $" + item.getPrice());
            System.out.println("Quantity: "+item.getQuantity());
            System.out.println("---------------------------");
        }
    }
    public static void displayCartMenu() {
        if (order_procedure.getOrderedItems().isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            order_procedure.displayCartItems();
        }
    }
    // Getters for the attributes
    public String getItemName() {
        return ItemName;
    }

    public String getDescription() {
        return Description;
    }

    public double getPrice() {
        return Price;
    }
    public int getQuantity() {
        return Quantity;
    }
    public static ArrayList<order_procedure> getOrderedItems() {
        return cart_items;
    }
}
