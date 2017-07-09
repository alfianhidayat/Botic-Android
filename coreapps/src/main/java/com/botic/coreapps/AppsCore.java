package com.botic.coreapps;

import android.app.Application;


public class AppsCore extends Application{
    public static final String BASE_URL = "http://192.168.0.100:8000/";
    public static final String GOOGLE_API_KEY = "AIzaSyCqk5uuqfG8Ska3ENzJHe5PNGTtNTXhvpk";
    public static final String ERROR_NETWORK = "Error. Please check your internet connection";
    public static final String ERROR = "An error has occurred, ";
    public static final String TOKEN_EXPIRED = "Sesi login anda telah habis, silahkan melakukan login ulang !";
    public static final String TAG_TOKEN_EXPIRED = "token_expired";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
