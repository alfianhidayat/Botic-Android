package com.bojonegorotic.amrizalns.botic.utils;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

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

/**
 * Created by alfianh on 7/13/17.
 */

public class CustomPicasso {

    private static Picasso sPicasso;

    public static Picasso getInstance(Context context) {
        if (sPicasso == null) {
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
//            InputStream keyStore = context.getResources().openRawResource(R.raw.mycrt);
            Picasso.Builder builder = new Picasso.Builder(context);
            OkHttpClient okHttpClient = new OkHttpClient();
//            SSLContext sslContext;
//            try {
//                sslContext = SSLContext.getInstance("TLS");
//                sslContext.init(null, tmf.getTrustManagers(), null);
            okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
            OkHttpDownloader okHttpDownloader = new OkHttpDownloader(okHttpClient);
            builder.downloader(okHttpDownloader);
            sPicasso = builder.build();
//            } catch (NoSuchAlgorithmException e) {
//                throw new IllegalStateException("Failure initializing default SSL context", e);
//            } catch (KeyManagementException e) {
//                throw new IllegalStateException("Failure initializing default SSL context", e);
//            } catch (GeneralSecurityException e) {
//                e.printStackTrace();
//            }
        }

        return sPicasso;
    }

}
