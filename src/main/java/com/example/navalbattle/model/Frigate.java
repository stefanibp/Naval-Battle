package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

import javafx.geometry.Insets;

public class Frigate implements IShip {

    private int currentRotation = 0;

    @Override
    public String getName() {
        return "Frigate";
    }

    @Override
    public int getSize() {
        return 1; // El tamaño en celdas
    }

    @Override
    public StackPane render() {

        StackPane root = new StackPane();

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                50.0, 50.0,
                75.0, 50.0,
                85.0, 37.5,
                75.0, 25.0,
                50.0, 25.0
        );

        polygon.setFill(Color.BLACK);
        polygon.setStroke(Color.BLACK);
        root.getChildren().addAll(polygon);


        Line line1 = Shape.lineStyle(50, 50, 50, 25, Color.BLACK, 2.0);
        Line line2 =  Shape.lineStyle(60, 42, 74, 42, Color.WHITE, 4.0);
        Line line3 =  Shape.lineStyle(54, 45, 54, 30, Color.WHITE, 2.0);
        Line line4 =  Shape.lineStyle(60, 33, 74, 33, Color.WHITE, 4.0);
        Line line5 =  Shape.lineStyle(49, 41, 48, 41, Color.BLACK, 4.0);
        Line line6 =  Shape.lineStyle(49, 34, 48, 34, Color.BLACK, 4.0);

        root.getChildren().addAll(line1, line2, line3, line4, line5, line6);
        return root;
    }

    @Override
    public IShip clone(){
        return new Frigate();
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