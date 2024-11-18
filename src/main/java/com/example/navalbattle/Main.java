package com.example.navalbattle;

import com.example.navalbattle.controller.GameController;
import com.example.navalbattle.view.GameStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WelcomeStage.getInstance();
    }
}
