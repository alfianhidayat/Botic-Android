package com.example.amrizalns.botic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.amrizalns.botic.fragment.item_content;

public class Utils {

    private Context mContext = null;

    public Utils(Context con) {
        mContext = con;
    }

    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    //This is a method to Check if the device internet connection is currently on
    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    public static Fragment getFragmentWithArgument(int idCategory, String tag) {
        Bundle bundle = new Bundle();
        item_content fragment = new item_content();
        bundle.putInt(Constants.TAG_ID_CATEGORY, idCategory);
        bundle.putString(Constants.TAG_OBJECT_TYPE, tag);
        fragment.setArguments(bundle);
        return fragment;
    }
}
