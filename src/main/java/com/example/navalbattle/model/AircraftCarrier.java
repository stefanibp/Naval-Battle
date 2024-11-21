package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class AircraftCarrier implements IShip {
    private int currentRotation = 0;
    private int rotationAngle = 0;  // Ángulo de rotación (0, 90, 180, 270)

    @Override
    public String getName() {
        return "Aircraft Carrier";
    }

    @Override
    public int getSize() {
        return 4; // El tamaño en celdas
    }

    @Override
    public Pane render() {
        // Creamos un pane con dimensiones de 160px de largo y 40px de ancho
        Pane root = new Pane();
        root.setPrefSize(160, 40); // Establecemos el tamaño del pane

        // Ajustamos las coordenadas para centrar el portaaviones sin dejar espacio
        double startX = 0;  // Coordenada X de inicio
        double startY = 0;  // Coordenada Y de inicio

        // Dibujamos las partes del portaaviones, ahora ajustados para no dejar espacios vacíos
        Ellipse back = new Ellipse(startX + 10, startY + 20, 10, 15);  // Ajustamos la posición
        back.setFill(Color.DARKGRAY);
        root.getChildren().add(back);

        Rectangle body = Shape.square(startX + 0, startY + 5, 120, 30, Color.DARKGRAY);  // Ajustamos la posición
        root.getChildren().add(body);

        Ellipse front = Shape.ellipseStyle(startX + 120, startY + 20, 25, 15, Color.DARKGRAY);  // Ajustamos la posición
        root.getChildren().add(front);

        Line runway = new Line(startX + 15, startY + 30, startX + 135, startY + 5);  // Ajustamos la posición
        runway.setStroke(Color.WHITE);
        runway.setStrokeWidth(2);
        runway.getStrokeDashArray().addAll(6.0, 10.0);
        root.getChildren().add(runway);

        Rectangle tower = Shape.square(startX + 120, startY + 10, 30, 10, Color.GRAY);  // Ajustamos la posición
        root.getChildren().add(tower);

        Rectangle window1 = Shape.square(startX + 135, startY + 2, 3, 3, Color.LIGHTGRAY);
        Rectangle window2 = Shape.square(startX + 142, startY + 2, 3, 3, Color.LIGHTGRAY);
        root.getChildren().addAll(window1, window2);

        Polygon airplane1 = createAirplane(startX + 25, startY + 10);  // Ajustamos la posición
        Polygon airplane2 = createAirplane(startX + 90, startY + 20);  // Ajustamos la posición
        root.getChildren().addAll(airplane1, airplane2);

        // Aplicar la rotación al Pane
        Rotate rotate = new Rotate();
        rotate.setAngle(rotationAngle);  // Establecemos el ángulo de rotación
        rotate.setPivotX(startX + 80);   // Ajustamos el pivote de rotación
        rotate.setPivotY(startY + 20);   // Ajustamos el pivote de rotación
        root.getTransforms().add(rotate);

        return root;
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

    @Override
    public IShip clone(){
        return new AircraftCarrier();
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
