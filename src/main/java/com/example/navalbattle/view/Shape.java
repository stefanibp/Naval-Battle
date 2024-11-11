package com.example.navalbattle.view;

import javafx.application.Application;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.EventListener;

public class Shape  {

    public static Line lineStyle(double v, double v1 , double v2, double  v3, Paint strokeColor, double strokeWidth) {
        Line line = new Line(v, v1, v2, v3);

        line.setStroke(strokeColor);

        line.setStrokeWidth(strokeWidth);

        return line;
    }

    public static Rectangle squareStyle(double x, double y, double size1, double size2, Paint fillColor){

        Rectangle rectangle = new Rectangle(x, y, size1, size2);

        rectangle.setFill(fillColor);

        return rectangle;
    }

    public static Rectangle square(double v, double v1, double v2, double v3, Paint fillColor){
        Rectangle rectangle = new Rectangle(v, v1, v2, v3);
        rectangle.setFill(fillColor);
        return rectangle;
    }

    public static Ellipse circleStyle(double v, double v1, Paint fillColor, Paint strokeColor, double strokeWidth, double centerX, double centerY){

        Ellipse ellipse = new Ellipse(v, v1);

        ellipse.setFill(fillColor);
        ellipse.setStroke(strokeColor);
        ellipse.setStrokeWidth(strokeWidth);
        ellipse.setCenterX(centerX);
        ellipse.setCenterY(centerY);

        return ellipse;
    }

    public static Ellipse ellipseStyle(double v, double v1, double v2, double v3, Paint fillColor){
        Ellipse ellipse = new Ellipse(v, v1, v2, v3);

        ellipse.setFill(fillColor);

        return ellipse;
    }

}
