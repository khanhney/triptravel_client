package com.example.apple.triptravel.common;

import com.example.apple.triptravel.interfaces.GetChatService;
import com.example.apple.triptravel.interfaces.GetHotelService;
import com.example.apple.triptravel.interfaces.GetTripService;
import com.example.apple.triptravel.interfaces.GetUserService;
import com.example.apple.triptravel.remotes.RetrofitClient;

import static com.example.apple.triptravel.common.Utils.BASE_URL;

public class UserCommon {

    public static GetUserService signUserUser()
    {
        return RetrofitClient.getClient(BASE_URL).create(GetUserService.class);
    }

    public static GetTripService getTripService(){
        return RetrofitClient.getClient(BASE_URL).create(GetTripService.class);
    }

    public static GetHotelService getHotelService(){
        return RetrofitClient.getClient(BASE_URL).create(GetHotelService.class);
    }

    public static GetChatService getChatService(){
        return RetrofitClient.getClient(BASE_URL).create(GetChatService.class);
    }
}
