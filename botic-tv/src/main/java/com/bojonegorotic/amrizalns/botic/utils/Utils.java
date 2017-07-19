package com.bojonegorotic.amrizalns.botic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bojonegorotic.amrizalns.botic.fragment.item_content;
import com.squareup.okhttp.OkHttpClient;

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


public class Utils {

    private static Context mContext = null;

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

    public static OkHttpClient builder(Context context) {
        OkHttpClient httpClient = new OkHttpClient();

        CertificateFactory cf = null;
        InputStream cert = null;
        Certificate ca = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            cert = context.getResources().openRawResource(com.botic.coreapps.R.raw.mycrt);
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
        httpClient.setSslSocketFactory(sslContext.getSocketFactory());

        // creating a RestAdapter using the custom client
        return httpClient;
    }
}
