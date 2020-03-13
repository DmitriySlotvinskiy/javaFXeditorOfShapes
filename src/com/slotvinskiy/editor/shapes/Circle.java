package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;

public class Circle extends BaseShape {

    public Circle(Board board, DisplayDriver displayDriver, double x, double y) {
        super(board, displayDriver, x, y);
    }

    @Override
    public void draw() {

//        displayDriver.setColor(color.toHex());
        displayDriver.setColor("#F00");
        displayDriver.drawCircle(x, y, size);
    }
}
