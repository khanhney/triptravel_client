package com.example.apple.triptravel.common;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {
    public static final String BASE_URL = "https://6ed8b644.ngrok.io";
    public static String TOKEN = "";

    public static String userCurrent = "1";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertDateToString(String currentDate){
        String result;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(currentDate, inputFormatter);
        result = outputFormatter.format(date);

        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertZoneTimeToString (String userCurrent){
        String dateInString;

        Instant instant = Instant.parse(userCurrent);

        //get date time only
        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

        dateInString = String.valueOf(result.toLocalDate().toString());
        return dateInString;
    }
}
