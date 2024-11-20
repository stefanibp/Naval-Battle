package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class AircraftCarrier implements IShip {
    private int currentRotation = 0;

    @Override
    public String getName() {
        return "Aircraft Carrier";
    }

    @Override
    public int getSize() {
        return 4; // El tamaño en celdas
    }

    @Override
    public StackPane render() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: lightgray;");

        Ellipse back = new Ellipse(180, 150, 25, 15);
        back.setFill(Color.BLACK);
        root.getChildren().add(back);
        back.setTranslateX(-55);
        back.setTranslateY(0);

        Rectangle body = Shape.square(60, 135, 120, 30, Color.BLACK);
        root.getChildren().add(body);

        Ellipse front = Shape.ellipseStyle(180, 150, 25, 15, Color.BLACK);
        root.getChildren().add(front);

        front.setTranslateX(55);
        front.setTranslateY(0);

        Line runway = new Line(70, 160, 150, 140);
        runway.setStroke(Color.WHITE);
        runway.setStrokeWidth(2);
        runway.getStrokeDashArray().addAll(6.0, 10.0);
        root.getChildren().add(runway);

        Rectangle tower = Shape.square(160, 145, 30, 10, Color.WHITE);
        root.getChildren().add(tower);

        tower.setTranslateX(50);
        tower.setTranslateY(5);

        Rectangle window1 = Shape.square(175, 137, 3, 3, Color.WHITE);
        Rectangle window2 = Shape.square(182, 137, 3, 3, Color.WHITE);
        root.getChildren().addAll(window1, window2);
        window1.setTranslateX(65);
        window1.setTranslateY(-10);
        window2.setTranslateX(55);
        window2.setTranslateY(-10);

        Polygon airplane1 = createAirplane(0, 0);
        Polygon airplane2 = createAirplane(0, 0);
        root.getChildren().addAll(airplane1, airplane2);

        airplane1.setTranslateX(-15);
        airplane1.setTranslateY(-10);

        airplane2.setTranslateX(-35);
        airplane2.setTranslateY(-10);
        return root;
    }

    private Polygon createAirplane(double x, double y) {
        Polygon airplane = new Polygon(
                x, y,
                x - 5, y + 10,
                x + 5, y + 10
        );
        airplane.setFill(Color.WHITE);
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
