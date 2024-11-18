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

        // Efecto visual con una "X" (editar con la figura correspondiente)
        Line line1 = new Line(10, 10, 40, 40);
        line1.setStroke(Color.BLUE);
        line1.setStrokeWidth(2);

        Line line2 = new Line(40, 10, 10, 40);
        line2.setStroke(Color.BLUE);
        line2.setStrokeWidth(2);

        pane.getChildren().addAll(line1, line2);
        return pane;
    }
}
