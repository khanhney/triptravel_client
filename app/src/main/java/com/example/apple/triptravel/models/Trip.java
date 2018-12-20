package com.example.apple.triptravel.models;

import java.io.Serializable;

public class Trip implements Serializable {
    private String img;
    private String title;

    public Trip(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
