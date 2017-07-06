package com.example.amrizalns.botic;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by alfianh on 7/5/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
