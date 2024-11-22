package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Miss implements IMove {

    @Override
    public void execute() {
        System.out.println("¡Agua! Pasa el turno al oponente.");
    }

    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Efecto visual con una "X" (moverla hacia la izquierda y arriba)
        Line line1 = new Line(10 - 5, 10 - 5, 40 - 5, 40 - 5); // Mover 5 píxeles a la izquierda y arriba
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(2);

        Line line2 = new Line(40 - 5, 10 - 5, 10 - 5, 40 - 5); // Mover 5 píxeles a la izquierda y arriba
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(2);

        pane.getChildren().addAll(line1, line2);
        return pane;
    }
}
