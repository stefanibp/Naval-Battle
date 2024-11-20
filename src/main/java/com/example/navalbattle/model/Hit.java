package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Hit implements IMove {

    @Override
    public void execute() {
        System.out.println("¡Tocado! El jugador puede volver a disparar.");
    }

    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Cuerpo de la bomba
        Circle body = new Circle(25, 25, 15, Color.BLACK);

        // Mecha de la bomba
        Line fuse = new Line(25, 10, 25, 0);
        fuse.setStroke(Color.GRAY);
        fuse.setStrokeWidth(2);

        // Explosión en forma de estrella
        Polygon explosion = new Polygon();
        explosion.getPoints().addAll(
                25.0, -5.0,
                27.0, -2.0,
                30.0, -2.0,
                28.0, 0.0,
                30.0, 3.0,
                27.0, 3.0,
                25.0, 5.0,
                23.0, 3.0,
                20.0, 3.0,
                22.0, 0.0,
                20.0, -2.0,
                23.0, -2.0
        );
        explosion.setFill(Color.ORANGE);

        // Añadir elementos al pane
        pane.getChildren().addAll(body, fuse, explosion);
        return pane;
    }
}
