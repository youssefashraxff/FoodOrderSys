package Restaurant;
import fileHandling.fileHandle;
import java.util.ArrayList;
import displaying.*;

public class displayRestaurants extends display
{
    private static final ArrayList<restaurant> restaurants=fileHandle.readRestaurantFromFile();

    public void displayRestaurant_attributes(restaurant r){
        System.out.println("\n=========================\n");
        System.out.println("Name: " + r.getName());
        System.out.println("Address: " + r.getAddress());
        System.out.println("Contact: " + r.getContact());
        System.out.println("Rating: " + r.getRating());
        System.out.println("Category: " + r.getCategory());
    }

    @Override
    public void displayRestaurnats_default(){
        for (restaurant r : restaurants)
        {
            displayRestaurant_attributes(r);
        }
    }
    public  ArrayList<restaurant> displayRestaurnats_rating(float rating) {
        ArrayList<restaurant> allRestaurants = fileHandle.readRestaurantFromFile();
        ArrayList<restaurant> filtered_restaurants=new ArrayList<>();
        boolean found = false;
        for (restaurant r : allRestaurants) {
            if (r.getRating()>=rating) {
                found = true;
                filtered_restaurants.add(r);
                displayRestaurant_attributes(r);
            }
            if(!found) {
                System.out.println("No restaurants found for the rating: " + rating);
            }
        }
        return filtered_restaurants;
    }
    public ArrayList<restaurant> displayRestaurnats_category(String category){
        ArrayList<restaurant> allRestaurants = fileHandle.readRestaurantFromFile();
        ArrayList<restaurant> filtered_restaurants=new ArrayList<>();
        boolean found = false;
        for (restaurant r : allRestaurants) {
            if (r.getCategory().trim().equalsIgnoreCase(category.trim())) {
                filtered_restaurants.add(r);
                found = true;
                displayRestaurant_attributes(r);
            }
        }
        if (!found) {
            System.out.println("No restaurants found for the category: " + category);
        }
        return filtered_restaurants;
    }
    public static ArrayList<restaurant> getRestaurants() {
        return restaurants;
    }
}
