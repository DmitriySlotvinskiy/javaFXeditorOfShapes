package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class SquareShape extends BaseShape {

    public SquareShape(Board board, DisplayDriver displayDriver, double x, double y) {
        super(board, displayDriver, x, y);
    }

    public SquareShape(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        super(board, displayDriver, x, y, selected);
    }

    @Override
    public void draw() {
        displayDriver.setColor(color.toHex());
        if (selected) {
            displayDriver.setLineWidth(10);
            displayDriver.drawSelectedSquare(x, y, size);
            displayDriver.setLineWidth(3);
        } else {
            displayDriver.drawSquare(x, y, size);
        }
    }

}