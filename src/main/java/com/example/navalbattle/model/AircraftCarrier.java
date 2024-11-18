package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class AircraftCarrier implements IShip {

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
        Pane root = new Pane();

        Ellipse back = new Ellipse(63, 150, 10, 15);
        back.setFill(Color.DARKGRAY);
        root.getChildren().add(back);

        Rectangle body = Shape.square(60, 135, 120, 30, Color.DARKGRAY);
        root.getChildren().add(body);

        Ellipse front = Shape.ellipseStyle(180, 150, 25, 15, Color.DARKGRAY);
        root.getChildren().add(front);

        Line runway = new Line(70, 160, 150, 140);
        runway.setStroke(Color.WHITE);
        runway.setStrokeWidth(2);
        runway.getStrokeDashArray().addAll(6.0, 10.0);
        root.getChildren().add(runway);

        Rectangle tower = Shape.square(160, 145, 30, 10, Color.GRAY);
        root.getChildren().add(tower);

        Rectangle window1 = Shape.square(175, 137, 3, 3, Color.LIGHTGRAY);
        Rectangle window2 = Shape.square(182, 137, 3, 3, Color.LIGHTGRAY);
        root.getChildren().addAll(window1, window2);

        Polygon airplane1 = createAirplane(80, 140);
        Polygon airplane2 = createAirplane(130, 155);
        root.getChildren().addAll(airplane1, airplane2);

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
}
