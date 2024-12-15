package user;
import Restaurant.*;
import fileHandling.fileHandle;
import Restaurant_menu.menuItems;
import java.util.ArrayList;

public class Admin extends User {

    private ArrayList<menuItems> items = new ArrayList<>();
    private final ArrayList<restaurant> restaurants = fileHandle.readRestaurantFromFile();

    public Admin(String username, String password) {
        super(username, password);
    }

    private int selectRestaurantAndDisplayMenu() {
        int restaurantID = displayMenu.chooseRestaurant(restaurants);
        displayMenu.displayMenuOfRestaurant(restaurantID);
        items = fileHandle.read_all_MenuItemsFromFile();
        return restaurantID;
    }

    public void addItem(){
        int Restaurant_ID=selectRestaurantAndDisplayMenu();
        int category_choice;
        String category;

        System.out.println("\nEnter item name : ");
        String name = input.nextLine();

        System.out.println("Enter item price : ");
        double price = input.nextDouble();
        input.nextLine();

        System.out.println("Enter item description: ");
        String description = input.nextLine();  // First input works fine

        while (true) {
            System.out.println("Enter item category:");
            System.out.println("1. Main Item\n2. Sides\n3. Beverages");
            category_choice = input.nextInt();  // Reads the integer
            input.nextLine();  // Consume the leftover newline character

            if (category_choice == 1) {
                category = "Main Item";
                break;
            } else if (category_choice == 2) {
                category = "Sides";
                break;
            } else if (category_choice == 3) {
                category = "Beverages";
                break;
            } else {
                System.out.println("Invalid choice");
            }

        }
        items.add(new menuItems(Restaurant_ID, name, price, description, category));

        fileHandle.writeMenuItemsToFile(items);
    }
    public void removeItem(){
        int Restaurant_ID=selectRestaurantAndDisplayMenu();
        boolean found = false;
        while(!found) {
            System.out.println("\nEnter item name : ");
            String name = input.nextLine();

            for (menuItems item : items) {
                if (item.getName().equalsIgnoreCase(name) && item.getRest_Id() == Restaurant_ID) {
                    items.remove(item);
                    System.out.println("Item deleted successfully");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Item not found, try again.");
            }
            fileHandle.writeMenuItemsToFile(items);
        }
    }
    public void changePrice() {
        int Restaurant_ID = selectRestaurantAndDisplayMenu();

        boolean found = false;
        while(!found) {
            System.out.println("\nEnter item name : ");
            String name = input.nextLine();

            for (menuItems item : items) {
                if (item.getName().equalsIgnoreCase(name) && item.getRest_Id() == Restaurant_ID) {
                    System.out.println("Enter new price : ");
                    double price = input.nextDouble();
                    item.setPrice(price);
                    System.out.println("Price of "+item.getName()+" changed successfully");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Item not found, try again.");
            }
        }
        fileHandle.writeMenuItemsToFile(items); // Write updated list back to the file
    }
}
