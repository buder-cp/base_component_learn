package com.test.login_mvp.login

import android.content.Context
import com.test.login_mvp.base.IBasePresenter
import com.test.login_mvp.bean.LoginBean

interface LoginPresenter : IBasePresenter {

    fun loginWanAndroid(context: Context,username:String,password:String)
    fun registerWanAndroid(context: Context,username:String,password:String,repassword:String)

    interface OnLoginListener{
        fun loginSuccess(loginBean: LoginBean)
        fun loginFailed(errorMsg:String)
    }

    interface OnRegisterListener{
        fun registerSuccess(loginBean:LoginBean)
        fun registerFailed(errorMsg:String)
    }
}