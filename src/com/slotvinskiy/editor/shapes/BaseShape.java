package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import com.slotvinskiy.editor.MyColor;

import java.util.Random;

public abstract class BaseShape implements Shape {

    protected double x;
    protected double y;
    protected int size = 50;
    protected Board board;
    protected DisplayDriver displayDriver;
    protected MyColor color;
    protected boolean selected = false;

    public BaseShape(Board board, DisplayDriver displayDriver, double x, double y) {
        this.board = board;
        this.displayDriver = displayDriver;
        this.x = x;
        this.y = y;
        Random random = new Random();
        int colorsNum = MyColor.values().length;
        color = MyColor.values()[random.nextInt(colorsNum)];
    }

    public BaseShape(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        this(board, displayDriver,  x,  y);
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
}
