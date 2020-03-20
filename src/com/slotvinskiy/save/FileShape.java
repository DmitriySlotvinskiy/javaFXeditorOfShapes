package com.slotvinskiy.save;

import com.slotvinskiy.editor.shapes.Shape;

public class FileShape {
    private double x;
    private double y;
    private int size;
    private String shapeType;

    public FileShape (Shape shape) {
        this.x = shape.getX();
        this.y = shape.getY();
        this.size = shape.getSize();
        this.shapeType = shape.getType();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public String getShapeType() {
        return shapeType;
    }
}
