package com.example.navalbattle.view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Destroyer extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                100.0, 40.0,
                83.0, 25.0,
                83.0, 55.0);

        triangle1.setFill(Color.DARKGRAY);
        root.getChildren().addAll(triangle1);

        Rectangle body = Shape.square(20, 25, 70, 30, Color.DARKGRAY);
        body.setArcWidth(20);
        body.setArcHeight(40);
        root.getChildren().add(body);

        double squareSize = 10;
        Rectangle square1 = Shape.squareStyle(35, 28, squareSize, squareSize, Color.GREY);
        Rectangle square2 = Shape.squareStyle(48, 28, squareSize, squareSize, Color.GREY);
        Rectangle square3 = Shape.squareStyle(35, 42, squareSize, squareSize, Color.GREY);
        Rectangle square4 = Shape.squareStyle(48, 42, squareSize, squareSize, Color.GREY);

        root.getChildren().addAll(square1, square2, square3, square4);

        double deltaX = 20;
        double deltaY = 22;

        Polygon hexagon = new Polygon(
                50.0 + deltaX, 5.0 + deltaY,
                60.0 + deltaX, 10.0 + deltaY,
                60.0 + deltaX, 25.0 + deltaY,
                50.0 + deltaX, 30.0 + deltaY,
                40.0 + deltaX, 25.0 + deltaY,
                40.0 + deltaX, 10.0 + deltaY
        );

        hexagon.setFill(Color.GREY);
        hexagon.setStrokeWidth(0);
        root.getChildren().add(hexagon);

        Ellipse e1 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 30, 45);

        Ellipse e2 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 30, 35);

        root.getChildren().addAll(e1, e2);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(root);
        hbox.setSpacing(10);

        Scene scene = new Scene(hbox, 200, 200);
        stage.setScene(scene);
        stage.setTitle("DESTRUCTOR");
        stage.show();
    }
}
