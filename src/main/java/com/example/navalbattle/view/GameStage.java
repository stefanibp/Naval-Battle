package com.example.navalbattle.view;

import com.example.navalbattle.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameStage extends Stage {
    private GameController gameController;
    private Parent root;

    public GameStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/game-view.fxml"));
        try{
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
       // initStyle(StageStyle.UNDECORATED);
        show();
    }


    public GameController getGameController() {
        if (gameController == null) {
            throw new IllegalStateException("El controlador del juego no ha sido inicializado.");
        }
        return gameController;
    }

    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    public static GameStage getInstance() {
        GameStageHolder.INSTANCE = (GameStageHolder.INSTANCE != null ? GameStageHolder.INSTANCE : new GameStage());
        return GameStageHolder.INSTANCE;
    }

    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }

}
