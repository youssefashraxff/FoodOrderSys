package app.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the first screen's FXML (e.g., Login screen)
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

        // Create the scene
        Scene scene = new Scene(root);

        // Set up the stage (the window)
        primaryStage.setTitle("Food Ordering System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}