package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class Square extends BaseShape {

    public Square(Board board, DisplayDriver displayDriver, double x, double y) {
        super(board, displayDriver, x, y);
    }

    public Square(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        super(board, displayDriver, x, y, selected);
    }

    @Override
    public void draw() {
        displayDriver.setColor("FF0");
        if (selected) {
            displayDriver.setLineWidth(10);
            displayDriver.drawSelectedSquare(x, y, size);
            displayDriver.setLineWidth(3);
        } else {
            displayDriver.drawSquare(x, y, size);
        }
    }

}