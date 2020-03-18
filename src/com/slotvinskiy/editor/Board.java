package com.slotvinskiy.editor;

import com.slotvinskiy.editor.shapes.*;

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
    private final List<Shape> selectedShapes = new ArrayList<>();
    private final List<Shape> notSelectedShapes = new ArrayList<>();

    public Board(DisplayDriver displayDriver) {
        this.displayDriver = displayDriver;
        selectedShapes.add(new CircleShape(this, displayDriver, START_X, START_Y, true));
    }

    public void drawFrame() {
        for (Shape shape : notSelectedShapes) {
            shape.draw();
        }
        for (Shape shape : selectedShapes) {
            shape.draw();
        }

    }

    public void moveUp() {
        for (Shape shape : selectedShapes) {
            shape.moveUp();
        }
    }

    public void moveDown() {
        for (Shape shape : selectedShapes) {
            shape.moveDown();
        }
    }

    public void moveRight() {
        for (Shape shape : selectedShapes) {
            shape.moveRight();
        }
    }

    public void moveLeft() {
        for (Shape shape : selectedShapes) {
            shape.moveLeft();
        }
    }

    public void moveWithMouse(double offSetX, double offSetY) {
        for (Shape shape : selectedShapes) {
            shape.moveWithMouse(offSetX, offSetY);
        }
    }

    public void decreaseSize() {
        for (Shape shape : selectedShapes) {
            shape.decreaseSize();
        }
    }

    public void increaseSize() {
        for (Shape shape : selectedShapes) {
            shape.increaseSize();
        }
    }

    public List<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public List<Shape> getNotSelectedShapes() {
        return notSelectedShapes;
    }

    public void switchCurrentMovingShapeToCircle() {
        turnAllMovingShapesIntoStatical();
        selectedShapes.add(new CircleShape(this, displayDriver, START_X, START_Y, true));
    }

    public void switchCurrentMovingShapeToSquare() {
        turnAllMovingShapesIntoStatical();
        selectedShapes.add(new SquareShape(this, displayDriver, START_X, START_Y, true));
    }

    public void switchCurrentMovingShapeToArcUp() {
        turnAllMovingShapesIntoStatical();
        selectedShapes.add(new ArcUp(this, displayDriver, START_X, START_Y, true));
    }

    public void switchCurrentMovingShapeToArcDown() {
        turnAllMovingShapesIntoStatical();
        selectedShapes.add(new ArcDown(this, displayDriver, START_X, START_Y, true));
    }


    public void turnAllMovingShapesIntoStatical() {
        if (selectedShapes.size() != 0) {
            for (Shape shape : selectedShapes) {
                shape.setSelection(false);
            }
            notSelectedShapes.addAll(selectedShapes);
            selectedShapes.clear();
        }
    }

    public void ifHitTurnSelected() {
        for (Shape shape : notSelectedShapes) {
            if (shape.isHit(x, y)) {
                turnAllMovingShapesIntoStatical();
                addToSelectedShapes(shape);
                break;
            }
        }
    }

    private void addToSelectedShapes(Shape shape) {
        notSelectedShapes.remove(shape);
        selectedShapes.add(shape);
        shape.setAsSelected();
    }

    public void ifHitDeselectedTurnSelectedElseTurnDeselected() {
        for (Shape shape : notSelectedShapes) {
            if (shape.isHit(x, y)) {
                addToSelectedShapes(shape);
                return;
            }
        }
        for (Shape shape : selectedShapes) {
            if (shape.isHit(x, y)) {
                addToDeselectedShapes(shape);
                break;
            }
        }
    }

    private void addToDeselectedShapes(Shape shape) {
        selectedShapes.remove(shape);
        notSelectedShapes.add(shape);
        shape.setAsDeselected();
    }

    public void deleteSelected() {
        selectedShapes.clear();
    }
}
