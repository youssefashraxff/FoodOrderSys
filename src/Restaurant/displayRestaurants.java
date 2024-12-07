package Restaurant;
import fileHandling.fileHandle;
import java.util.ArrayList;

public class displayRestaurants
{
    private static final ArrayList<restaurant> restaurants=fileHandle.readRestaurantFromFile();
    public static void displayRestaurantss()
    {
        for (restaurant r : restaurants)
        {
            System.out.println("\n=========================\n");
            System.out.println("Name: " + r.getName());
            System.out.println("Address: " + r.getAddress());
            System.out.println("Contact: " + r.getContact());
            System.out.println("Rating: " + r.getRating());
            System.out.println("Category: " + r.getCategory());
        }
    }
    public static ArrayList<restaurant> getRestaurants() {
        return restaurants;
    }
}
