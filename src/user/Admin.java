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
        items = fileHandle.readMenuItemsFromFile(restaurantID);
        return restaurantID;
    }

    public void addItem(){
        int Restaurant_ID=selectRestaurantAndDisplayMenu();
        for(restaurant restaurant : restaurants)
        {
            if(restaurant.getId() == Restaurant_ID )
            {

                //id,name,price,descrep,category
                System.out.println("\nEnter item name : ");
                String name = input.nextLine();
                System.out.println("Enter item price : ");
                int price = input.nextInt();
                System.out.println("Enter item description : ");
                String description = input.nextLine();
                System.out.println("Enter item category : ");
                String category = input.nextLine();
                items.add(new menuItems(Restaurant_ID, name, price, description, category));
            }
        }

        fileHandle.writeMenuItemsToFile(items);
    }
    public void removeItem(){
        int Restaurant_ID=selectRestaurantAndDisplayMenu();
        for(restaurant restaurant : restaurants)
        {
            if(restaurant.getId() == Restaurant_ID )
            {
                menuItems elements = items.get(Restaurant_ID);
                items.remove(elements);
            }
        }
        fileHandle.writeMenuItemsToFile(items);
    }
    public void changePrice() {
        int Restaurant_ID = selectRestaurantAndDisplayMenu();

        for (restaurant restaurant : restaurants) {
            if (restaurant.getId() == Restaurant_ID) {
                System.out.println("Enter the name of the item to update: ");
                String itemName = input.nextLine(); // Input the name of the item
                boolean itemFound = false; // Flag to check if the item exists

                for (menuItems item : items) {
                    if (item.getRest_Id() == Restaurant_ID && item.getName().equalsIgnoreCase(itemName)) {
                        System.out.println("Current price of the item: " + item.getPrice());
                        System.out.println("Enter the new price: ");
                        int newPrice = input.nextInt();
                        input.nextLine(); // Clear the buffer
                        item.setPrice(newPrice); // Update the price
                        System.out.println("Price updated successfully.");
                        itemFound = true;
                        break; // Exit loop after updating
                    }
                }

                if (!itemFound) {
                    System.out.println("Item not found for the given restaurant.");
                }
            }
        }

        fileHandle.writeMenuItemsToFile(items); // Write updated list back to the file
    }
}
