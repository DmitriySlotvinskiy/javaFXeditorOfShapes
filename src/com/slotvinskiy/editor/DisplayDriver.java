package com.slotvinskiy.editor;

public interface DisplayDriver {

    void setColor(String hex);

    double getWidth();

    double getHeight();

    void drawCircle(double x, double y, double diameter);

    void drawSquare(double x, double y, double size);
}
