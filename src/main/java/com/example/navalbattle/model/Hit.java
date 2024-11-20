package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Hit implements IMove {

    @Override
    public void execute() {
        System.out.println("¡Tocado! El jugador puede volver a disparar.");
    }

    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Efecto visual con un círculo rojo (debe ir la bomba)
        Circle circle = new Circle(25, 25, 15, Color.RED);
        pane.getChildren().add(circle);
        return pane;
    }
}
