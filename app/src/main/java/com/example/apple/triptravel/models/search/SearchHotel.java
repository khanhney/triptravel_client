package com.example.apple.triptravel.models.search;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchHotel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<DataSearch> data = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<DataSearch> getData() {
        return data;
    }

    public void setData(List<DataSearch> data) {
        this.data = data;
    }

}