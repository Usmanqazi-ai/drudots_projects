package com.example.express.Classes;

public class MoreModel {

    int imageView;
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public MoreModel(int imageView, String text) {
        this.imageView = imageView;
        this.text = text;
    }
}
