package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;

public class Sink implements IMove {

    @Override
    public void execute() {
        System.out.println("¡Hundido! El jugador puede volver a disparar.");
    }

    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Crear un degradado de colores para la llama
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1, true, null,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.ORANGE),
                new Stop(1, Color.YELLOW)
        );

        // Llama de hundido
        Polygon flame = new Polygon();
        flame.getPoints().addAll(
                10.0, 0.0,
                15.0, 10.0,
                20.0, 5.0,
                25.0, 15.0,
                30.0, 5.0,
                35.0, 10.0,
                40.0, 0.0,
                35.0, 20.0,
                25.0, 30.0,
                15.0, 20.0
        );
        flame.setFill(gradient);

        pane.getChildren().add(flame);
        return pane;
    }
}

