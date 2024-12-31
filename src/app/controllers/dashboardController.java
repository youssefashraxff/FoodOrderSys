package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class dashboardController {
    @FXML
    private VBox menuDropdown;
    public Button backToLogin;
    public Button accountButton;
    @FXML
    public void toggleMenu() {
        menuDropdown.setVisible(!menuDropdown.isVisible());
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
    @FXML
    public void handleAccount (){
        try{
            Parent dashboardRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/account.fxml")));
            Stage stage = (Stage) accountButton.getScene().getWindow();
            stage.setScene(new Scene(dashboardRoot));
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error loading dashboard screen: "+e.getMessage());
        }
    }
}
