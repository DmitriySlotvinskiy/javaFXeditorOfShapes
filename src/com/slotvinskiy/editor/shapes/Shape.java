package com.slotvinskiy.editor.shapes;

public interface Shape {

    void draw();

    void moveUp();
    void moveDown();
    void moveRight();
    void moveLeft();
    void moveWithMouse(double offSetX, double offSetY);

    void decreaseSize();
    void increaseSize();

    double getX();

    double getY();

    void setSelection(boolean b);
}
