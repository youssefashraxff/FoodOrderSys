package app.controllers;

import app.models.user.Admin;
import app.models.user.Customer;
import app.models.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    public static User loggedInUser = null;
    public static Customer loggedInCustomer = null;
    public static Admin loggedInAdmin = null;


    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private Label errorLabelLogin;
    @FXML
    public Label errorLabelRegister;
    @FXML
    public Label CreateNewAccount;
    @FXML
    public Label backToLogin;

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        loggedInUser = User.LoginUser(username, password);

        if (loggedInUser instanceof Customer) {
            loggedInCustomer = (Customer) loggedInUser;
            errorLabelLogin.setText("Login successful. Welcome, "+loggedInCustomer.getUsername()+"!");
        }
        else if (loggedInUser instanceof Admin){
            loggedInAdmin = (Admin) loggedInUser;
            errorLabelLogin.setText("Login successful. Welcome, "+loggedInAdmin.getUsername()+"!");
        } else if (loggedInUser == null) {
            errorLabelLogin.setText("Invalid username or password.");
        }
    }
    @FXML
    public void handleRegister(){
        try {
            Parent registerRoot = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
            Stage stage = (Stage) CreateNewAccount.getScene().getWindow();
            // Set the new scene
            stage.setScene(new Scene(registerRoot));
            stage.setTitle("Register");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Register screen: " + e.getMessage());
        }
    }
    @FXML
    public void handleRegisterProcess(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        loggedInCustomer = User.RegisterUser(username,email,password,errorLabelRegister);
    }
    @FXML
    private void handleBackToLogin() {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            // Create the scene
            Scene scene = new Scene(loginRoot);
            Stage stage = (Stage) backToLogin.getScene().getWindow();
            // Set up the stage (the window)
            stage.setTitle("Food Ordering System");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Login screen: " + e.getMessage());
        }
    }
}
