package com.example.express.Classes;

public class HelpModel {
    private int img;
   private String title;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HelpModel(int img, String title) {
        this.img = img;
        this.title = title;
    }
}
