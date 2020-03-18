package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class CircleShape extends BaseShape {

    public CircleShape(CircleShape source) {
        super(source.board, source.displayDriver, source.x, source.y);

        size = source.size;
        color = source.color;
    }

    public CircleShape(Board board, DisplayDriver displayDriver, int x, int y, boolean selected) {
        super(board, displayDriver, x, y, selected);
    }

    @Override
    public void draw() {
        displayDriver.setColor(color.toHex());
        if (selected) {
            setAsSelected();
            displayDriver.drawSelectedCircle(x, y, size);
        } else {
            displayDriver.drawCircle(x, y, size);
        }
    }


    @Override
    public boolean isHit(int x, int y) {
        if (x >= this.x && x <= (this.x + size) && y >= this.y && y <= (this.y + size)) {
            double xCenter = this.x + size / 2.0;
            double yCenter = this.y + size / 2.0;
            if (Math.hypot((Math.abs(xCenter - x)), (Math.abs(yCenter - y))) <= size / 2.0) {
                return true;
            }
        }
        return false;
    }
}
