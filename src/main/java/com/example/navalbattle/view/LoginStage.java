package com.example.navalbattle.view;

import com.example.navalbattle.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the login stage for the Naval Battle game.
 * It manages the display and behavior of the login window.
 * The stage is a singleton, ensuring that only one instance of the login window exists at any time.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class LoginStage extends Stage {
    private LoginController loginController;
    private Parent root;

    /**
     * Constructor for LoginStage. It loads the FXML view, sets up the scene, and initializes the stage.
     * It also sets the title, icon, and resizes properties of the login window.
     */
    public LoginStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/login-view.fxml"));
        try {
            root = loader.load();
            loginController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("BATALLA NAVAL");
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/navalbattle/favicon.png"))));
        setResizable(false);
        show();
    }

    /**
     * Returns the LoginController associated with this stage.
     *
     * @return the LoginController.
     */
    public LoginController getLoginController() {
        return loginController;
    }

    /**
     * Singleton pattern implementation to ensure only one instance of LoginStage.
     *
     * @return the instance of LoginStage.
     */
    private static class LoginStageHolder {
        private static LoginStage INSTANCE;
    }

    /**
     * Returns the singleton instance of the LoginStage. If the instance doesn't exist, it creates one.
     *
     * @return the singleton instance of LoginStage.
     */
    public static LoginStage getInstance() {
        LoginStageHolder.INSTANCE = (LoginStageHolder.INSTANCE != null ? LoginStageHolder.INSTANCE : new LoginStage());
        return LoginStageHolder.INSTANCE;
    }

    /**
     * Closes the LoginStage and deletes the instance.
     */
    public static void deleteInstance() {
        LoginStageHolder.INSTANCE.close();
        LoginStageHolder.INSTANCE = null;
    }
}
