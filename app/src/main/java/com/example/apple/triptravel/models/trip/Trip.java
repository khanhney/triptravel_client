package com.example.apple.triptravel.models.trip;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trip {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<DataTrip> data = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<DataTrip> getData() {
        return data;
    }

    public void setData(List<DataTrip> data) {
        this.data = data;
    }

}