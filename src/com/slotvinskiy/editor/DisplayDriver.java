package com.slotvinskiy.editor;

public interface DisplayDriver {

    void setColor(String hex);

    double getWidth();

    double getHeight();

    void drawCircle(double x, double y, double diameter);

    void drawSelectedCircle(double x, double y, double diameter);

    void drawSquare(double x, double y, double size);

    void drawSelectedSquare(double x, double y, double size);

    public void drawArc1(double x, double y, double w, double h, double startAngle, double arcExtent);

    public void drawSelectedArc1(double x, double y, double w, double h, double startAngle, double arcExtent);

    void setLineWidth(int x);
}
