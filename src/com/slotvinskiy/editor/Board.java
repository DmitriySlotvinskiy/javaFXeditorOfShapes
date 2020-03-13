package com.slotvinskiy.editor;

import com.slotvinskiy.editor.shapes.Arc1;
import com.slotvinskiy.editor.shapes.Circle;
import com.slotvinskiy.editor.shapes.Shape;
import com.slotvinskiy.editor.shapes.Square;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int START_X = 400;
    private final int START_Y = 300;


    private final DisplayDriver displayDriver;
    private final List<Shape> movingShapes = new ArrayList<>();
    private final List<Shape> staticalShapes = new ArrayList<>();

    public Board(DisplayDriver displayDriver) {
        this.displayDriver = displayDriver;
        movingShapes.add(new Circle(this, displayDriver, START_X, START_Y, true));
    }

    public void drawFrame() {
        for (Shape shape : movingShapes) {
            shape.draw();
        }
        for (Shape shape : staticalShapes) {
            shape.draw();
        }

    }

    public void moveUp() {
        for (Shape shape : movingShapes) {
            shape.moveUp();
        }
    }

    public void moveDown() {
        for (Shape shape : movingShapes) {
            shape.moveDown();
        }
    }

    public void moveRight() {
        for (Shape shape : movingShapes) {
            shape.moveRight();
        }
    }

    public void moveLeft() {
        for (Shape shape : movingShapes) {
            shape.moveLeft();
        }
    }

    public List<Shape> getMovingShapes() {
        return movingShapes;
    }

    public List<Shape> getStaticalShapes() {
        return staticalShapes;
    }

    public void switchCurrentMovingShapeToCircle() {
        turnMovingShapesIntoStatical();
        movingShapes.add(new Circle(this, displayDriver, START_X, START_Y, true));
    }

    public void switchCurrentMovingShapeToSquare() {
        turnMovingShapesIntoStatical();
        movingShapes.add(new Square(this, displayDriver, START_X, START_Y, true));
    }

    public void switchCurrentMovingShapeToArc1() {
        turnMovingShapesIntoStatical();
        movingShapes.add(new Arc1(this, displayDriver, START_X, START_Y, true));
    }

    private void turnMovingShapesIntoStatical() {
        if (movingShapes.size() != 0) {
            for (Shape shape : movingShapes) {
                shape.setSelection(false);
            }
            staticalShapes.addAll(movingShapes);
            movingShapes.clear();
        }
    }
}
