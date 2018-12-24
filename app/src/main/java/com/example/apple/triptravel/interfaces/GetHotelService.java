package com.example.apple.triptravel.interfaces;

import com.example.apple.triptravel.models.chat.ChatBot;
import com.example.apple.triptravel.models.hotel.DataRatingWrite;
import com.example.apple.triptravel.models.hotel.Hotel;
import com.example.apple.triptravel.models.hotel.HotelRating;
import com.example.apple.triptravel.models.rating.TrainRating;
import com.example.apple.triptravel.models.search.SearchHotel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetHotelService {

    @GET("/list-hotel")
    Call<Hotel> getListHotel();

    @GET("/list-rating")
    Call<HotelRating> getListHotelRating(@Query("hotelId") String hotelId);

    @POST("/add-rating")
    @FormUrlEncoded
    Call<DataRatingWrite> postRatingWrite(@Header("token") String token,
                                          @Field("title") String title,
                                          @Field("message") String message,
                                          @Field("star") int star,
                                          @Field("hotelID") String hotelID);

    @GET("/search-hotel")
    Call<SearchHotel> getSearchHotel(@Query("search")  String search);

    @POST("/user-rating-aswer")
    @FormUrlEncoded
    Call<TrainRating> postTrainRating(@Field("message") String message,
                                      @Field("answer") String answer,
                                      @Field("pointUserRating") int point,
                                      @Header("Token") String token);
}
