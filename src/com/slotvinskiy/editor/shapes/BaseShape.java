package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import com.slotvinskiy.editor.MyColor;

import java.util.Random;

public abstract class BaseShape implements Shape{

    protected double x;
    protected double y;
    protected int size = 50;
    protected Board board;
    protected DisplayDriver displayDriver;
    protected MyColor color;

    public BaseShape(Board board, DisplayDriver displayDriver, double x, double y) {
        this.board = board;
        this.displayDriver = displayDriver;
        this.x = x;
        this.y = y;
        Random random = new Random();
        int colorsNum = MyColor.values().length;
        color = MyColor.values()[random.nextInt(colorsNum)];
    }

    @Override
    public void moveUp() {
        y--;
    }

    @Override
    public void moveDown() {
        y++;
    }

    @Override
    public void moveRight() {
        x++;
    }

    @Override
    public void moveLeft() {
        x--;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
