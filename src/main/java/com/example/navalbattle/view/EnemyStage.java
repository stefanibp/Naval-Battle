package com.example.navalbattle.view;

import com.example.navalbattle.controller.EnemyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

/**
 * This class represents the enemy stage for the Naval Battle game.
 * It manages the display and behavior of the enemy setup window.
 * The stage is a singleton, ensuring that only one instance of the enemy window exists at any time.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class EnemyStage extends Stage {
    private EnemyController enemyController;
    private Parent root;

    /**
     * Constructor for EnemyStage. It loads the FXML view, sets up the scene, and initializes the stage.
     * It also sets the title, icon, and resizes properties of the enemy window.
     */
    public EnemyStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/Enemy-view.fxml"));
        try {
            root = loader.load();
            enemyController = loader.getController();
        } catch (IOException e) {
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
     * Returns the EnemyController associated with this stage.
     *
     * @return the EnemyController.
     */
    public EnemyController getEnemyController() {
        return enemyController;
    }

    /**
     * Singleton pattern implementation to ensure only one instance of EnemyStage.
     *
     * @return the instance of EnemyStage.
     */
    private static class EnemyStageHolder {
        private static EnemyStage INSTANCE;
    }

    /**
     * Returns the singleton instance of the EnemyStage. If the instance doesn't exist, it creates one.
     *
     * @return the singleton instance of EnemyStage.
     */
    public static EnemyStage getInstance() {
        EnemyStageHolder.INSTANCE = (EnemyStageHolder.INSTANCE != null ? EnemyStageHolder.INSTANCE : new EnemyStage());
        return EnemyStageHolder.INSTANCE;
    }

    /**
     * Closes the EnemyStage and deletes the instance.
     */
    public static void deleteInstance() {
        EnemyStageHolder.INSTANCE.close();
        EnemyStageHolder.INSTANCE = null;
    }
}
