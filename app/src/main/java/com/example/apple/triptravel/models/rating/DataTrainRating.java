package com.example.apple.triptravel.models.rating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTrainRating {

    @SerializedName("pointUserRating")
    @Expose
    private Integer pointUserRating;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getPointUserRating() {
        return pointUserRating;
    }

    public void setPointUserRating(Integer pointUserRating) {
        this.pointUserRating = pointUserRating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
