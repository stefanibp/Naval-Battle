package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class Destroyer implements IShip {

    private int currentRotation = 0;

    @Override
    public String getName() {
        return "Destroyer";
    }

    @Override
    public int getSize() {
        return 2; // El tamaño en celdas
    }

    @Override
    public StackPane render() {
        StackPane root = new StackPane();

        root.setStyle("-fx-background-color: lightgray;");

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                100.0, 40.0,
                83.0, 25.0,
                83.0, 55.0);

        triangle1.setFill(Color.BLACK);
        root.getChildren().addAll(triangle1);

        Rectangle body = Shape.square(20, 25, 70, 30, Color.BLACK);
        body.setArcWidth(20);
        body.setArcHeight(40);
        root.getChildren().add(body);

        double squareSize = 10;
        Rectangle square1 = Shape.squareStyle(35, 28, squareSize, squareSize, Color.WHITE);
        Rectangle square2 = Shape.squareStyle(48, 28, squareSize, squareSize, Color.WHITE);
        Rectangle square3 = Shape.squareStyle(35, 42, squareSize, squareSize, Color.WHITE);
        Rectangle square4 = Shape.squareStyle(48, 42, squareSize, squareSize, Color.WHITE);

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

        hexagon.setFill(Color.WHITE);
        hexagon.setStrokeWidth(0);
        root.getChildren().add(hexagon);

        Ellipse e1 = Shape.circleStyle(3, 3, Color.WHITE, Color.BLACK, 1.0, 30, 45);

        Ellipse e2 = Shape.circleStyle(3, 3, Color.WHITE, Color.BLACK, 1.0, 30, 35);

        root.getChildren().addAll(e1, e2);

        return root;
    }

    @Override
    public IShip clone(){
        return new Destroyer();
    }

    @Override
    public int getCurrentRotation() {
        return currentRotation;
    }

    @Override
    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }
}

