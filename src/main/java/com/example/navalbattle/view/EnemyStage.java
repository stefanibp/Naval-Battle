package com.example.navalbattle.view;

import com.example.navalbattle.controller.EnemyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class EnemyStage extends Stage {
    private EnemyController enemyController;
    private Parent root;

    public EnemyStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/Enemy-view.fxml"));
        try{
            root = loader.load();
            enemyController = loader.getController();
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


    public EnemyController getEnemyController() {
        return enemyController;
    }

    private static class EnemyStageHolder {
        private static EnemyStage INSTANCE;
    }

    public static EnemyStage getInstance() {
        EnemyStageHolder.INSTANCE = (EnemyStageHolder.INSTANCE != null ? EnemyStageHolder.INSTANCE : new EnemyStage());
        return EnemyStageHolder.INSTANCE;
    }

    public static void deleteInstance() {
        EnemyStageHolder.INSTANCE.close();
        EnemyStageHolder.INSTANCE = null;
    }
}
