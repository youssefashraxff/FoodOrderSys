package app.models.foodsys;

import app.models.Order.order_procedure;
import app.models.Order.search_filter;
import app.models.Restaurant.displayRestaurants;
import app.models.user.Admin;
import app.models.user.Customer;
import app.models.user.User;

import java.util.Scanner;

public class foodSys
{
    public static User loggedInUser = null;
    public static Customer loggedInCustomer = null;
    public static Admin loggedInAdmin = null;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nWelcome to the Food Ordering System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInt(input);

            switch (choice) {
                case 1 -> {
                    //Customer.RegisterUser();
                    postLoginMenu_customer(input);
                }
                case 2 -> {
                    //loggedInUser = User.LoginUser();

                    if (loggedInUser instanceof Customer) {
                        loggedInCustomer = (Customer) loggedInUser;
                        postLoginMenu_customer(input);
                    }
                    else if (loggedInUser instanceof Admin){
                        loggedInAdmin = (Admin) loggedInUser;
                        postLoginMenu_admin(input);
                    }

                }
                case 3 -> {
                    System.out.println("Goodbye!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }

        input.close();
    }
    // Method to display the post-login menu
    private static void postLoginMenu_customer(Scanner input) {
        displayRestaurants display = new displayRestaurants();
        display.displayRestaurnats_default();
        boolean inMenu = true;
        while (inMenu) {
            //System.out.println("\nWhat would you like to do next?");
            System.out.println("\n1. Choose a restaurant");
            System.out.println("2. Filter restaurants");
            System.out.println("3. View Cart");
            System.out.println("4. View Orders");
            System.out.println("5. View java.java.models.payments");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");

            int choiceNext = getValidInt(input);

            switch (choiceNext) {
                case 1 -> order_procedure.order_items(loggedInCustomer);
                case 2 -> search_filter.filterRestaurantsMenu(input);
                case 3 -> {
                    if(loggedInCustomer.getCart().displayCartMenu()){
                        loggedInCustomer.addCartToOrder();
                        loggedInCustomer.postOrder();
                    }
                }
                case 4 -> {loggedInCustomer.displayOrder();}
                case 5 -> {if(loggedInCustomer.displayPaymentMethods()){
                    loggedInCustomer.addPaymentMethod();
                }
                }
                case 6 -> {
                    System.out.println("Goodbye!");
                    inMenu = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private static void postLoginMenu_admin(Scanner input) {
        boolean inMenu = true;
        while(inMenu){
            System.out.println("\n1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Change Item Price");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice: ");

            int choiceNext = getValidInt(input);
            switch (choiceNext) {
                case 1 -> {loggedInAdmin.addItem();}
                case 2 -> {loggedInAdmin.removeItem();}
                case 3 -> {loggedInAdmin.changePrice();}
                case 4 -> {System.out.println("Goodbye!");
                    inMenu = false;}
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static int getValidInt(Scanner input) {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}