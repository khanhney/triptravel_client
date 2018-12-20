package com.example.apple.triptravel.models;

import java.io.Serializable;

public class Deal implements Serializable {
    private String imgUrl;
    private String title;
    private String price;
    private String description;
    private String dealLeft;

    public Deal(Builder builder) {
        this.imgUrl         = builder.imgUrl;
        this.title          = builder.title;
        this.price          = builder.price;
        this.description    = builder.description;
        this.dealLeft       = builder.dealLeft;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getDealLeft() {
        return dealLeft;
    }

    public static class Builder {
        private String imgUrl;
        private String title;
        private String price;
        private String description;
        private String dealLeft;

        public Builder() {
        }

        public Builder(String imgUrl, String title, String price, String description, String dealLeft) {
            this.imgUrl         = imgUrl;
            this.title          = title;
            this.price          = price;
            this.description    = description;
            this.dealLeft       = dealLeft;
        }

        public Builder setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDealLeft(String dealLeft) {
            this.dealLeft = dealLeft;
            return this;
        }

        public Deal build(){
            return new Deal(this);
        }
    }


}
