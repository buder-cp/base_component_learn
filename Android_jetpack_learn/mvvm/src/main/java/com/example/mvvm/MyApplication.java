package com.example.mvvm;

import android.app.Application;

import com.example.mvvm.api.Api;
import com.example.mvvm.api.RetrofitClient;
import com.example.mvvm.db.UserDatabase;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        userDatabase = UserDatabase.getInstance(this);
        api = RetrofitClient.getInstance().getApi();
    }

    private static UserDatabase userDatabase;
    private static Api api;

    public static Api getApi() {
        return api;
    }

    public static UserDatabase getUserDatabase() {
        return userDatabase;
    }

}
