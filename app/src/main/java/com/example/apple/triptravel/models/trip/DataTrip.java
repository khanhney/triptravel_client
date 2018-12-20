package com.example.apple.triptravel.models.trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataTrip implements Serializable {

    @SerializedName("isFeature")
    @Expose
    private Integer isFeature;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("qrcode")
    @Expose
    private String qrcode;
    @SerializedName("image")
    @Expose
    private String image;

    public DataTrip(Integer isFeature, Integer status, String id, String title, String description, String price, String startTime, Integer v, String qrcode, String image) {
        this.isFeature = isFeature;
        this.status = status;
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.startTime = startTime;
        this.v = v;
        this.qrcode = qrcode;
        this.image = image;
    }

    public DataTrip(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Integer getIsFeature() {
        return isFeature;
    }

    public void setIsFeature(Integer isFeature) {
        this.isFeature = isFeature;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
