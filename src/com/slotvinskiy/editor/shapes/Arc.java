package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import com.slotvinskiy.editor.MyColor;

public abstract class Arc extends BaseShape {

    private final int ARC_EXTENT = 180;
    private int startAngle;

    public Arc(Board board, DisplayDriver displayDriver, double x, double y, int startAngle, boolean selected) {
        super(board, displayDriver, x, y, selected);
        this.startAngle = startAngle;
    }

    @Override
    public void draw() {
        color = MyColor.values()[colorCode];
        displayDriver.setColor(color.toHex());
        if (selected) {
            displayDriver.drawSelectedArc1(x, y, size, size, startAngle, ARC_EXTENT);
        } else {
            displayDriver.drawArc1(x, y, size, size, startAngle, ARC_EXTENT);
        }
    }
}
