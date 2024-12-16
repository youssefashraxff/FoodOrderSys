package fileHandling;

import Order.Review;
import user.*;
import Restaurant.restaurant;
import Restaurant_menu.*;
import payments.CardPayment;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileHandle {
    //File path to user text file
    private static final String UserFilePath = "./files/users.txt";
    private static final String AdminFilePath = "./files/admins.txt";
    private static final String RestaurantFilePath = "./files/restaurants.txt";
    private static final String MenuFilePath = "./files/menu.txt";
    private static final String PaymentFilePath = "./files/payment_methods.txt";
    private static final String ReviewFilePath = "./files/reviews.txt";
    //Method for reading user info
    public static List<Customer> readUsersFromFile() {
        List<Customer> customers = new ArrayList<>();
        try
        {
            FileReader userFile = new FileReader(UserFilePath);
            BufferedReader br = new BufferedReader(userFile);
            String line = br.readLine();
            while (line != null)
            {
                String[] user_info = line.split(",");
                customers.add(new Customer(user_info[0],user_info[1],user_info[2],user_info[3],user_info[4]));
                line = br.readLine();
            }
            br.close();
            userFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error loading customers: " + e.getMessage());
        }
        return customers;
    }
    //Method for writing user Info
    public static void writeUsersToFile(List<Customer> customers) {
        try
        {
            FileWriter userFile = new FileWriter(UserFilePath,true);
            BufferedWriter bw = new BufferedWriter(userFile);

            for(Customer customer : customers)
            {
                bw.write(customer.getUserID()+","+ customer.getUsername()+","+ customer.getEmail()+","+ customer.getPassword()+","+ customer.getDeliveryAddress());
                bw.newLine();
            }
            bw.close();
            userFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error writing customers: " + e.getMessage());
        }
    }
    //Method for reading admin info
    public static List<Admin> readAdminsFromFile() {
        List<Admin> admins = new ArrayList<>();
        try
        {
            FileReader adminFile = new FileReader(AdminFilePath);
            BufferedReader br = new BufferedReader(adminFile);
            String line = br.readLine();
            while (line != null)
            {
                String[] admin_info = line.split(",");
                admins.add(new Admin(admin_info[0],admin_info[1]));
                line = br.readLine();
            }
            br.close();
            adminFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error loading admins: " + e.getMessage());
        }
        return admins;
    }
    //Method for reading restaurant info
    public static ArrayList<restaurant> readRestaurantFromFile() {
        ArrayList<restaurant> restaurants = new ArrayList<>();
        ArrayList<menuItems> items;
        try
        {
            FileReader restaurantFile = new FileReader(RestaurantFilePath);
            BufferedReader br = new BufferedReader(restaurantFile);
            String line = br.readLine();
            while (line != null)
            {
                String[] Rest_info = line.split("\\|");
                int Id = Integer.parseInt(Rest_info[0]);
                String Name = Rest_info[1];
                String Address = Rest_info[2];
                String Contact = Rest_info[3];
                double Rating = Double.parseDouble(Rest_info[4]);
                String Category = Rest_info[5];
                restaurants.add(new restaurant(Id,Name,Address,Contact,Rating,Category));
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
    public static ArrayList<menuItems> readMenuItemsFromFile(int Rest_Id) {
        ArrayList<menuItems> items = new ArrayList<>();
        try {
            FileReader menuFile = new FileReader(MenuFilePath);
            BufferedReader br = new BufferedReader(menuFile);
            String line = br.readLine();
            while (line != null)
            {
                String[] Item_info = line.split("\\|");
                int Id = Integer.parseInt(Item_info[0]);
                if(Id==Rest_Id)
                {
                    String Name = Item_info[1];
                    String Description = Item_info[3];
                    double Price = Double.parseDouble(Item_info[2]);
                    String Category = Item_info[4];
                    items.add(new menuItems(Id, Name, Price, Description, Category));
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
    public static ArrayList<menuItems> read_all_MenuItemsFromFile() {
        ArrayList<menuItems> items = new ArrayList<>();
        try {
            FileReader menuFile = new FileReader(MenuFilePath);
            BufferedReader br = new BufferedReader(menuFile);
            String line = br.readLine();
            while (line != null)
            {
                String[] Item_info = line.split("\\|");
                int Id = Integer.parseInt(Item_info[0]);
                String Name = Item_info[1];
                String Description = Item_info[3];
                double Price = Double.parseDouble(Item_info[2]);
                String Category = Item_info[4];
                items.add(new menuItems(Id, Name, Price, Description, Category));

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
    //Method for writing menu item
    public static void writeMenuItemsToFile(ArrayList<menuItems> items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MenuFilePath))) {
            for (menuItems item : items) {
                String line = String.join("|",
                        String.valueOf(item.getRest_Id()),
                        item.getName(),
                        String.valueOf(item.getPrice()),
                        item.getDescription(),
                        item.getCategory()
                );
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing menu items: " + e.getMessage());
        }
    }
    //Method for reading payment info
    public static ArrayList<CardPayment> readPaymentMethodFromFile(String UserId) {
        ArrayList<CardPayment> user_payment_methods = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(PaymentFilePath);
            BufferedReader br = new BufferedReader(fileReader);
            String line = br.readLine();
            while(line != null){
                String[] payment_info = line.split("\\|");
                String Id = payment_info[0];

                if (UserId.equals(Id)){
                    String cardType = payment_info[1];
                    String cardNumber = payment_info[2];
                    String expiryDate = payment_info[3];
                    String cvv = payment_info[4];
                    String cardHolderName = payment_info[5];

                    user_payment_methods.add(new CardPayment(Id,cardType,cardNumber,expiryDate,cvv,cardHolderName));
                }
                line = br.readLine();
            }
            br.close();
            fileReader.close();
        }catch(IOException e){
            System.out.println("Error loading payment methods: " + e.getMessage());
        }
        return user_payment_methods;
    }
    //Method for writing payment info
    public static void writePaymentMethodToFile(ArrayList<CardPayment> payment_methods, String userId) {
        try {
            FileWriter fileWriter = new FileWriter(PaymentFilePath, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            for (CardPayment payment : payment_methods) {
                bw.write(userId + "|" + payment.getCardType() + "|" + payment.getCardNumber() + "|" +
                        payment.getExpiryDate() + "|" + payment.getCvv() + "|" + payment.getCardHolderName());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing payment methods: " + e.getMessage());
        }
    }
    //Method for reading reviews
    public static ArrayList<Review> readReviewFromFile(String RestaurantName) {
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            FileReader reviewFile = new FileReader(ReviewFilePath);
            BufferedReader br = new BufferedReader(reviewFile);
            String line = br.readLine();
            while (line != null)
            {
                String[] Item_info = line.split("\\|");
                String name = Item_info[0];
                if(name.equalsIgnoreCase(RestaurantName))
                {
                    String customerName = Item_info[1];
                    String Review = Item_info[2];
                    double Rating = Double.parseDouble(Item_info[3]);

                    reviews.add(new Review(name, customerName, Review, Rating ));
                }
                line = br.readLine();
            }
            br.close();
            reviewFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error loading menu items: " + e.getMessage());
        }
        return reviews;
    }
    //Method for writing reviews
    public static void writeReviewToFile( String RestaurantName, String CustomerName, String Review, double Rating) {
        try
        {
            FileWriter reviewFile = new FileWriter(ReviewFilePath,true);
            BufferedWriter bw = new BufferedWriter(reviewFile);

            bw.write(RestaurantName+"|"+CustomerName+"|"+ Review +"|"+Rating+"\n");
            bw.newLine();

            bw.close();
            reviewFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error writing customers: " + e.getMessage());
        }
    }
}
