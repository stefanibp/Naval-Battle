
package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
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
    public Pane render() {
        Pane root = new Pane();
        root.setPrefSize(80, 40); // Establece las dimensiones del Pane

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                60.0, 20.0,
                43.0, 5.0,
                43.0, 35.0
        );
        triangle1.setFill(Color.DARKGRAY);
        root.getChildren().add(triangle1);

        Rectangle body = Shape.square(10, 5, 60, 30, Color.DARKGRAY);
        body.setArcWidth(20);
        body.setArcHeight(40);
        root.getChildren().add(body);

        double squareSize = 10;
        Rectangle square1 = Shape.squareStyle(25, 8, squareSize, squareSize, Color.GREY);
        Rectangle square2 = Shape.squareStyle(38, 8, squareSize, squareSize, Color.GREY);
        Rectangle square3 = Shape.squareStyle(25, 22, squareSize, squareSize, Color.GREY);
        Rectangle square4 = Shape.squareStyle(38, 22, squareSize, squareSize, Color.GREY);
        root.getChildren().addAll(square1, square2, square3, square4);

        double deltaX = 10;
        double deltaY = 2;

        Polygon hexagon = new Polygon(
                40.0 + deltaX, 5.0 + deltaY,
                50.0 + deltaX, 10.0 + deltaY,
                50.0 + deltaX, 25.0 + deltaY,
                40.0 + deltaX, 30.0 + deltaY,
                30.0 + deltaX, 25.0 + deltaY,
                30.0 + deltaX, 10.0 + deltaY
        );
        hexagon.setFill(Color.GREY);
        hexagon.setStrokeWidth(0);
        root.getChildren().add(hexagon);

        Ellipse e1 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 20, 25);
        Ellipse e2 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 20, 15);
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

