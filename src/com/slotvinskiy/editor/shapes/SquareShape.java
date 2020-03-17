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
            setAsSelected();
            displayDriver.drawSelectedSquare(x, y, size);
        } else {
            displayDriver.drawSquare(x, y, size);
        }
    }


    @Override
    public boolean isHit(int x, int y) {
        if (x >= this.x && x <= (this.x + size) && y >= this.y && y <= (this.y + size)) {
            return true;
        } else {
            return false;
        }
    }
}