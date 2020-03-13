package com.slotvinskiy.editor.shapes;
import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class Arc1 extends BaseShape{

    private final int START_ANGLE = 360;
    private final int ARC_EXTENT = 180;

    public Arc1(Board board, DisplayDriver displayDriver, double x, double y) {
        super(board, displayDriver, x, y);
    }

    public Arc1(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        super(board, displayDriver, x, y, selected);
    }

    @Override
    public void draw() {
        displayDriver.setColor(color.toHex());
        if (selected) {
            displayDriver.setLineWidth(10);
            displayDriver.drawSelectedArc1(x, y, size, size, START_ANGLE, ARC_EXTENT);
            displayDriver.setLineWidth(3);
        } else {
            displayDriver.drawArc1(x, y, size, size, START_ANGLE, ARC_EXTENT);
        }
    }
}
