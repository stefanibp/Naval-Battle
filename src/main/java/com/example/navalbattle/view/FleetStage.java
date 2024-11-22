package com.example.navalbattle.view;

import com.example.navalbattle.controller.FleetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the fleet stage for the Naval Battle game.
 * It manages the display and behavior of the fleet setup window.
 * The stage is a singleton, ensuring that only one instance of the fleet window exists at any time.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class FleetStage extends Stage {
    private FleetController fleetController;
    private Parent root;

    /**
     * Constructor for FleetStage. It loads the FXML view, sets up the scene, and initializes the stage.
     * It also sets the title, icon, and resizes properties of the fleet window.
     */
    public FleetStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/Fleet-view.fxml"));
        try {
            root = loader.load();
            fleetController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Batalla naval");
        setResizable(false);
        show();
    }

    /**
     * Returns the FleetController associated with this stage.
     *
     * @return the FleetController.
     */
    public FleetController getGameController() {
        return fleetController;
    }

    /**
     * Singleton pattern implementation to ensure only one instance of FleetStage.
     *
     * @return the instance of FleetStage.
     */
    private static class FleetStageHolder {
        private static FleetStage INSTANCE;
    }

    /**
     * Returns the singleton instance of the FleetStage. If the instance doesn't exist, it creates one.
     *
     * @return the singleton instance of FleetStage.
     */
    public static FleetStage getInstance() {
        FleetStageHolder.INSTANCE = (FleetStageHolder.INSTANCE != null ? FleetStageHolder.INSTANCE : new FleetStage());
        return FleetStageHolder.INSTANCE;
    }

    /**
     * Closes the FleetStage and deletes the instance.
     */
    public static void deleteInstance() {
        FleetStageHolder.INSTANCE.close();
        FleetStageHolder.INSTANCE = null;
    }
}
