package user;

import fileHandling.fileHandle;
import main.foodsys.foodSys;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private static final List<Customer> customers = fileHandle.readUsersFromFile();
    private static final List<Admin> admins = fileHandle.readAdminsFromFile();

    static Scanner input = new Scanner(System.in);

    String username;
    String password;

     public User( String username, String password) {
         this.username = username;
         this.password = password;
     }

    //Login and register process
    public static Customer findUserByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) { // Check if the username matches
                return customer; // Return the matching customer
            }
        }
        return null; // Return null if no user is found
    }
    public static Admin findAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) { // Check if the username matches
                return admin; // Return the matching admin
            }
        }
        return null; // Return null if no user is found
    }
    public static boolean checkUsername(String username) {
        boolean check = false;

        for (Customer customer : customers) {
            if ((customer.getUsername()).equals(username)) {
                foodSys.loggedInCustomer = Customer.findUserByUsername(username);
                check = true;
            }
        }
        return check;
    }
    public static boolean checkEmail(String email) {
        boolean check = false;
        for (Customer customer : customers) {
            if ((customer.getEmail()).equals(email)) {
                check = true;
                break;
            }
        }
        return check;
    }
    public static boolean checkCustomerLogin(String username, String password) {
        boolean check = false;
        for (Customer customer : customers) {
            if ((customer.getUsername()).equals(username) && (customer.getPassword()).equals(password)) {
                check = true;
                break;
            }
        }
        return check;
    }
    public static boolean checkAdminsLogin(String username, String password) {
        boolean check = false;
        for (Admin admin : admins) {
            if ((admin.getUsername()).equals(username) && (admin.getPassword()).equals(password)) {
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

    public static void addUser(String username, String password, String email, String deliveryAddress) {
        customers.add(new Customer(generateUserID("customer"),username, password, email, deliveryAddress));
        fileHandle.writeUsersToFile(customers);
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
            } else if (Customer.checkUsername(usernameInput)) {
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
            } else if (!Customer.isValidEmail(emailInput)) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            } else if (Customer.checkEmail(emailInput)) {
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
        System.out.println("Customer registered successfully!");
    }

    public static User LoginUser() {
        User tempUser;
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
            //Check if admin
            if (usernameInput.contains("admin") && passwordInput.contains("admin")) {
                if(checkAdminsLogin(usernameInput, passwordInput)) {
                    System.out.println("Login successful. Welcome, " + usernameInput + "!");
                    // Find the admin and create a new admin object
                    tempUser = findAdminByUsername(usernameInput);
                    Admin tempAdmin = (Admin)tempUser;
                    if(tempUser != null) {
                        return new Admin(tempAdmin.getUsername(),tempAdmin.getPassword());
                    }
                }
                else {
                    isAttemptingLogin = loginFailed();
                }
            }
            //Check if customer
            else{
                // Check login credentials
                if (checkCustomerLogin(usernameInput, passwordInput)) {
                    System.out.println("Login successful. Welcome, " + usernameInput + "!");
                    // Find the user and create a new Customer object
                    tempUser = User.findUserByUsername(usernameInput);
                    Customer tempCustomer = (Customer)tempUser;
                    if (tempUser != null) {
                        return new Customer(
                                tempCustomer.getUserID(),
                                tempCustomer.getUsername(),
                                tempCustomer.getEmail(),
                                tempCustomer.getPassword(),
                                tempCustomer.getDeliveryAddress()
                        );
                    } else {
                        System.out.println("Unexpected error: Customer not found after successful login.");
                        return null; // Handle case where user is not found
                    }
                } else {
                    isAttemptingLogin = loginFailed();
                }
            }

        }
        return null; // Return null if the user exits the login process
    }
    public static boolean loginFailed(){
         boolean check;

        System.out.println("Login failed. Incorrect username or password.");
        while (true) {
            System.out.println("Would you like to try again?");
            System.out.println("1. Yes");
            System.out.println("2. Exit");

            int choice = foodSys.getValidInt(input);
            if(choice == 1) {
                check = true;
                break;
            }
            if (choice == 2) {
                System.out.println("Exiting login. Goodbye!");
                check = false;
                break;
            } else {
                System.out.println("Invalid choice. Please select 1 or 2.");
            }
        }
        return check;
    }

    public static String generateUserID(String choice) {
        if (choice.equals("admin")) {
            Random random = new Random();
            int randomNumber = 1000 + random.nextInt(101); // Generates a number between 1000 and 1100
            return "admin" + randomNumber;
        }
        else if (choice.equals("customer")) {
            Random random = new Random();
            int randomNumber = 1000 + random.nextInt(101);
            return "customer" + randomNumber;
        }
        else return null;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
