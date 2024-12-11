package user;
import fileHandling.fileHandle;
import main.foodsys.foodSys;
import payments.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Order.*;

public class User {
    static Scanner input = new Scanner(System.in);

    private String username;
    private String password;
    private String email;
    private String deliveryAddress;
    private static List<User> users = fileHandle.readUsersFromFile();
    private ArrayList<userPayment> UserPayments = new ArrayList<>();
    public cart Usercart = null;

    public User(String username,String email,String password,String deliveryAddress) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        Usercart = new cart();
    }
    public User(){
        User tempUser = LoginUser();
        new User(tempUser.getUsername(),tempUser.getEmail(),tempUser.getPassword(),tempUser.getDeliveryAddress());
    }
//Getters and Setters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) { // Check if the username matches
                return user; // Return the matching user
            }
        }
        return null; // Return null if no user is found
    }
    public static boolean checkUsername(String username) {
        boolean check = false;

        for (User user : users) {
            if ((user.getUsername()).equals(username)) {
                foodSys.loggedInUser = User.findUserByUsername(username);
                check = true;
            }
        }
        return check;
    }

    public static boolean checkEmail(String email) {
        boolean check = false;
        for (User user : users) {
            if ((user.getEmail()).equals(email)) {
                check = true;
            }
        }
        return check;
    }

    public static boolean checkLogin(String username, String password) {
        boolean check = false;
        for (User user : users) {
            if ((user.getUsername()).equals(username) && (user.getPassword()).equals(password)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static void addUser(String username, String password, String email, String deliveryAddress)
    {
        users.add(new User(username, password, email, deliveryAddress));
        fileHandle.writeUsersToFile(users);
    }
    public static void RegisterUser() {
        String usernameInput;
        String passwordInput;
        String emailInput ;
        String deliveryAddressInput;

        // Validate username
        while (true) {
            System.out.print("Enter username: ");
            usernameInput = input.nextLine().trim();

            if (usernameInput.isEmpty()) {
                System.out.println("Username cannot be empty. Please try again.");
            } else if (User.checkUsername(usernameInput)) {
                System.out.println("Username already exists. Please enter a different username.");
            } else {
                break;
            }
        }
        // Validate email
        while (true) {
            System.out.print("Enter email: ");
            emailInput = input.nextLine().trim();

            if (emailInput.isEmpty()) {
                System.out.println("Email cannot be empty. Please try again.");
            } else if (!User.isValidEmail(emailInput)) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            } else if (User.checkEmail(emailInput)) {
                System.out.println("Email already exists. Please enter a different email.");
            } else {
                break;
            }
        }
        // Validate password
        while (true) {
            System.out.print("Enter password: ");
            passwordInput = input.nextLine().trim();

            if (passwordInput.isEmpty()) {
                System.out.println("Password cannot be empty. Please try again.");
            } else {
                break;
            }
        }
        // Get delivery address
        while (true) {
            System.out.print("Enter delivery address: ");
            deliveryAddressInput = input.nextLine().trim();

            if (deliveryAddressInput.isEmpty()) {
                System.out.println("Delivery address cannot be empty. Please try again.");
            } else {
                break;
            }
        }
        // Add user to the system
        User.addUser(usernameInput, emailInput, passwordInput, deliveryAddressInput);
        foodSys.loggedInUser =  User.findUserByUsername(usernameInput);
        System.out.println("User registered successfully!");
    }
    public static User LoginUser() {
        User tempUser=null;
        boolean isAttemptingLogin = true;

        while (isAttemptingLogin) {
            System.out.println("Enter your username: ");
            String usernameInput = input.nextLine().trim();

            System.out.println("Enter your password: ");
            String passwordInput = input.nextLine().trim();

            if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                System.out.println("Username and password cannot be empty. Please try again.");
                continue;
            }

            if (checkLogin(usernameInput, passwordInput)) {
                System.out.println("Login successful. Welcome, " + usernameInput + "!");
                tempUser = User.findUserByUsername(usernameInput);
                return tempUser;
            } else {
                System.out.println("Login failed. Incorrect username or password.");
                System.out.println("Would you like to try again?");
                System.out.println("1. Yes");
                System.out.println("2. Exit");

                int choice = foodSys.getValidInt(input);
                if (choice == 2) {
                    System.out.println("Exiting login. Goodbye!");
                    isAttemptingLogin = false;
                }
            }
        }
        return tempUser;
    }
    public cart getCart() {
        if (Usercart == null) {
            Usercart = new cart(); // Lazily initialize if null
        }
        return Usercart;
    }

    public void setCart(cart cart) {
        this.Usercart = cart;
    }
}


