package com.example.apple.triptravel.interfaces;


import com.example.apple.triptravel.models.login.DataUserLogin;
import com.example.apple.triptravel.models.login.Login;
import com.example.apple.triptravel.models.sign_up.DataUser;
import com.example.apple.triptravel.models.sign_up.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetUserService {

    @POST("/register")
    Call<User> signUpUser(@Body DataUser dataUser);

    @POST("/sign-in")
    @FormUrlEncoded
    Call<Login> signInUser(@Field("username_or_email") String username_or_email,
                           @Field("password") String password);
}
