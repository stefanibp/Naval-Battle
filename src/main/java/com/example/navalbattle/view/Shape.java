package com.example.navalbattle.view;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Utility class for creating and styling various shapes used in the game.
 * This class provides methods for creating and customizing the appearance
 * of lines, rectangles, circles, and ellipses.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Shape {

    /**
     * Creates a styled Line object with the specified coordinates, stroke color, and stroke width.
     *
     * @param v the starting x-coordinate of the line.
     * @param v1 the starting y-coordinate of the line.
     * @param v2 the ending x-coordinate of the line.
     * @param v3 the ending y-coordinate of the line.
     * @param strokeColor the color of the line's stroke.
     * @param strokeWidth the width of the line's stroke.
     * @return a Line object with the specified styles applied.
     */
    public static Line lineStyle(double v, double v1 , double v2, double v3, Paint strokeColor, double strokeWidth) {
        Line line = new Line(v, v1, v2, v3);
        line.setStroke(strokeColor);
        line.setStrokeWidth(strokeWidth);
        return line;
    }

    /**
     * Creates a styled Rectangle object with the specified position, size, and fill color.
     *
     * @param x the x-coordinate of the top-left corner of the rectangle.
     * @param y the y-coordinate of the top-left corner of the rectangle.
     * @param size1 the width of the rectangle.
     * @param size2 the height of the rectangle.
     * @param fillColor the color used to fill the rectangle.
     * @return a Rectangle object with the specified styles applied.
     */
    public static Rectangle squareStyle(double x, double y, double size1, double size2, Paint fillColor){
        Rectangle rectangle = new Rectangle(x, y, size1, size2);
        rectangle.setFill(fillColor);
        return rectangle;
    }

    /**
     * Creates a Rectangle object with the specified position, size, and fill color.
     *
     * @param v the x-coordinate of the top-left corner of the rectangle.
     * @param v1 the y-coordinate of the top-left corner of the rectangle.
     * @param v2 the width of the rectangle.
     * @param v3 the height of the rectangle.
     * @param fillColor the color used to fill the rectangle.
     * @return a Rectangle object with the specified styles applied.
     */
    public static Rectangle square(double v, double v1, double v2, double v3, Paint fillColor){
        Rectangle rectangle = new Rectangle(v, v1, v2, v3);
        rectangle.setFill(fillColor);
        return rectangle;
    }

    /**
     * Creates a styled Ellipse object with the specified radius, fill color, stroke color,
     * stroke width, and center position.
     *
     * @param v the radius along the x-axis of the ellipse.
     * @param v1 the radius along the y-axis of the ellipse.
     * @param fillColor the color used to fill the ellipse.
     * @param strokeColor the color of the ellipse's stroke.
     * @param strokeWidth the width of the ellipse's stroke.
     * @param centerX the x-coordinate of the center of the ellipse.
     * @param centerY the y-coordinate of the center of the ellipse.
     * @return an Ellipse object with the specified styles applied.
     */
    public static Ellipse circleStyle(double v, double v1, Paint fillColor, Paint strokeColor, double strokeWidth, double centerX, double centerY){
        Ellipse ellipse = new Ellipse(v, v1);
        ellipse.setFill(fillColor);
        ellipse.setStroke(strokeColor);
        ellipse.setStrokeWidth(strokeWidth);
        ellipse.setCenterX(centerX);
        ellipse.setCenterY(centerY);
        return ellipse;
    }

    /**
     * Creates a styled Ellipse object with the specified radii and fill color.
     *
     * @param v the radius along the x-axis of the ellipse.
     * @param v1 the radius along the y-axis of the ellipse.
     * @param v2 the second radius along the x-axis of the ellipse.
     * @param v3 the second radius along the y-axis of the ellipse.
     * @param fillColor the color used to fill the ellipse.
     * @return an Ellipse object with the specified styles applied.
     */
    public static Ellipse ellipseStyle(double v, double v1, double v2, double v3, Paint fillColor){
        Ellipse ellipse = new Ellipse(v, v1, v2, v3);
        ellipse.setFill(fillColor);
        return ellipse;
    }
}
