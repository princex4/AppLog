package com.example.applogs;

import java.util.ArrayList;

public class ProductModel {

    private String id;
    private String name;
    private DimensionsModel dimensionsModel;
    private ArrayList<String> tagArrayList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DimensionsModel getDimensionsModel() {
        return dimensionsModel;
    }

    public void setDimensionsModel(DimensionsModel dimensionsModel) {
        this.dimensionsModel = dimensionsModel;
    }

    public ArrayList<String> getTagArrayList() {
        return tagArrayList;
    }

    public void setTagArrayList(ArrayList<String> tagArrayList) {
        this.tagArrayList = tagArrayList;
    }
}
