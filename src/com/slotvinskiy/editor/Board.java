package com.slotvinskiy.editor;

import com.slotvinskiy.editor.shapes.*;
import com.slotvinskiy.save.Saver;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int COLORS_CODES_NUMBER = 7;
    private final int DEFAULT_X = 400;
    private final int DEFAULT_Y = 300;
    private int x;
    private int y;
    private int currentColorCode = 0;


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
    private List<Shape> selectedShapes = new ArrayList<>();
    private List<Shape> notSelectedShapes = new ArrayList<>();

    public Board(DisplayDriver displayDriver) {
        this.displayDriver = displayDriver;
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

    public void switchCurrentSelectedShapeToCircle() {
        turnAllSelectedShapesIntoDeselected();
        selectedShapes.add(new CircleShape(this, displayDriver, DEFAULT_X, DEFAULT_Y, true));
    }

    public void switchCurrentSelectedShapeToSquare() {
        turnAllSelectedShapesIntoDeselected();
        selectedShapes.add(new SquareShape(this, displayDriver, DEFAULT_X, DEFAULT_Y, true));
    }

    public void switchCurrentSelectedShapeToArcUp() {
        turnAllSelectedShapesIntoDeselected();
        selectedShapes.add(new ArcUp(this, displayDriver, DEFAULT_X, DEFAULT_Y, true));
    }

    public void switchCurrentSelectedShapeToArcDown() {
        turnAllSelectedShapesIntoDeselected();
        selectedShapes.add(new ArcDown(this, displayDriver, DEFAULT_X, DEFAULT_Y, true));
    }

    public void turnAllSelectedShapesIntoDeselected() {
        if (selectedShapes.size() != 0) {
            for (Shape shape : selectedShapes) {
                shape.setSelection(false);
            }
            notSelectedShapes.addAll(selectedShapes);
            selectedShapes.clear();
        }
    }

    public void ifHitTurnSelected() {
        for (Shape shape : selectedShapes) {
            if (shape.isHit(x, y)) {
                return;
            }
        }
        for (Shape shape : notSelectedShapes) {
            if (shape.isHit(x, y)) {
                turnAllSelectedShapesIntoDeselected();
                turnShapeIntoSelected(shape);
                break;
            }
        }
    }

    private void turnShapeIntoSelected(Shape shape) {
        notSelectedShapes.remove(shape);
        selectedShapes.add(shape);
        shape.setAsSelected();
    }

    public void ifHitDeselectedTurnIntoSelectedElseTurnIntoNotSelected() {
        for (Shape shape : notSelectedShapes) {
            if (shape.isHit(x, y)) {
                turnShapeIntoSelected(shape);
                return;
            }
        }
        for (Shape shape : selectedShapes) {
            if (shape.isHit(x, y)) {
                addToNotSelectedShapes(shape);
                break;
            }
        }
    }

    private void addAllToSelectedShapes(List<Shape> shapes) {
        for (Shape shape : shapes) {
            addToSelectedShapes(shape);

        }
    }

    private void addToSelectedShapes(Shape shape) {
        selectedShapes.add(shape);
        shape.setAsSelected();
    }


    private void addToNotSelectedShapes(Shape shape) {
        selectedShapes.remove(shape);
        notSelectedShapes.add(shape);
        shape.setAsDeselected();
    }

    public void deleteSelected() {
        selectedShapes.clear();
    }

    public void cloneSelected() {
        List<Shape> temp = new ArrayList<>();
        for (Shape shape : selectedShapes) {
            if (shape instanceof CircleShape) {
                temp.add(new CircleShape((CircleShape) shape));
            }
            if (shape instanceof SquareShape) {
                temp.add(new SquareShape((SquareShape) shape));
            }
            if (shape instanceof ArcUp) {
                temp.add(new ArcUp((ArcUp) shape));
            }
            if (shape instanceof ArcDown) {
                temp.add(new ArcDown((ArcDown) shape));
            }
        }
        turnAllSelectedShapesIntoDeselected();
        addAllToSelectedShapes(temp);

    }

    public void save() {
        turnAllSelectedShapesIntoDeselected();
        Saver.save(notSelectedShapes);
    }

    public void load() {
        selectedShapes.clear();
        notSelectedShapes.clear();
        notSelectedShapes = Saver.load(displayDriver, this);
    }

    public void changeCurrentColor() {
        if (currentColorCode < COLORS_CODES_NUMBER) {
            currentColorCode++;
        } else {
            currentColorCode = 0;
        }
        for (Shape shape : selectedShapes) {
            shape.changeColor(currentColorCode);
        }
        drawFrame();
    }
}
