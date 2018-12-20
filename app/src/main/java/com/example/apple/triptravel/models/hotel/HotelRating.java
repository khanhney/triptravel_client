package com.example.apple.triptravel.models.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelRating {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<DataHotelRating> data = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<DataHotelRating> getData() {
        return data;
    }

    public void setData(List<DataHotelRating> data) {
        this.data = data;
    }

}