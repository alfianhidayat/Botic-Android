package com.botic.coreapps.networks;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.botic.coreapps.AppsCore;
import com.botic.coreapps.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private ApiService mApiService;
    private static RetrofitApi instance = null;
    private Retrofit retrofit;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();
    private String token;
    private static Context context;

    public static RetrofitApi getInstance() {
        if (instance == null)
            instance = new RetrofitApi();

        return instance;
    }


    public static RetrofitApi getInstance(Context mContenx) {
        context = mContenx;
        if (instance == null)
            instance = new RetrofitApi();

        return instance;
    }

    private RetrofitApi() {
        retrofit = new Retrofit.Builder().baseUrl(AppsCore.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttp().build())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService(String token) {
        this.token = token;
        return mApiService;
    }

    public static Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    private OkHttpClient.Builder getOkHttp() {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        OkHttpClient.Builder httpClient = builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("Network", message);
            }
        });
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(request);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);

        return httpClient;
    }

    private OkHttpClient.Builder builder() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        CertificateFactory cf = null;
        InputStream cert = null;
        Certificate ca = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            cert = context.getResources().openRawResource(R.raw.mycrt);
            ca = cf.generateCertificate(cert);
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        // creating a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = null;
        try

        {
            keyStore = KeyStore.getInstance(keyStoreType);
        } catch (
                KeyStoreException e)

        {
            e.printStackTrace();
        }
        try

        {
            keyStore.load(null, null);
        } catch (
                IOException e)

        {
            e.printStackTrace();
        } catch (
                NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        } catch (
                CertificateException e)

        {
            e.printStackTrace();
        }
        try

        {
            keyStore.setCertificateEntry("ca", ca);
        } catch (
                KeyStoreException e)

        {
            e.printStackTrace();
        }

        // creating a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = null;
        try

        {
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        } catch (
                NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
        try

        {
            tmf.init(keyStore);
        } catch (
                KeyStoreException e)

        {
            e.printStackTrace();
        }

        // creating an SSLSocketFactory that uses our TrustManager
        SSLContext sslContext = null;
        try

        {
            sslContext = SSLContext.getInstance("TLS");
        } catch (
                NoSuchAlgorithmException e)

        {
            e.printStackTrace();
        }
        try

        {
            sslContext.init(null, tmf.getTrustManagers(), null);
        } catch (
                KeyManagementException e)

        {
            e.printStackTrace();
        }
        httpClient.sslSocketFactory(sslContext.getSocketFactory());

        // creating a RestAdapter using the custom client
        return httpClient;
    }
}