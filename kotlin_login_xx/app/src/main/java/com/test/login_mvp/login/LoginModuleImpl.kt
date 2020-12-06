package com.test.login_mvp.login

import android.content.Context
import com.test.login_mvp.api.WanAndroidApi
import com.test.login_mvp.bean.LoginBean
import com.test.login_mvp.network.ApiClient
import com.test.login_mvp.network.ApiError
import com.test.login_mvp.network.ApiResponse
import com.test.login_mvp.network.NetworkScheduler

class LoginModuleImpl : LoginModue {
    override fun cancelRequest() {

    }

    override fun login(
        context: Context,
        username: String,
        password: String,
        onLoginListener: LoginPresenter.OnLoginListener
    ) {
        ApiClient.instance.getService(WanAndroidApi::class.java).loginWanAndroid(username, password)
            .compose(NetworkScheduler.compose())
            .subscribe(object : ApiResponse<LoginBean>(context) {
                override fun success(data: LoginBean) {
                    onLoginListener.loginSuccess(data)
                }

                override fun failure(statusCode: Int, apiError: ApiError) {
                    onLoginListener.loginFailed(apiError.message)
                }

            })
    }

    override fun register(
        context: Context,
        username: String,
        password: String,
        repassword: String,
        onRegisterListener: LoginPresenter.OnRegisterListener
    ) {
        ApiClient.instance.getService(WanAndroidApi::class.java)
            .registerWanAndroid(username, password, repassword)
            .compose(NetworkScheduler.compose())
            .subscribe(object : ApiResponse<LoginBean>(context) {
                override fun success(data: LoginBean) {
                    onRegisterListener.registerSuccess(loginBean = data)
                }

                override fun failure(statusCode: Int, apiError: ApiError) {
                    onRegisterListener.registerFailed(apiError.message)
                }

            })
    }


}