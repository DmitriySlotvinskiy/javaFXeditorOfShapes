package com.slotvinskiy.editor;

public enum MyColor {
    RED("#FF0000"), ORANGE("#FF9900"), YELLOW("#FFEE00"), GREEN("#33FF00"), BLUE("#00FFDD"), NAVY_BLUE("#0077FF"), PURPLE("#7700FF"), BLACK("#000000");

    private String color;

    MyColor(String color) {
        this.color = color;
    }

    public String toHex() {
        return color;
    }
}
