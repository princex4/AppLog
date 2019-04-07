package com.example.applogs;

public class DimensionsModel {

    private float length;
    private float width;
    private float height;

    public DimensionsModel(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
