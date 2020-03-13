package com.slotvinskiy;

import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 500;
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
        drawFrame();
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
            case DIGIT1:
                board.switchCurrentMovingShapeToCircle();
                break;
            case DIGIT2:
                board.switchCurrentMovingShapeToSquare();
                break;
        }
        drawFrame();
    }


    public void drawFrame() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        board.drawFrame();
        gc.fillOval(100, 100, 10, 10);
    }
}
