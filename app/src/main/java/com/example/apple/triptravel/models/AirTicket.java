package com.example.apple.triptravel.models;

import java.io.Serializable;

public class AirTicket implements Serializable {
    private String placeFrom;
    private String placeTo;
    private String dateTimeFrom;
    private String dateTimeTo;
    private String brand;
    private String dateTimeLastAgo;
    private String price;


    public AirTicket(Builder builder) {
        this.placeFrom          = builder.placeFrom;
        this.placeTo            = builder.placeTo;
        this.dateTimeFrom       = builder.dateTimeFrom;
        this.dateTimeTo         = builder.dateTimeTo;
        this.brand              = builder.brand;
        this.dateTimeLastAgo    = builder.dateTimeLastAgo;
        this.price              = builder.price;
    }

    public String getPlaceFrom() {
        return placeFrom;
    }

    public String getPlaceTo() {
        return placeTo;
    }

    public String getDateTimeFrom() {
        return dateTimeFrom;
    }

    public String getDateTimeTo() {
        return dateTimeTo;
    }

    public String getBrand() {
        return brand;
    }

    public String getDateTimeLastAgo() {
        return dateTimeLastAgo;
    }

    public String getPrice() {
        return price;
    }

    public static class Builder {
        private String placeFrom;
        private String placeTo;
        private String dateTimeFrom;
        private String dateTimeTo;
        private String brand;
        private String dateTimeLastAgo;
        private String price;

        public Builder() {
        }

        public Builder(String placeFrom, String placeTo, String dateTimeFrom, String dateTimeTo, String brand, String dateTimeLastAgo, String price) {
            this.placeFrom          = placeFrom;
            this.placeTo            = placeTo;
            this.dateTimeFrom       = dateTimeFrom;
            this.dateTimeTo         = dateTimeTo;
            this.brand              = brand;
            this.dateTimeLastAgo    = dateTimeLastAgo;
            this.price              = price;
        }

        public Builder setPlaceFrom(String placeFrom) {
            this.placeFrom = placeFrom;
            return this;
        }

        public Builder setPlaceTo(String placeTo) {
            this.placeTo = placeTo;
            return this;
        }

        public Builder setDateTimeFrom(String dateTimeFrom) {
            this.dateTimeFrom = dateTimeFrom;
            return this;
        }

        public Builder setDateTimeTo(String dateTimeTo) {
            this.dateTimeTo = dateTimeTo;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setDateTimeLastAgo(String dateTimeLastAgo) {
            this.dateTimeLastAgo = dateTimeLastAgo;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public AirTicket build(){
            return new AirTicket(this);
        }
    }
}
