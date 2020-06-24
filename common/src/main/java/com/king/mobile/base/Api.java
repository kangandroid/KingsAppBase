package com.king.mobile.base;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("users/{user}/repos")
    Call<List<String>> listRepos(@Path("user") String user);
}
