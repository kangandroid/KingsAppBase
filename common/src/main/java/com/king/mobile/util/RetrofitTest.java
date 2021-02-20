package com.king.mobile.util;

import com.king.mobile.base.Api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

class RetrofitTest {

    private static Api api;

    static Api getApi() {
        if (api == null) {
            OkHttpClient client = new OkHttpClient();
            CertificatePinner certificatePinner = client.certificatePinner();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .client(client)
                    .callbackExecutor(Executor.getInstance().executor())
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }
}
