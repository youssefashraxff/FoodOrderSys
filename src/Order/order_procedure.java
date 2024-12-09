package Order;

import Restaurant.displayMenu;
import Restaurant_menu.menuItems;
import fileHandling.fileHandle;
import user.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class order_procedure {
    static Scanner input = new Scanner(System.in);

    private String ItemName;
    private String Description;
    private double Price;
    private int Quantity;


   // private static ArrayList<order_procedure> cart_items = new ArrayList<>();
    private static ArrayList<order_procedure> ordered_items = new ArrayList<>();

    public order_procedure(String ItemName, String Description, double Price, int Quantity) {
        this.ItemName = ItemName;
        this.Description = Description;
        this.Price = Price;
        this.Quantity = Quantity;
    }
    public order_procedure() {}
    public static void order_items(User loggedInUser)
    {
        // Choosing a restaurant and placing an order
        int rest_choice = displayMenu.chooseRestaurant();
        displayMenu.displayMenuOfRestaurant(rest_choice);

        boolean order_choice = false;
        while (!order_choice) {
            System.out.println("Enter the name of the item you would like to order:");
            String itemName = input.nextLine();
            selectItem(rest_choice, itemName ,loggedInUser);

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
    }
    // Static method to select an item and add it to the orderedItems list
    public static void selectItem(int restId, String itemName, User loggedInUser) {
        ArrayList<menuItems> allMenuItems = fileHandle.readMenuItemsFromFile(restId);
        boolean found = false;

        for (menuItems menuItem : allMenuItems) {
            if (menuItem.getName().trim().equalsIgnoreCase(itemName.trim())) {
                found = true;
                int quantity = 0;
                boolean validInput = false;

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
                // Add the item to the user's cart
                loggedInUser.getCart().addItem(new cart(
                        menuItem.getName(),
                        menuItem.getDescription(),
                        menuItem.getPrice(),
                        quantity));
                System.out.println("\n" + quantity + " x " + menuItem.getName() + " added to your cart.");
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found. Please check the menu and try again.");
        }
    }

    public static void addToOrder(User loggedInUser) {
        System.out.println("\nYour Order:");
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

    /*public static ArrayList<order_procedure> getOrderedItems() {
        return cart_items;
    }*/
}
