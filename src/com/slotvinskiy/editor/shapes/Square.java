package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class Square extends BaseShape {

    public Square(Board board, DisplayDriver displayDriver, double x, double y) {
        super(board, displayDriver, x, y);
    }

    @Override
    public void draw() {
        displayDriver.setColor("FF0");
        displayDriver.drawCircle(x, y, size);
    }
}