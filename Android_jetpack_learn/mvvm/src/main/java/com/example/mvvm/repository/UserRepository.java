package com.example.mvvm.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;


import com.example.mvvm.api.Api;
import com.example.mvvm.db.UserDao;
import com.example.mvvm.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 数据层，只对ViewModel负责，ViewModel不再需要关心数据从哪里来。
 * <p>
 * Room数据库是ViewModel的单一数据来源，刷新后数据也是直接写入Room数据库，通过LiveData暴露给ViewModel
 */
public class UserRepository {
    private String TAG = this.getClass().getName();
    private UserDao userDao;
    private Api api;

    public UserRepository(UserDao userDao, Api api) {
        this.api = api;
        this.userDao = userDao;
    }

    public LiveData<User> getUser(final String name) {
        refresh(name);
        return userDao.getUserByName(name);
    }

    public void refresh(String userName) {
        api.getUser(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    Log.e(TAG, "refresh()->response:" + response.body());
                    insertUser(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "refresh()->onFailure()");
            }
        });
    }

    private void insertUser(final User user) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
                Log.e(TAG, "insertUser()->" + user);
            }
        });
    }
}
