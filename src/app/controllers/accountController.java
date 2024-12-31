package app.controllers;

import app.models.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class accountController extends loginController {
    @FXML
    private VBox menuDropdown;
    @FXML
    private Button accountButton;
    @FXML
    public Button backLoginButton;
    @FXML
    private ImageView Logo;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField addressLabel;
    @FXML
    private TextField LastNameLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private TextField passwordLabel;
    @FXML
    private TextField firstNameLabel;
    @FXML
    public Label applyChangeLabel;

    @FXML
    public void initialize() {
        if (getLoggedInCustomer() != null) {
            firstNameLabel.setText(getLoggedInCustomer().getUsername());
            LastNameLabel.setText(getLoggedInCustomer().getLastName());
            emailLabel.setText(getLoggedInCustomer().getEmail());
            passwordLabel.setText(getLoggedInCustomer().getPassword());
            phoneNumber.setText(getLoggedInCustomer().getPhoneNumber());
            addressLabel.setText(getLoggedInCustomer().getDeliveryAddress());
        } else {
            System.out.println("Logged-in customer data is not available!");
        }
    }

    @FXML
    public void toggleMenu() {
        menuDropdown.setVisible(!menuDropdown.isVisible());
    }

    @FXML
    public void handleAccount() {
        try {
            Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/fxml/account.fxml"));
            Stage stage = (Stage) accountButton.getScene().getWindow();
            stage.setScene(new Scene(dashboardRoot));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading account screen: " + e.getMessage());
        }
    }

    @FXML
    public void backLogin() {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loginRoot);
            Stage stage = (Stage) backLoginButton.getScene().getWindow();
            stage.setTitle("Food Ordering System");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Login screen: " + e.getMessage());
        }
    }

    @FXML
    public void backToDashboard() {
        try {
            Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
            Stage stage = (Stage) Logo.getScene().getWindow();
            stage.setScene(new Scene(dashboardRoot));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading dashboard screen: " + e.getMessage());
        }
    }
    @FXML
    public void applyChanges() {
        boolean check = true;
        StringBuilder errorMessages = new StringBuilder();

        // Check if any field is empty
        if (firstNameLabel.getText().isEmpty() || LastNameLabel.getText().isEmpty() ||
                emailLabel.getText().isEmpty() || passwordLabel.getText().isEmpty() ||
                phoneNumber.getText().isEmpty() || addressLabel.getText().isEmpty()) {
            applyChangeLabel.setText("All fields must be filled!");
            return;
        }

        // Check if fields are unchanged
        if (getLoggedInCustomer().getUsername().equals(firstNameLabel.getText()) &&
                getLoggedInCustomer().getLastName().equals(LastNameLabel.getText()) &&
                getLoggedInCustomer().getEmail().equals(emailLabel.getText()) &&
                getLoggedInCustomer().getPassword().equals(passwordLabel.getText()) &&
                getLoggedInCustomer().getPhoneNumber().equals(phoneNumber.getText()) &&
                getLoggedInCustomer().getDeliveryAddress().equals(addressLabel.getText())) {
            applyChangeLabel.setText("No changes to apply.");
            return;
        }

        // Validate phone number
        if (!User.isValidPhone(phoneNumber.getText())) {
            check = false;
            errorMessages.append("Invalid Phone Number. ");
        }

        // Validate email
        if (!User.isValidEmail(emailLabel.getText())) {
            check = false;
            errorMessages.append("Invalid Email Address. ");
        }

        // If there are validation errors, display them and stop further execution
        if (!check) {
            applyChangeLabel.setText(errorMessages.toString().trim());
            return;
        }

        // Update the customer's information if all validations pass
        getLoggedInCustomer().setPhoneNumber(phoneNumber.getText());
        getLoggedInCustomer().setEmail(emailLabel.getText());
        getLoggedInCustomer().setPassword(passwordLabel.getText());
        getLoggedInCustomer().setDeliveryAddress(addressLabel.getText());
        getLoggedInCustomer().setLastName(LastNameLabel.getText());
        getLoggedInCustomer().setUsername(firstNameLabel.getText());

        // Persist the changes
        User.updateUserInfo(getLoggedInCustomer());

        applyChangeLabel.setText("Changes applied successfully!");
    }
}