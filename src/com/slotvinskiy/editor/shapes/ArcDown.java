package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class ArcDown extends Arc {

    private static final int START_ANGLE = 180;

    public ArcDown(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        super(board, displayDriver, x, y, START_ANGLE, selected);
    }

    @Override
    public boolean isHit(int x, int y) {
        if (x >= this.x && x <= (this.x + size) && y >= this.y + size / 2.0 && y <= (this.y + size)) {
            double xCenter = this.x + size / 2.0;
            double yCenter = this.y + size / 2.0;
            if (Math.hypot((Math.abs(xCenter - x)), (Math.abs(yCenter - y))) <= size / 2.0) {
                return true;
            }
        }
        return false;
    }
}
