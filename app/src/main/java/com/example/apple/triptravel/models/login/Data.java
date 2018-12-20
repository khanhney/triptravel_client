package com.example.apple.triptravel.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("data_user")
    @Expose
    private DataUserLogin dataUser;
    @SerializedName("token")
    @Expose
    private String token;

    public DataUserLogin getDataUser() {
        return dataUser;
    }

    public void setDataUser(DataUserLogin dataUser) {
        this.dataUser = dataUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
