package com.test.login_mvp.login

import com.test.login_mvp.bean.LoginBean


interface LoginView{
    fun loginSucces(loginBean: LoginBean)
    fun loginFailure(errorMsg:String)

}