package com.slotvinskiy.editor;

import com.slotvinskiy.editor.shapes.Arc;
import com.slotvinskiy.editor.shapes.CircleShape;
import com.slotvinskiy.editor.shapes.Shape;
import com.slotvinskiy.editor.shapes.SquareShape;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int START_X = 400;
    private final int START_Y = 300;
    private int x;
    private int y;


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = (int) x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = (int) y;
    }

    private final DisplayDriver displayDriver;
    private final List<Shape> movingShapes = new ArrayList<>();
    private final List<Shape> staticalShapes = new ArrayList<>();

    public Board(DisplayDriver displayDriver) {
        this.displayDriver = displayDriver;
        movingShapes.add(new CircleShape(this, displayDriver, START_X, START_Y, true));
    }

    public void drawFrame() {
        for (Shape shape : staticalShapes) {
            shape.draw();
        }
        for (Shape shape : movingShapes) {
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

    public void moveWithMouse(double offSetX, double offSetY) {
        for (Shape shape : movingShapes) {
            shape.moveWithMouse(offSetX, offSetY);
        }
    }

    public void decreaseSize() {
        for (Shape shape : movingShapes) {
            shape.decreaseSize();
        }
    }

    public void increaseSize() {
        for (Shape shape : movingShapes) {
            shape.increaseSize();
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
        movingShapes.add(new CircleShape(this, displayDriver, START_X, START_Y, true));
    }

    public void switchCurrentMovingShapeToSquare() {
        turnMovingShapesIntoStatical();
        movingShapes.add(new SquareShape(this, displayDriver, START_X, START_Y, true));
    }

    public void switchCurrentMovingShapeToArc1() {
        turnMovingShapesIntoStatical();
        movingShapes.add(new Arc(this, displayDriver, START_X, START_Y, 360, true));
    }

    public void switchCurrentMovingShapeToArc2() {
        turnMovingShapesIntoStatical();
        movingShapes.add(new Arc(this, displayDriver, START_X, START_Y, 180, true));
    }

    public void turnMovingShapesIntoStatical() {
        if (movingShapes.size() != 0) {
            for (Shape shape : movingShapes) {
                shape.setSelection(false);
            }
            staticalShapes.addAll(movingShapes);
            movingShapes.clear();
        }
    }

    public void isHit() {
        for (Shape shape : staticalShapes) {
            if (shape.isHit(x, y)) {
                turnMovingShapesIntoStatical();
                addToMoving(shape);
                break;
            }
        }
    }

    private void addToMoving(Shape shape) {
        staticalShapes.remove(shape);
        movingShapes.add(shape);
        shape.setAsSelected();
    }
}
