package com.test.login_mvp.login

import android.content.Context

interface LoginModue {
    fun cancleRequst()
    fun login(
        context: Context,
        username: String,
        password: String,
        onLoginListener: LoginPresenter.OnLoginListener
    )

    fun register(
        context: Context,
        username: String,
        password: String,
        repassword: String,
        onRegisterListener: LoginPresenter.OnRegisterListener
    )
}