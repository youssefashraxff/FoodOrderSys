package user;
import Restaurant.*;
import fileHandling.fileHandle;
import Restaurant_menu.menuItems;
import java.util.ArrayList;

public class Admin extends User {

    private final ArrayList<menuItems> items = new ArrayList<>();
    private final ArrayList<restaurant> restaurants = fileHandle.readRestaurantFromFile();

    public Admin(String username, String password) {
        super(username, password);
    }

    private int selectRestaurantAndDisplayMenu() {
        int restaurantID = displayMenu.chooseRestaurant(restaurants);
        displayMenu.displayMenuOfRestaurant(restaurantID);
        return restaurantID;
    }

    public void addItem(){
        int Restaurant_ID=selectRestaurantAndDisplayMenu();
        //Implementation
        fileHandle.writeMenuItemsToFile(items);
    }
    public void removeItem(){
        int Restaurant_ID=selectRestaurantAndDisplayMenu();
        //Implementation
        fileHandle.writeMenuItemsToFile(items);
    }
    public void changePrice() {
        int Restaurant_ID = selectRestaurantAndDisplayMenu();
        //Implementation
        fileHandle.writeMenuItemsToFile(items);
    }
}
