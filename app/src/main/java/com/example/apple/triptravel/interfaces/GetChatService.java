package com.example.apple.triptravel.interfaces;

import com.example.apple.triptravel.models.chat.ChatBot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface GetChatService {

    @GET("/chat-bot")
    Call<ChatBot> getListChatBot(@Query("message")  String message);
}
