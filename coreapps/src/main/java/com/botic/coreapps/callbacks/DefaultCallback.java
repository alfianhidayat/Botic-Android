package com.botic.coreapps.callbacks;

import android.content.Context;
import android.util.Log;

import com.botic.coreapps.R;
import com.botic.coreapps.networks.RetrofitApi;
import com.botic.coreapps.responses.BaseResponse;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultCallback<T> implements Callback<BaseResponse<T>> {
    final Context mContext;

    DefaultCallback(Context context) {
        mContext = context;

        onStart();
    }

    @Override
    public void onFailure(Call<BaseResponse<T>> call, Throwable t) {

        if (t instanceof ConnectException || t instanceof SocketTimeoutException) {
            onError("Please check your internet connection.");
        } else {
            Log.e("Error", t.getLocalizedMessage());
            onError("An unknown error occurred.");
        }

        onFinish();
    }

    @Override
    public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body().data);
        } else if (response.code() == 401) {
            onUnauthorized();
        } else {
            try {
                onError(RetrofitApi.createGson().fromJson(response.errorBody().string(), BaseResponse.class).message);
            } catch (IOException e) {
                onFailure(call, e);
            }
        }

        onFinish();
    }

    protected void onError(String message) {
    }

    protected void onFinish() {
    }

    protected void onStart() {
    }

    protected void onSuccess(T data) {
    }

    protected void onUnauthorized() {
    }
}
