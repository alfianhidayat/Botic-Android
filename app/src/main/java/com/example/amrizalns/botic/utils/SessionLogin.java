package com.example.amrizalns.botic.utils;

import com.botic.coreapps.models.Token;
import com.orhanobut.hawk.Hawk;

/**
 * Created by alfianh on 7/5/17.
 */

public class SessionLogin {
    public static void saveAccessToken(Token token) {
        Hawk.put(Constants.SHARED_PREF_TOKEN, token.getAccessToken());
    }

    public static String getAccessToken() {
        return Hawk.get(Constants.SHARED_PREF_TOKEN);
    }

    public static boolean isExist() {
        if (Hawk.contains(Constants.SHARED_PREF_TOKEN)) {
            return true;
        } else {
            return false;
        }
    }

    public static void reset(){
        Hawk.deleteAll();
    }
}
