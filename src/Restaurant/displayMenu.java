package Restaurant;
import fileHandling.fileHandle;
import Restaurant_menu.*;
import java.util.ArrayList;
import java.util.Scanner;
public class displayMenu {
    static Scanner input = new Scanner(System.in);
    private static ArrayList<menuItems> menu;
    public static void displayMenuOfRestaurant(int Rest_id) {
        menu = fileHandle.readMenuItemsFromFile(Rest_id);
        for (menuItems item : menu) {
            System.out.println("\nItem name: "+item.getName());
            System.out.println("Description: "+item.getDescription());
            System.out.println("Price: "+item.getPrice());
        }
    }
    public static int chooseRestaurant() {
        System.out.println("\nChoose a restaurant: ");
        String Rest_name = input.nextLine();
        ArrayList<restaurant> restaurants = displayRestaurants.getRestaurants();
        boolean found = false;
        int rest_id=-1;
        for (restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(Rest_name)) {
                found = true;
                rest_id = restaurant.getId();
            }
        }
        if (found){
            return rest_id;}
        else {
            System.out.println("\nRestaurant not found");
            return -1;}
    }
}
