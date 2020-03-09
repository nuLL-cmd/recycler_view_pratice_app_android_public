package com.example.recyclerviewpratice;

public class DataProvider {

    private  String anything;
    private String anything1;
    private int img_resource;
    public void toString(String clicked){
        this.anything = clicked;
    }
    public DataProvider(String anything, String anything1, int img_resource) {
        this.anything = anything;
        this.anything1 = anything1;
        this.img_resource = img_resource;
    }

    public String getAnything() {
        return anything;
    }

    public String getAnything1() {
        return anything1;
    }

    public int getImg_resource() {
        return img_resource;
    }
}
