package com.example.navalbattle.view;

import com.example.navalbattle.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    private GameController gameController;


    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattle/game-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        setTitle("Batalla naval");
        Scene scene = new Scene(root);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/navalbattle/icon.png"))));
        setScene(scene);
        setResizable(false);
        show();

    }


    /**
     * Retrieves the singleton instance of the GameStage.
     * @return The singleton instance of the GameStage.
     * @throws IOException If an I/O exception occurs.
     */
    public static GameStage getInstance() throws IOException {
        return GameStageHolder.INSTANCE = new GameStage();
    }

    /**
     * Deletes the singleton instance of the GameStage.
     */
    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the singleton instance of the GameStage.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }
}
