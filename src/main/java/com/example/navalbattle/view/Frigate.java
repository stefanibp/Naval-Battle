package com.example.navalbattle.view;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class Frigate extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                50.0, 50.0,
                75.0, 50.0,
                85.0, 37.5,
                75.0, 25.0,
                50.0, 25.0
        );

        polygon.setFill(Color.DARKGRAY);
        polygon.setStroke(Color.DARKGRAY);
        root.getChildren().addAll(polygon);

        Line line1 = Shape.lineStyle(50, 50, 50, 25, Color.DARKGRAY, 2.0);
        Line line2 =  Shape.lineStyle(60, 42, 74, 42, Color.GREY, 4.0);
        Line line3 =  Shape.lineStyle(54, 45, 54, 30, Color.GREY, 2.0);
        Line line4 =  Shape.lineStyle(60, 33, 74, 33, Color.GREY, 4.0);
        Line line5 =  Shape.lineStyle(49, 41, 48, 41, Color.DARKGRAY, 4.0);
        Line line6 =  Shape.lineStyle(49, 34, 48, 34, Color.DARKGRAY, 4.0);

        root.getChildren().addAll(line1, line2, line3, line4, line5, line6);

        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);
        stage.setTitle("Fragata");
        stage.show();


    }
}