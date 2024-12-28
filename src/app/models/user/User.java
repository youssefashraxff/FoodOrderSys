package app.models.user;

import javafx.scene.control.Label;

import app.models.fileHandling.fileHandle;
import app.models.foodsys.foodSys;

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
        return null; // Return null if no java.java.models.user is found
    }
    public static Admin findAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) { // Check if the username matches
                return admin; // Return the matching admin
            }
        }
        return null; // Return null if no java.java.models.user is found
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

    public static Customer RegisterUser(String usernameInput, String emailInput, String passwordInput, Label errorLabel) {
        // Validate username
        if (usernameInput.isEmpty()) {
            errorLabel.setText("Username cannot be empty. Please try again.");
            return null;
        }
        if (Customer.checkUsername(usernameInput)) {
            errorLabel.setText("Username already exists. Please enter a different username.");
            return null;
        }

        // Validate email
        if (emailInput.isEmpty()) {
            errorLabel.setText("Email cannot be empty. Please try again.");
            return null;
        }
        if (!Customer.isValidEmail(emailInput)) {
            errorLabel.setText("Invalid email format. Please enter a valid email address.");
            return null;
        }
        if (Customer.checkEmail(emailInput)) {
            errorLabel.setText("Email already exists. Please enter a different email.");
            return null;
        }

        // Validate password
        if (passwordInput.isEmpty()) {
            errorLabel.setText("Password cannot be empty. Please try again.");
            return null;
        }

        // If all validations pass, register the customer
        customers.add(new Customer(generateUserID("customer"),usernameInput, passwordInput, emailInput, null));
        fileHandle.writeUsersToFile(customers);
        errorLabel.setText("Customer registered successfully!");
        return customers.getLast();
    }

    public static User LoginUser(String usernameInput, String passwordInput) {
        User tempUser;
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
                    return null;
                }
            }
            //Check if customer
            else{
                // Check login credentials
                if (checkCustomerLogin(usernameInput, passwordInput)) {
                    System.out.println("Login successful. Welcome, " + usernameInput + "!");
                    // Find the java.java.models.user and create a new Customer object
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
                        return null; // Handle case where java.java.models.user is not found
                    }
                }
            }
        return null; // Return null if the java.java.models.user exits the login process
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
