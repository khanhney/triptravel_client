package com.example.apple.triptravel.models;

import java.io.Serializable;

public class Flights implements Serializable {
    private String title;
    private String imgURL;

    public Flights(String title, String imgURL) {
        this.title = title;
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
