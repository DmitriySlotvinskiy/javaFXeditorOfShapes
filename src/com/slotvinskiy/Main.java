package com.slotvinskiy;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;
    private boolean closed;
    private GraphicsContext gc;

    private Board board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Graphic Editor");
        Canvas canvas = new Canvas();
        canvas.setHeight(BOARD_HEIGHT);
        canvas.setWidth(BOARD_WIDTH);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        DisplayDriver displayDriver = new DisplayDriverImpl(gc);
        board = new Board(displayDriver);

        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnMousePressed(this::mouseLeftClick);
        scene.setOnMouseDragged(this::mouseDrag);
        scene.setOnMouseReleased(this::mouseRelease);
        scene.setOnScroll(this::changeSize);
        drawFrame();
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                board.moveUp();
                break;
            case DOWN:
                board.moveDown();
                break;
            case LEFT:
                board.moveLeft();
                break;
            case RIGHT:
                board.moveRight();
                break;
            case ENTER:
            case ESCAPE:
                board.turnAllSelectedShapesIntoDeselected();
                break;
            case DIGIT1:
                board.switchCurrentSelectedShapeToCircle();
                break;
            case DIGIT2:
                board.switchCurrentSelectedShapeToSquare();
                break;
            case DIGIT3:
                board.switchCurrentSelectedShapeToArcUp();
                break;
            case DIGIT4:
                board.switchCurrentSelectedShapeToArcDown();
                break;
            case DELETE:
                board.deleteSelected();
                break;
            case C:
                if (event.isControlDown()) {
                    board.cloneSelected();
                } else {
                    board.changeCurrentColor();
                }
                break;
            case F5:
                board.save();
                break;
            case F6:
                board.load();
                break;
        }
        drawFrame();
    }

    private void changeSize(ScrollEvent scrollEvent) {

        if (scrollEvent.getDeltaY() > 0) {
            board.increaseSize();
        } else {
            board.decreaseSize();
        }
        drawFrame();
    }

    private void mouseDrag(MouseEvent mouseEvent) {
        double offSetX = board.getX() - mouseEvent.getX();
        double offSetY = board.getY() - mouseEvent.getY();
        board.moveWithMouse(offSetX, offSetY);
        drawFrame();
        board.setX(mouseEvent.getX());
        board.setY(mouseEvent.getY());
    }

    private void mouseLeftClick(MouseEvent mouseEvent) {
            board.setX(mouseEvent.getX());
            board.setY(mouseEvent.getY());
            if (mouseEvent.isControlDown()) {
                board.ifHitDeselectedTurnIntoSelectedElseTurnIntoNotSelected();
            } else {
                board.ifHitTurnSelected();
            }
        drawFrame();
    }

    private void mouseRelease(MouseEvent mouseEvent) {
        board.setX(0);
        board.setY(0);
    }

    @Override
    public void stop() {
        closed = true;
    }

    public void drawFrame() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        showManual();
        board.drawFrame();
    }

    public void showManual() {
        gc.setFill(Color.BLUE);
        gc.fillText("1-4 - add shape (and apply previous one);    MOUSE and KEYBOARD ARROWS - moving shape;" +
                "    ESC - deselect;    ENTER - apply shape(shapes);", 10, 15);
        gc.fillText("LEFT CLICK - select aim and deselect another shapes;   LEFT CLICK + CTRL - add to selected;" +
                "    CTRL + C - copy selected;   DEL - delete selected", 10, 35);
        gc.fillText("SCROLLING - change size;    C - change color;    F5 - save scene;   F6 - load scene;", 10, 55);
    }
}
