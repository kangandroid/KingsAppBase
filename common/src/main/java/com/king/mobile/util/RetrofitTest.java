package com.king.mobile.util;

import com.king.mobile.base.Api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

class RetrofitTest {

    private static Api api;

    static Api getApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .client(new OkHttpClient())
                    .callbackExecutor(Executor.getInstance().executor())
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }
}
