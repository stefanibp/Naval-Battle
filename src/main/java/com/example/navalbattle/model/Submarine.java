package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class Submarine implements IShip {

    private int currentRotation = 0;

    @Override
    public String getName() {
        return "Submarine";
    }

    @Override
    public int getSize() {
        return 3; // El tamaño en celdas
    }

    @Override
    public Pane render() {
        // Creamos un pane con dimensiones de 120px de largo y 40px de ancho
        Pane root = new Pane();
        root.setPrefSize(120, 40); // Establecemos el tamaño del pane

        // Ajustamos las coordenadas de inicio para ocupar todo el espacio
        double startX = 0;  // Coordenada X de inicio
        double startY = 0;  // Coordenada Y de inicio

        // Dibujamos el cuerpo del submarino con las coordenadas ajustadas
        Rectangle body = Shape.square(startX + 15, startY + 10, 90, 20, Color.DARKGRAY);

        body.setArcWidth(40);
        body.setArcHeight(40);
        root.getChildren().add(body);

        // Dibujamos el triángulo frontal del submarino
        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                startX + 20.0, startY + 15.0,
                startX + 5.0, startY + 20.0,
                startX + 20.0, startY + 25.0);
        triangle1.setFill(Color.DARKGRAY);
        root.getChildren().addAll(triangle1);

        // Dibujamos la hélice trasera
        Ellipse propeller1 = new Ellipse(startX + 13, startY + 20, 3, 8);
        propeller1.setFill(Color.DARKGRAY);
        root.getChildren().addAll(propeller1);

        // Dibujamos las escotillas
        Ellipse hatch1 = Shape.ellipseStyle(startX + 30, startY + 20, 3, 3, Color.WHITE);
        Ellipse hatch2 = Shape.ellipseStyle(startX + 50, startY + 20, 3, 3, Color.WHITE);
        Ellipse hatch3 = Shape.ellipseStyle(startX + 85, startY + 20, 3, 3, Color.WHITE);
        root.getChildren().addAll(hatch1, hatch2, hatch3);

        // Dibujamos las aletas traseras
        Line fins1 = Shape.lineStyle(startX + 85, startY + 8, startX + 85, startY + 10, Color.DARKGRAY, 4.0);
        Line fins2 = Shape.lineStyle(startX + 85, startY + 32, startX + 85, startY + 30, Color.DARKGRAY, 4.0);
        root.getChildren().addAll(fins1, fins2);

        return root;
    }
  
    @Override
    public IShip clone(){
        return new Submarine();
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
