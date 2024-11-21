package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
        // Creamos un pane con dimensiones de 40px de largo y 40px de ancho
        StackPane root = new StackPane();
        root.setPrefSize(40, 40); // Establecemos el tamaño del pane

        // Ajustamos las coordenadas para que el polígono ocupe todo el espacio

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                10.0, 10.0,   // Punto 1
                30.0, 10.0,   // Punto 2
                35.0, 20.0,   // Punto 3
                30.0, 30.0,   // Punto 4
                10.0, 30.0    // Punto 5
        );

        polygon.setFill(Color.BLACK);
        polygon.setStroke(Color.BLACK);
        root.getChildren().addAll(polygon);

        // Dibujamos las líneas ajustadas dentro del espacio de 40px x 40px
        Line line1 = Shape.lineStyle(10, 10, 10, 30, Color.DARKGRAY, 2.0);
        Line line2 = Shape.lineStyle(12, 17, 28, 17, Color.GREY, 4.0);
        Line line3 = Shape.lineStyle(13, 20, 13, 28, Color.GREY, 2.0);
        Line line4 = Shape.lineStyle(12, 25, 28, 25, Color.GREY, 4.0);
        Line line5 = Shape.lineStyle(9, 22, 8, 22, Color.DARKGRAY, 4.0);
        Line line6 = Shape.lineStyle(9, 19, 8, 19, Color.DARKGRAY, 4.0);

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