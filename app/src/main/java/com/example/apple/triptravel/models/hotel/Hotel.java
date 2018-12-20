package com.example.apple.triptravel.models.hotel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Hotel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<DataHotel> data = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<DataHotel> getData() {
        return data;
    }

    public void setData(List<DataHotel> data) {
        this.data = data;
    }

}