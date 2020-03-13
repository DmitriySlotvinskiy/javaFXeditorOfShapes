package com.slotvinskiy.editor.shapes;

public interface Shape {

    void draw();

    void moveUp();
    void moveDown();
    void moveRight();
    void moveLeft();

    double getX();

    double getY();

    void setSelection(boolean b);
}
