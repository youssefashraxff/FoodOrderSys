package main.foodsys;
import java.util.Scanner;
import user.*;
import Restaurant.*;

public class foodSys
{
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        while (true) {
            String username = "";
            String email = "";
            String password = "";
            String address = "";

            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                boolean validInput = false;

                while (!validInput) {
                    System.out.print("Enter username: ");
                    username = input.nextLine();
                    if (User.checkUsername(username)) {
                        System.out.println("User already exists. Please enter a different username.");
                    } else {
                        validInput = true;
                    }
                }

                validInput = false;
                while (!validInput) {
                    System.out.print("Enter email: ");
                    email = input.nextLine();

                    if (!User.isValidEmail(email)) {
                        System.out.println("Email is not valid. Please enter a valid email address.");
                    } else if (User.checkEmail(email)) {
                        System.out.println("Email already exists. Please enter a different email.");
                    } else {
                        validInput = true;
                    }
                }

                System.out.print("Enter password: ");
                password = input.nextLine();
                System.out.print("Enter delivery address: ");
                address = input.nextLine();

                User.addUser(username, email, password, address);
                System.out.println("User registered successfully!");
                displayRestaurants display = new displayRestaurants();
                int rest_choice=displayMenu.chooseRestaurant();
                displayMenu.displayMenuOfRestaurant(rest_choice);

            } else if (choice == 2) {
                boolean validInput = false;
                while (!validInput) {
                    System.out.println("Enter your username: ");
                    username = input.nextLine();
                    System.out.println("Enter your password: ");
                    password = input.nextLine();

                    if (User.checkLogin(username, password)) {
                        System.out.println("Login is successful");
                        displayRestaurants display = new displayRestaurants();
                        int rest_choice=displayMenu.chooseRestaurant();
                        displayMenu.displayMenuOfRestaurant(rest_choice);
                        validInput = true;
                    } else {
                        System.out.println("Your username or password is incorrect");
                    }
                }

            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option, please try again.");
            }
        }

        input.close();
    }
}
