package com.example.apple.triptravel.models.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatBot {

    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("awnser")
    @Expose
    private String awnser;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAwnser() {
        return awnser;
    }

    public void setAwnser(String awnser) {
        this.awnser = awnser;
    }

}