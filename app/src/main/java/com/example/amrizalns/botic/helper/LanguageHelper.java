package com.example.amrizalns.botic.helper;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by user on 16/07/2017.
 */

public class LanguageHelper {
    public static void changeLocale(Resources resource, String locale){
        Configuration config;
        config = new Configuration(resource.getConfiguration());

        switch (locale){
            case "in":
                config.locale = new Locale("id", "ID");
                break;
            case "en":
                config.locale = Locale.ENGLISH;
                break;
            default:
                break;
        }
        resource.updateConfiguration(config, resource.getDisplayMetrics());
    }
}
