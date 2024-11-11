package com.example.navalbattle.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AircraftCarrier extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();


        Ellipse back = new Ellipse(63, 150, 10, 15);
        back.setFill(Color.DARKGRAY);
        root.getChildren().add(back);

        Rectangle body = Shape.square(60, 135, 120, 30, Color.DARKGRAY);
        root.getChildren().add(body);

        Ellipse front = Shape.ellipseStyle(180, 150, 25, 15, Color.DARKGRAY);
        root.getChildren().add(front);

        Line runway = new Line(70, 160, 150, 140);
        runway.setStroke(Color.WHITE);
        runway.setStrokeWidth(2);
        runway.getStrokeDashArray().addAll(6.0, 10.0);
        root.getChildren().add(runway);

        Rectangle tower = Shape.square(160, 145, 30, 10, Color.GRAY);
        root.getChildren().add(tower);

        Rectangle window1 = Shape.square(175, 137, 3, 3, Color.LIGHTGRAY);
        Rectangle window2 = Shape.square(182, 137, 3, 3, Color.LIGHTGRAY);
        root.getChildren().addAll(window1, window2);

        Polygon airplane1 = createAirplane(80, 140);
        Polygon airplane2 = createAirplane(130, 155);
        root.getChildren().addAll(airplane1, airplane2);

        Scene scene = new Scene(root, 300, 250);
        stage.setTitle("Aircraft Carrier");
        stage.setScene(scene);
        stage.show();
    }

    private Polygon createAirplane(double x, double y) {
        Polygon airplane = new Polygon(
                x, y,
                x - 5, y + 10,
                x + 5, y + 10
        );
        airplane.setFill(Color.LIGHTGRAY);
        return airplane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
