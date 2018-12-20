package com.example.apple.triptravel.interfaces;

import com.example.apple.triptravel.models.trip.Trip;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface GetTripService {
    @GET("/list-trip")
    Call<Trip> getListTrip();

}
