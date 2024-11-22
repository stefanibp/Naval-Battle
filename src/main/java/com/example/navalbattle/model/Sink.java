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

        // Calcular la nueva escala para hacer la figura un poco más grande
        double scale = Math.min(48.0 / 40.0, 48.0 / 40.0); // Escala ligeramente mayor

        // Crear un degradado de colores para la llama
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1, true, null,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.ORANGE),
                new Stop(1, Color.YELLOW)
        );

        // Llama de hundido: escalado y movida hacia la izquierda
        Polygon flame = new Polygon();
        flame.getPoints().addAll(
                (10.0 - 7.0) * scale, 0.0 * scale,  // Mover hacia la izquierda y agrandar
                (15.0 - 7.0) * scale, 10.0 * scale,
                (20.0 - 7.0) * scale, 5.0 * scale,
                (25.0 - 7.0) * scale, 15.0 * scale,
                (30.0 - 7.0) * scale, 5.0 * scale,
                (35.0 - 7.0) * scale, 10.0 * scale,
                (40.0 - 7.0) * scale, 0.0 * scale,
                (35.0 - 7.0) * scale, 20.0 * scale,
                (25.0 - 7.0) * scale, 30.0 * scale,
                (15.0 - 7.0) * scale, 20.0 * scale
        );
        flame.setFill(gradient);

        pane.getChildren().add(flame);
        return pane;
    }
}
