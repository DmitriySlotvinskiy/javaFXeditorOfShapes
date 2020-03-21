package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import com.slotvinskiy.editor.MyColor;

public abstract class BaseShape implements Shape {

    public final int DEFAULT_SIZE = 50;
    protected double x;
    protected double y;
    protected int size = DEFAULT_SIZE;
    protected Board board;
    protected DisplayDriver displayDriver;
    protected MyColor color;
    protected int colorCode;
    protected boolean selected = false;

    public BaseShape(Board board, DisplayDriver displayDriver, double x, double y) {
        this.board = board;
        this.displayDriver = displayDriver;
        this.x = x;
        this.y = y;
        color = MyColor.values()[colorCode];
    }

    public BaseShape(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        this(board, displayDriver, x, y);
        this.selected = selected;
    }

    @Override
    public void moveUp() {
        y -= 5;
    }

    @Override
    public void moveDown() {
        y += 5;
    }

    @Override
    public void moveRight() {
        x += 5;
    }

    @Override
    public void moveLeft() {
        x -= 5;
    }

    @Override
    public void moveWithMouse(double offSetX, double offSetY) {
        x += -offSetX;
        y += -offSetY;
    }

    @Override
    public void decreaseSize() {
        size--;
    }

    @Override
    public void increaseSize() {
        size++;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setSelection(boolean bool) {
        selected = bool;
    }

    public void setAsSelected() {
        displayDriver.setLineWidthAsSelected();
        setSelection(true);
    }

    public void setAsDeselected() {
        displayDriver.setLineWidthAsDeselected();
        setSelection(false);
    }

    public int getSize() {
        return size;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void changeColor(int currentColorCode) {
        this.colorCode = currentColorCode;
    }
}
