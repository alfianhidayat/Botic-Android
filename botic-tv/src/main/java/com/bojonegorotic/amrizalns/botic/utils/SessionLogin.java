package com.bojonegorotic.amrizalns.botic.utils;

import com.botic.coreapps.models.CheckInParams;
import com.botic.coreapps.models.Token;
import com.botic.coreapps.models.User;
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

    public static void reset() {
        Hawk.deleteAll();
    }

    public static void saveCheckIn(CheckInParams params) {
        Hawk.put(Constants.SHARED_PREF_CHECK_IN_PARAMS, params);
    }

    public static CheckInParams getCheckIn() {
        return Hawk.get(Constants.SHARED_PREF_CHECK_IN_PARAMS);
    }

    public static void deleteCheckInParams() {
        Hawk.delete(Constants.SHARED_PREF_CHECK_IN_PARAMS);
    }

    public static void saveProfile(User user) {
        Hawk.put(Constants.SHARED_PREF_PROFILE, user);
    }

    public static User getProfile() {
        return Hawk.get(Constants.SHARED_PREF_PROFILE);
    }

}
