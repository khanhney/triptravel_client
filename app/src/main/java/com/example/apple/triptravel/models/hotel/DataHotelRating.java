package com.example.apple.triptravel.models.hotel;

import com.example.apple.triptravel.models.hotel.UserHotelRating;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataHotelRating {

    @SerializedName("star")
    @Expose
    private Integer star;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("user")
    @Expose
    private UserHotelRating user;
    @SerializedName("hotel")
    @Expose
    private Hotel hotel;
    @SerializedName("updateAt")
    @Expose
    private String updateAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserHotelRating getUser() {
        return user;
    }

    public void setUser(UserHotelRating user) {
        this.user = user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }


}
