package com.king.mobile.util;

import com.king.mobile.base.Api;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

class OkHttpTest {

    private OkHttpClient client;
    CertificatePinner certificatePinner = new CertificatePinner.Builder()
            .add("hostname", "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
            .build();
    OkHttpClient getClient() {
        if (client == null) {
            try {
                client = new OkHttpClient.Builder()
                        .certificatePinner(certificatePinner)
                        .sslSocketFactory(getSSLSocketFactory(), new MyX509TrustManager())
                        .build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

        }
        return client;

    }

    private SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext context = SSLContext.getInstance("TLS");
        TrustManager[] trustManagers = {new MyX509TrustManager()};
        context.init(null, trustManagers, new SecureRandom());
        return context.getSocketFactory();
    }
}
