package com.example.databinding.twowaybinding;

import android.util.Log;

import androidx.databinding.ObservableField;

/**
 * 不继承自BaseObservable，而采用ObservableField的方式
 * */
public class TwoWayBindingFieldViewModel
{
    private String TAG = this.getClass().getName();

    private ObservableField<LoginModel> loginModelObservableField;

    public TwoWayBindingFieldViewModel()
    {
        LoginModel loginModel = new LoginModel();
        loginModel.userName = "Michael";
        loginModel.rememberMe = true;

        loginModelObservableField = new ObservableField<>();
        loginModelObservableField.set(loginModel);
    }

    public String getUserName()
    {
        Log.e(TAG, "getUserName()");
        return loginModelObservableField.get().userName;
    }

    public void setUserName(String userName)
    {
        Log.e(TAG, "setUserName()->"+userName);
        loginModelObservableField.get().userName = userName;
    }

    public boolean getRememberMe()
    {
        Log.e(TAG, "getRememberMe()");
        return loginModelObservableField.get().rememberMe;
    }

    public void setRememberMe(boolean rememberMe)
    {
        Log.e(TAG, "setRememberMe()->"+rememberMe);
        loginModelObservableField.get().rememberMe = rememberMe;
    }
}
