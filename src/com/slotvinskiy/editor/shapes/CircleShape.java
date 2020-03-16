package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class CircleShape extends BaseShape {

    public CircleShape(Board board, DisplayDriver displayDriver, double x, double y) {
        super(board, displayDriver, x, y);
    }

    public CircleShape(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        super(board, displayDriver, x, y, selected);
    }

    @Override
    public void draw() {
        displayDriver.setColor(color.toHex());
        if (selected) {
            displayDriver.setLineWidth(10);
            displayDriver.drawSelectedCircle(x, y, size);
            displayDriver.setLineWidth(3);
        } else {
            displayDriver.drawCircle(x, y, size);
        }
    }
}
