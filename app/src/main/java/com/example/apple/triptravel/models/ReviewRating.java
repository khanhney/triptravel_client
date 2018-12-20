package com.example.apple.triptravel.models;

import java.io.Serializable;

public class ReviewRating implements Serializable {
    private String title;
    private String author;
    private double rating;
    private String createAt;
    private String description;

    public ReviewRating(Builder builder) {
        this.title          = builder.title;
        this.author         = builder.author;
        this.rating         = builder.rating;
        this.createAt       = builder.createAt;
        this.description    = builder.description;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getRating() {
        return rating;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder{
        private String title;
        private String author;
        private double rating;
        private String createAt;
        private String description;

        public Builder() {
        }

        public Builder(String title, String author, double rating, String createAt, String description) {
            this.title = title;
            this.author = author;
            this.rating = rating;
            this.createAt = createAt;
            this.description = description;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setRating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setCreateAt(String createAt) {
            this.createAt = createAt;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ReviewRating build(){
            return  new ReviewRating(this);
        }
    }
}
