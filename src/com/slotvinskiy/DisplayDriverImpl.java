package com.slotvinskiy;

import com.slotvinskiy.editor.DisplayDriver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;


public class DisplayDriverImpl implements DisplayDriver {

    private final GraphicsContext gc;

    public DisplayDriverImpl(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void setColor(String hex) {
        gc.setFill(Color.web(hex));
        gc.setStroke(Color.web(hex));
    }


    @Override
    public double getWidth() {
        return gc.getCanvas().getWidth();
    }

    @Override
    public double getHeight() {
        return gc.getCanvas().getHeight();
    }

    @Override
    public void drawCircle(double x, double y, double diameter) {
        gc.fillOval(x, y, diameter, diameter);
    }

    @Override
    public void drawSelectedCircle(double x, double y, double diameter) {
        gc.strokeOval(x, y, diameter, diameter);

    }

    @Override
    public void drawSquare(double x, double y, double size) {
        gc.fillRect(x, y, size, size);
    }

    @Override
    public void drawSelectedSquare(double x, double y, double size) {
        gc.strokeRect(x, y, size, size);
    }

    @Override
    public void drawArc1(double x, double y, double w, double h, double startAngle, double arcExtent) {
        gc.fillArc(x, y, w, h, startAngle, arcExtent, ArcType.ROUND);

    }

    @Override
    public void drawSelectedArc1(double x, double y, double w, double h, double startAngle, double arcExtent) {
        gc.strokeArc(x, y, w, h, startAngle, arcExtent, ArcType.ROUND);
    }

    @Override
    public void setLineWidth(int x) {
        gc.setLineWidth(x);
    }


}
