package com.test.login_mvp.login

import android.content.Context
import com.test.login_mvp.bean.LoginBean

class LoginPresenterImpl(private var loginView: LoginView?) : LoginPresenter,LoginPresenter.OnLoginListener
,LoginPresenter.OnRegisterListener{

    private val loginModue: LoginModue = LoginModuleImpl()
    override fun attachView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dettachView() {
        loginView = null
        loginModue.cancleRequst()
    }

    override fun loginWanAndroid(context: Context, username: String, password: String) {
        loginModue.login(context,username,password,this)
    }

    override fun registerWanAndroid(
        context: Context,
        username: String,
        password: String,
        repassword: String
    ) {
        loginModue.register(context,username,password,password,this)
    }

    override fun loginSuccess(loginBean: LoginBean) {
        loginView?.loginSucces(loginBean)
    }

    override fun loginFailed(errorMsg: String) {
        loginView?.loginFailure(errorMsg)
    }

    override fun registerSuccess(loginBean: LoginBean) {
    }

    override fun registerFailed(errorMsg: String) {
    }


}