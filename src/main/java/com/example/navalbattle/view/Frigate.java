package com.example.navalbattle.view;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class Frigate extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
        Polygon frigate = new Polygon();
        frigate.getPoints().addAll(
                50.0, 50.0,   // Punto inferior izquierdo
                75.0, 50.0,   // Punto inferior derecho
                85.0, 37.5,   // Punto superior derecho
                75.0, 25.0,   // Punto superior
                50.0, 25.0    // Punto superior izquierdo
        );

        frigate.setFill(Color.GREY);   // Color de relleno
        frigate.setStroke(Color.BLACK); // Color del borde
        frigate.setStrokeWidth(1.0);    // Grosor del borde

        Line line1 = Shape.lineStyle(50, 50, 75, 50, Color.BLACK, 2.0);   //  inferior
        Line line2 = Shape.lineStyle(50, 50, 50, 25, Color.BLACK, 2.0);   //  izquierda
        Line line3 = Shape.lineStyle(50, 25, 75, 25, Color.BLACK, 2.0);   //  superior
        Line line4 = Shape.lineStyle(75, 25, 85, 37.5, Color.BLACK, 2.0); //  diagonal superior
        Line line5 = Shape.lineStyle(85, 37.5, 75, 50, Color.BLACK, 2.0); //  diagonal inferior
        Line line6 =  Shape.lineStyle(60, 42, 74, 42, Color.WHITE, 4.0);   // Línea inferior
        Line line7 =  Shape.lineStyle(54, 45, 54, 30, Color.BLUE, 2.0);   //  izquierda
        Line line8 =  Shape.lineStyle(60, 33, 74, 33, Color.WHITE, 4.0);   //  superior
        Line line9 =  Shape.lineStyle(49, 41, 48, 41, Color.BLACK, 4.0);
        Line line10 =  Shape.lineStyle(49, 34, 48, 34, Color.BLACK, 4.0);

        root.getChildren().addAll(line1, line2, line3, line4, line5, frigate, line6, line7, line8, line9, line10);

        Scene scene = new Scene(root, 150, 100);
        stage.setScene(scene);
        stage.setTitle("Fragata");
        stage.show();


    }
}