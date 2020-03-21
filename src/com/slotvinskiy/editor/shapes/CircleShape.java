package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import com.slotvinskiy.editor.MyColor;

public class CircleShape extends BaseShape {

    public static final String SHAPE_TYPE = "Circle";

    public CircleShape(CircleShape source) {
        super(source.board, source.displayDriver, source.x, source.y);

        size = source.size;
        color = source.color;
        colorCode = source.colorCode;
    }

    public CircleShape(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        super(board, displayDriver, x, y, selected);
        if (selected) {
            setAsSelected();
        }
    }

    public CircleShape(Board board, DisplayDriver displayDriver, double x, double y, int size, boolean selected, int colorCode) {
        super(board, displayDriver, x, y, selected);
        this.size = size;
        if (selected) {
            setAsSelected();
        }
        this.colorCode = colorCode;
    }

    @Override
    public void draw() {
        color = MyColor.values()[colorCode];
        displayDriver.setColor(color.toHex());
        if (selected) {
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

    public String getType() {
        return SHAPE_TYPE;
    }
}
