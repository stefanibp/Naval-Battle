package com.example.navalbattle.view;

import com.example.navalbattle.controller.FleetController;
import com.example.navalbattle.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FleetStage extends Stage {
    private FleetController fleetController;
    private Parent root;

    public FleetStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/Fleet-view.fxml"));
        try{
            root = loader.load();
            fleetController = loader.getController();
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


    public FleetController getGameController() {
        return fleetController;
    }

    private static class FleetStageHolder {
        private static FleetStage INSTANCE;
    }

    public static FleetStage getInstance() {
        FleetStageHolder.INSTANCE = (FleetStageHolder.INSTANCE != null ? FleetStageHolder.INSTANCE : new FleetStage());
        return FleetStageHolder.INSTANCE;
    }

    public static void deleteInstance() {
        FleetStageHolder.INSTANCE.close();
        FleetStageHolder.INSTANCE = null;
    }
}
