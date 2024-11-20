package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sink implements IMove {

    @Override
    public void execute() {
        System.out.println("¡Hundido! El jugador puede volver a disparar.");
    }

    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Barco completo con un borde negro (Debe aparecer la llama de hundido)
        Rectangle rectangle = new Rectangle(10, 10, 50, 20);
        rectangle.setFill(Color.GRAY);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);

        pane.getChildren().add(rectangle);
        return pane;
    }
}
