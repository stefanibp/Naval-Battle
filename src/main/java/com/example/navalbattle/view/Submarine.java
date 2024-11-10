package com.example.navalbattle.view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Submarine extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        Rectangle body = Shape.square(50, 100, 90, 20, Color.DARKGRAY);
        body.setArcWidth(40);
        body.setArcHeight(40);
        root.getChildren().add(body);

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                55.0, 105.0,
                40.0, 110.0,
                55.0, 115.0);

        triangle1.setFill(Color.DARKGRAY);
        root.getChildren().addAll(triangle1);


        Ellipse propeller1 = new Ellipse(43, 110, 3, 8);
        propeller1.setFill(Color.DARKGRAY);
        root.getChildren().addAll(propeller1);

        Ellipse hatch1 = Shape.ellipseStyle(60, 110, 3, 3, Color.WHITE);
        Ellipse hatch2 = Shape.ellipseStyle(80, 110, 3, 3, Color.WHITE);
        Ellipse hatch3 = Shape.ellipseStyle(115, 110, 3, 3, Color.WHITE);
        root.getChildren().addAll(hatch1, hatch2, hatch3);


        Line fins1 = Shape.lineStyle(115, 98, 115, 100, Color.DARKGRAY, 4.0);
        Line fins2 = Shape.lineStyle(115, 122, 115, 120, Color.DARKGRAY, 4.0);
        root.getChildren().addAll(fins1, fins2);

        Scene scene = new Scene(root, 250, 200);
        stage.setTitle("Submarine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
