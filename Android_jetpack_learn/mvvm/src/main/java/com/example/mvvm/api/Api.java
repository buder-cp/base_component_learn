package com.example.mvvm.api;

import com.example.mvvm.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    /**
     * 获取GitHub用户信息
     * https://api.github.com/users/michaelye
     */
    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") String userId);
}
