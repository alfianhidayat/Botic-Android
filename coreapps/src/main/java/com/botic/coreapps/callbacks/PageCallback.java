package com.botic.coreapps.callbacks;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

public class PageCallback<T> extends DefaultCallback<T> {
    protected PageCallback(Context context) {
        super(context);
    }

    @Override
    protected void onError(String message) {
        Log.e("onError: ", message);
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onUnauthorized() {
//        Hawk.deleteAll();

//        Intent intent = new Intent(mContext, signIn.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

//        mContext.startActivity(intent);
    }
}
