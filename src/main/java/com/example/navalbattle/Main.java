package com.example.navalbattle;

import com.example.navalbattle.view.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point for the Naval Battle application.
 * Initializes and launches the application, starting with the WelcomeStage.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Main extends Application {

    /**
     * Main method that launches the JavaFX application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application by displaying the WelcomeStage.
     *
     * @param primaryStage the primary stage for the application, unused here as the application uses custom stages.
     */
    @Override
    public void start(Stage primaryStage) {
        WelcomeStage.getInstance();
    }
}
