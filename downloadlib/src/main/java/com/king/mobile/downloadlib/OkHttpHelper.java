package com.king.mobile.downloadlib;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpHelper {
    static OkHttpClient client;

    public static Response doRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("Accept-Encoding","gzip, deflate")
                .build();
        return getOkClient().newCall(request).execute();
    }

    private static OkHttpClient getOkClient() {
        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            CertificatePinner.Builder builder = new CertificatePinner.Builder();
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .certificatePinner(builder.build())
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }
        return client;
    }

    public static Response doRangeRequest(String url, long start, long end) throws IOException {
        Request build = new Request.Builder()
                .addHeader("RANGE", "bytes=" + start + "-" + end)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("Accept-Encoding","gzip, deflate")
                .url(url)
                .build();
        return getOkClient().newCall(build).execute();
    }
}
