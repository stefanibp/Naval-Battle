package com.example.navalbattle.view;

import com.example.navalbattle.controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Represents the welcome stage (main window) of the Naval Battle game.
 * This class handles the initialization of the window, including setting the scene,
 * loading the FXML file, and managing the controller.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class WelcomeStage extends Stage {
    private WelcomeController welcomeController;
    private Parent root;

    /**
     * Constructor for WelcomeStage.
     * Initializes the stage by loading the FXML, setting the scene, and configuring window properties.
     */
    public WelcomeStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/Welcome-view.fxml"));
        try{
            root = loader.load();
            welcomeController = loader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("BATALLA NAVAL");
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/navalbattle/favicon.png"))));
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
        show();
    }

    /**
     * Returns the instance of the WelcomeController associated with the WelcomeStage.
     *
     * @return the WelcomeController instance.
     */
    public WelcomeController getWelcomeController() {
        return welcomeController;
    }

    /**
     * Inner static class used for implementing the Singleton design pattern for the WelcomeStage.
     * Ensures that only one instance of the WelcomeStage is created.
     */
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    /**
     * Returns the singleton instance of the WelcomeStage.
     * If the instance does not exist, it is created.
     *
     * @return the singleton WelcomeStage instance.
     */
    public static WelcomeStage getInstance() {
        WelcomeStageHolder.INSTANCE = (WelcomeStageHolder.INSTANCE != null ? WelcomeStageHolder.INSTANCE : new WelcomeStage());
        return WelcomeStageHolder.INSTANCE;
    }

    /**
     * Closes and deletes the instance of the WelcomeStage.
     * This ensures that the instance is removed from memory when it is no longer needed.
     */
    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }
}
