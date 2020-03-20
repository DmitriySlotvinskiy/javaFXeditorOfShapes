package com.slotvinskiy.save;

import com.google.gson.Gson;
import com.slotvinskiy.editor.Board;
import com.slotvinskiy.editor.DisplayDriver;
import com.slotvinskiy.editor.shapes.ArcUp;
import com.slotvinskiy.editor.shapes.CircleShape;
import com.slotvinskiy.editor.shapes.Shape;
import com.slotvinskiy.editor.shapes.SquareShape;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Saver {

    public static void save(List<Shape> shapes) {

        if (shapes.isEmpty()) {
            return;
        }
        Gson gson = new Gson();
        FileShapesList fileShapeList = new FileShapesList();
        FileShape fs;
        for (Shape shape : shapes) {
            fs = new FileShape(shape);
            fileShapeList.add(fs);
        }
        String saveData = gson.toJson(fileShapeList);
        System.out.println(saveData);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"))) {
            writer.write(saveData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Shape> load(DisplayDriver displayDriver, Board board) {

        String jsonString = new String();
        try (BufferedReader br = new BufferedReader(new FileReader("save.txt"))) {
            jsonString = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        FileShapesList fsList = gson.fromJson(jsonString, FileShapesList.class);
        List<Shape> loadList = new ArrayList<>();
        for (FileShape fs : fsList.getShapeList()) {
            if (fs.getShapeType().equals("Circle")) {
                loadList.add(new CircleShape(board, displayDriver, fs.getX(), fs.getY(), fs.getSize(), false));
            }
            if (fs.getShapeType().equals("Square")) {
                loadList.add(new SquareShape(board, displayDriver, fs.getX(), fs.getY(), fs.getSize(), false));
            }
            if (fs.getShapeType().equals("ArcUp")) {
                loadList.add(new ArcUp(board, displayDriver, fs.getX(), fs.getY(), fs.getSize(), false));
            }
            if (fs.getShapeType().equals("ArcDown")) {
                loadList.add(new ArcUp(board, displayDriver, fs.getX(), fs.getY(), fs.getSize(), false));
            }
        }

        return loadList;
    }
}