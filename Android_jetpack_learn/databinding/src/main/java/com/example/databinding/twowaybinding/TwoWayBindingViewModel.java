package com.example.databinding.twowaybinding;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.databinding.BR;

public class TwoWayBindingViewModel extends BaseObservable {
    private LoginModel loginModel;

    private String TAG = this.getClass().getName();

    public TwoWayBindingViewModel() {
        loginModel = new LoginModel();
        loginModel.userName = "Michael";
        loginModel.rememberMe = true;
    }

    @Bindable
    public String getUserName() {
        return loginModel.userName;
    }

    public void setUserName(String userName) {
        Log.e(TAG, "setUserName()->called, userName=" + userName);
        if (userName != null && !userName.equals(loginModel.userName)) {
            loginModel.userName = userName;
            //TODO 可以在此处理一些相关业务逻辑，例如，保存userName等。
            notifyPropertyChanged(BR.userName);
        }
    }

    /**
     * 不使用@={}的方式来实现双向绑定，就只能通过这样的方式接收EditText的回调
     */
//    public void onTextChanged(CharSequence s, int start, int before, int count)
//    {
//        Log.e(TAG, "setUserName()->called, newText="+s.toString());
//        notifyPropertyChanged(BR.userName);
//    }
    @Bindable
    public boolean getRememberMe() {
        return loginModel.rememberMe;
    }

    public void setRememberMe(boolean checked) {
        Log.e(TAG, "setRememberMe()->called, checked=" + checked);
        // Avoids infinite loops.
        if (loginModel.rememberMe != checked) {
            loginModel.rememberMe = checked;
            notifyPropertyChanged(BR.rememberMe);
        }
    }
}
