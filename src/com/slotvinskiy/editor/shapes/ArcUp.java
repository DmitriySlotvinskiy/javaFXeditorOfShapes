package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class ArcUp extends Arc {

    private static final int START_ANGLE = 360;
    public static final String SHAPE_TYPE = "ArcUp";

    public ArcUp(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        super(board, displayDriver, x, y, START_ANGLE, selected);
        setAsSelected();
    }

    public ArcUp(ArcUp source) {
        super(source.board, source.displayDriver, source.x, source.y, START_ANGLE, source.selected);
        size = source.size;
        color = source.color;
        colorCode = source.colorCode;
    }

    public ArcUp(Board board, DisplayDriver displayDriver, double x, double y, int size, boolean selected, int colorCode) {
        super(board, displayDriver, x, y, START_ANGLE, selected);
        this.size = size;
        setAsDeselected();
        this.colorCode = colorCode;
    }

    @Override
    public boolean isHit(int x, int y) {
        if (x >= this.x && x <= (this.x + size) && y >= this.y && y <= (this.y + size / 2.0)) {
            double xCenter = this.x + size / 2.0;
            double yCenter = this.y + size / 2.0;
            if (Math.hypot((Math.abs(xCenter - x)), (Math.abs(yCenter - y))) <= size / 2.0) {
                return true;
            }
        }
        return false;
    }

    public String getType() {
        return SHAPE_TYPE;
    }
}
