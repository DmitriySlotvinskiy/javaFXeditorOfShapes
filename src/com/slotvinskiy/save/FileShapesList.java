package com.slotvinskiy.save;

import java.util.ArrayList;
import java.util.List;

public class FileShapesList {

    private List<FileShape> shapeList = new ArrayList<>();

    public void add(FileShape fs) {
        shapeList.add(fs);
    }

    public List<FileShape> getShapeList() {
        return shapeList;
    }

    @Override
    public String toString() {
        return "FileShapesList{" +
                "shapeList=" + shapeList +
                '}';
    }
}
