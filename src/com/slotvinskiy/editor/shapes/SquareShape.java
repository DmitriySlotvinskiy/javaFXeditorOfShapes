package com.slotvinskiy.editor.shapes;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import com.slotvinskiy.editor.MyColor;

public class SquareShape extends BaseShape {


    public static final String SHAPE_TYPE = "Square";

    public SquareShape(SquareShape source) {
        super(source.board, source.displayDriver, source.x, source.y);
        size = source.size;
        color = source.color;
        colorCode = source.colorCode;
    }

    public SquareShape(Board board, DisplayDriver displayDriver, double x, double y, boolean selected) {
        super(board, displayDriver, x, y, selected);
        setAsSelected();
    }

    public SquareShape(Board board, DisplayDriver displayDriver, double x, double y, int size, boolean selected, int colorCode) {
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

    public String getType() {
        return SHAPE_TYPE;
    }
}