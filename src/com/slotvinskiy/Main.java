package com.slotvinskiy;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;
    private static final int FPS = 60;

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
        scene.setOnMousePressed(this::mouseClick);
        scene.setOnMouseDragged(this::mouseDrag);
        scene.setOnMouseReleased(this::mouseRelease);
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

    private void mouseClick(MouseEvent mouseEvent) {
        board.setX(mouseEvent.getX());
        board.setY(mouseEvent.getY());
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
                board.turnMovingShapesIntoStatical();
                break;
            case DIGIT1:
                board.switchCurrentMovingShapeToCircle();
                break;
            case DIGIT2:
                board.switchCurrentMovingShapeToSquare();
                break;
            case DIGIT3:
                board.switchCurrentMovingShapeToArc1();
                break;
            case DIGIT4:
                board.switchCurrentMovingShapeToArc2();
                break;
        }
        drawFrame();
    }


    public void drawFrame() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        board.drawFrame();
    }
}
