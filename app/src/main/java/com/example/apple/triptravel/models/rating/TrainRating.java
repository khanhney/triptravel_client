package com.example.apple.triptravel.models.rating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrainRating {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private DataTrainRating data;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public DataTrainRating getData() {
        return data;
    }

    public void setData(DataTrainRating data) {
        this.data = data;
    }

}