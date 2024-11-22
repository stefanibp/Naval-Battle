package com.example.navalbattle.view;

import com.example.navalbattle.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * This class represents the game stage for the Naval Battle game.
 * It manages the display and behavior of the game window.
 * The stage is a singleton, ensuring that only one instance of the game window exists at any time.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class GameStage extends Stage {
    private GameController gameController;
    private Parent root;

    /**
     * Constructor for GameStage. It loads the FXML view, sets up the scene, and initializes the stage.
     * It also sets the title, icon, and resizes properties of the game window.
     */
    public GameStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/game-view.fxml"));
        try {
            root = loader.load();
            gameController = loader.getController();
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
     * Returns the GameController associated with this stage.
     *
     * @return the GameController.
     * @throws IllegalStateException if the gameController is not initialized.
     */
    public GameController getGameController() {
        if (gameController == null) {
            throw new IllegalStateException("The game controller has not been initialized.");
        }
        return gameController;
    }

    /**
     * Singleton pattern implementation to ensure only one instance of GameStage.
     *
     * @return the instance of GameStage.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    /**
     * Returns the singleton instance of the GameStage. If the instance doesn't exist, it creates one.
     *
     * @return the singleton instance of GameStage.
     */
    public static GameStage getInstance() {
        GameStageHolder.INSTANCE = (GameStageHolder.INSTANCE != null ? GameStageHolder.INSTANCE : new GameStage());
        return GameStageHolder.INSTANCE;
    }

    /**
     * Closes the GameStage and deletes the instance.
     */
    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }
}
