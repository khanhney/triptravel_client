package com.example.apple.triptravel.models.hotel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelRatingWrite {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private DataRatingWrite data;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public DataRatingWrite getData() {
        return data;
    }

    public void setData(DataRatingWrite data) {
        this.data = data;
    }

}