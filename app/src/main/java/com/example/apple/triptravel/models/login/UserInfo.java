package com.example.apple.triptravel.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private DataUserInfo data;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public DataUserInfo getData() {
        return data;
    }

    public void setData(DataUserInfo data) {
        this.data = data;
    }

}