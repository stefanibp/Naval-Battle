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
        pane.setPrefSize(40, 40); // Tamaño del pane

        // Cuerpo de la bomba
        Circle body = new Circle(20, 20, 10, Color.BLACK); // Ajustar tamaño y posición

        // Mecha de la bomba
        Line fuse = new Line(20, 10, 20, 0);
        fuse.setStroke(Color.GRAY);
        fuse.setStrokeWidth(1);

        // Explosión en forma de estrella
        Polygon explosion = new Polygon();
        explosion.getPoints().addAll(
                20.0, 5.0,
                21.0, 8.0,
                24.0, 8.0,
                22.0, 10.0,
                24.0, 13.0,
                21.0, 13.0,
                20.0, 15.0,
                19.0, 13.0,
                16.0, 13.0,
                18.0, 10.0,
                16.0, 8.0,
                19.0, 8.0
        );
        explosion.setFill(Color.ORANGE);

        // Añadir elementos al pane
        pane.getChildren().addAll(body, fuse, explosion);
        return pane;
    }
}