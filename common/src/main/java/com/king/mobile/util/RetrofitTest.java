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

    void retrofitTest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(new OkHttpClient())
                .callbackExecutor(Executor.getInstance().executor())
                .build();
        Api service = retrofit.create(Api.class);

        service.listRepos("octocat");
    }
}
