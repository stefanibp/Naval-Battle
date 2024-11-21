/*package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class Destroyer implements IShip {

    private int currentRotation = 0;

    @Override
    public String getName() {
        return "Destroyer";
    }

    @Override
    public int getSize() {
        return 2; // El tamaño en celdas
    }

    @Override
    public StackPane render() {
        StackPane root = new StackPane();

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                100.0, 40.0,
                83.0, 25.0,
                83.0, 55.0);

        triangle1.setFill(Color.BLACK);
        root.getChildren().addAll(triangle1);

        triangle1.setTranslateX(35);
        triangle1.setTranslateY(0);

        Rectangle body = Shape.square(20, 25, 70, 30, Color.BLACK);
        body.setArcWidth(20);
        body.setArcHeight(40);
        root.getChildren().add(body);

        double squareSize = 10;
        Rectangle square1 = Shape.squareStyle(35, 28, squareSize, squareSize, Color.WHITE);
        Rectangle square2 = Shape.squareStyle(48, 28, squareSize, squareSize, Color.WHITE);
        Rectangle square3 = Shape.squareStyle(35, 42, squareSize, squareSize, Color.WHITE);
        Rectangle square4 = Shape.squareStyle(48, 42, squareSize, squareSize, Color.WHITE);

        root.getChildren().addAll(square1, square2, square3, square4);

        square1.setTranslateX(-15);
        square1.setTranslateY(6);

        square2.setTranslateX(-15);
        square2.setTranslateY(-6);

        square3.setTranslateX(-2);
        square3.setTranslateY(6);

        square4.setTranslateX(-2);
        square4.setTranslateY(-6);

        double deltaX = 20;
        double deltaY = 22;

        Polygon hexagon = new Polygon(
                50.0 + deltaX, 5.0 + deltaY,
                60.0 + deltaX, 10.0 + deltaY,
                60.0 + deltaX, 25.0 + deltaY,
                50.0 + deltaX, 30.0 + deltaY,
                40.0 + deltaX, 25.0 + deltaY,
                40.0 + deltaX, 10.0 + deltaY
        );

        hexagon.setFill(Color.WHITE);
        hexagon.setStrokeWidth(0);
        root.getChildren().add(hexagon);

        hexagon.setTranslateX(15);

        Ellipse e1 = Shape.circleStyle(3, 3, Color.WHITE, Color.BLACK, 1.0, 30, 45);

        Ellipse e2 = Shape.circleStyle(3, 3, Color.WHITE, Color.BLACK, 1.0, 30, 35);

        root.getChildren().addAll(e1, e2);

        e1.setTranslateX(-25);
        e1.setTranslateY(-6);

        e2.setTranslateX(-25);
        e2.setTranslateY(6);

        return root;
    }
*/

package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class Destroyer implements IShip {

    @Override
    public String getName() {
        return "Destroyer";
    }

    @Override
    public int getSize() {
        return 2; // El tamaño en celdas
    }

    @Override
    public Pane render() {
        Pane root = new Pane();
        root.setPrefSize(80, 40); // Establece las dimensiones del Pane

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                60.0, 20.0,
                43.0, 5.0,
                43.0, 35.0
        );
        triangle1.setFill(Color.DARKGRAY);
        root.getChildren().add(triangle1);

        Rectangle body = Shape.square(10, 5, 60, 30, Color.DARKGRAY);
        body.setArcWidth(20);
        body.setArcHeight(40);
        root.getChildren().add(body);

        double squareSize = 10;
        Rectangle square1 = Shape.squareStyle(25, 8, squareSize, squareSize, Color.GREY);
        Rectangle square2 = Shape.squareStyle(38, 8, squareSize, squareSize, Color.GREY);
        Rectangle square3 = Shape.squareStyle(25, 22, squareSize, squareSize, Color.GREY);
        Rectangle square4 = Shape.squareStyle(38, 22, squareSize, squareSize, Color.GREY);
        root.getChildren().addAll(square1, square2, square3, square4);

        double deltaX = 10;
        double deltaY = 2;

        Polygon hexagon = new Polygon(
                40.0 + deltaX, 5.0 + deltaY,
                50.0 + deltaX, 10.0 + deltaY,
                50.0 + deltaX, 25.0 + deltaY,
                40.0 + deltaX, 30.0 + deltaY,
                30.0 + deltaX, 25.0 + deltaY,
                30.0 + deltaX, 10.0 + deltaY
        );
        hexagon.setFill(Color.GREY);
        hexagon.setStrokeWidth(0);
        root.getChildren().add(hexagon);

        Ellipse e1 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 20, 25);
        Ellipse e2 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 20, 15);
        root.getChildren().addAll(e1, e2);

        return root;
    }
  
      @Override
      public IShip clone(){
          return new Destroyer();
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

