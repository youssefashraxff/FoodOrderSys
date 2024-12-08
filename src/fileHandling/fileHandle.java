package fileHandling;

import user.User;
import Restaurant.restaurant;
import Restaurant_menu.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileHandle {
    //File path to user text file
    private static final String UserFilePath = "./files/users.txt";
    private static final String RestaurantFilePath = "./files/restaurants.txt";
    private static final String MenuFilePath = "./files/menu.txt";
    //Method for reading user info
    public static List<User> readUsersFromFile()
    {
        List<User> users = new ArrayList<>();
        try
        {
            FileReader userFile = new FileReader(UserFilePath);
            BufferedReader br = new BufferedReader(userFile);
            String line = br.readLine();
            while (line != null)
            {
                String user_info[] = line.split(",");
                users.add(new User(user_info[0],user_info[1],user_info[2],user_info[3]));
                line = br.readLine();
            }
            br.close();
            userFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;

    }
    //Method for writing user Info
    public static void writeUsersToFile(List<User> users)
    {
        try
        {
            FileWriter userFile = new FileWriter(UserFilePath);
            BufferedWriter bw = new BufferedWriter(userFile);
            for(User user : users)
            {
                bw.write(user.getUsername()+","+user.getEmail()+","+user.getPassword()+","+user.getDeliveryAddress());
                bw.newLine();
            }
            bw.close();
            userFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error writing users: " + e.getMessage());
        }
    }
    //Method for reading restaurant info
    public static ArrayList<restaurant> readRestaurantFromFile()
    {
        ArrayList<restaurant> restaurants = new ArrayList<>();
        try
        {
            FileReader restaurantFile = new FileReader(RestaurantFilePath);
            BufferedReader br = new BufferedReader(restaurantFile);
            String line = br.readLine();
            while (line != null)
            {
                String Rest_info[] = line.split("\\|");
                restaurants.add(new restaurant(Integer.parseInt(Rest_info[0]),
                        Rest_info[1],Rest_info[2],Rest_info[3],
                        Double.parseDouble(Rest_info[4]),Rest_info[5],readMenuItemsFromFile(Integer.parseInt(Rest_info[0]))));
                line = br.readLine();
            }
            br.close();
            restaurantFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error loading restaurants: " + e.getMessage());
        }
        return restaurants;
    }
    //Method for reading menu item
    public static ArrayList<menuItems> readMenuItemsFromFile(int Rest_Id)
    {
        ArrayList<menuItems> items = new ArrayList<>();
        try {
            FileReader menuFile = new FileReader(MenuFilePath);
            BufferedReader br = new BufferedReader(menuFile);
            String line = br.readLine();
            while (line != null)
            {
                String Item_info [] = line.split("\\|");
                if((Integer.parseInt(Item_info[0]))==Rest_Id)
                {
                    items.add(new menuItems(Integer.parseInt(Item_info[0]),
                            Item_info[1],
                            Double.parseDouble(Item_info[2]),
                            Item_info[3],Item_info[4]));
                }
                line = br.readLine();
            }
            br.close();
            menuFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error loading menu items: " + e.getMessage());
        }
        return items;
    }
}
