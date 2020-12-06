package com.test.login_mvp.login


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.test.login_mvp.R
import com.test.login_mvp.bean.LoginBean
import com.test.login_mvp.toast
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : RxAppCompatActivity(), LoginView {

    //loginPresenter初始化方式二：调用注册登录有问题
//    private val loginPresenter: LoginPresenterImpl by lazy {
//        LoginPresenterImpl(this)
//    }

    //loginPresenter初始化方式一：
    private lateinit var loginPresenter : LoginPresenter

    //private static final String TAG = "Zero";
    companion object {
        //伴生对象 一个类只能有一个
        val TAG = "Zero"
    }

//    object TAGS{
//        val TAG1 = "Zero"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenterImpl(this)//loginPresenter初始化方式一：
        login.setOnClickListener(onClickListener)
        register.setOnClickListener(onClickListener)
//        loginPresenter.attachView()
    }

    override fun onDestroy() {
        super.onDestroy()
//        loginPresenter.dettachView()
    }

    private val onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.login -> {
                //调用登录
                val usernameStr = username.text.toString()
                val passwordStr = password.text.toString()
                Log.i(TAG, "username: $usernameStr, password: $passwordStr")
                loginPresenter.loginWanAndroid(this@LoginActivity, usernameStr, passwordStr)
            }
            R.id.register -> {
                //调用注册
                val usernameStr = username.text.toString()
                val passwordStr = password.text.toString()
                loginPresenter.registerWanAndroid(
                    this@LoginActivity,
                    usernameStr,
                    passwordStr,
                    passwordStr
                )
            }
        }

    }

    override fun loginSucces(loginBean: LoginBean) {
        Toast.makeText(this@LoginActivity, loginBean.toString(), Toast.LENGTH_LONG).show()
        Log.i(TAG, "loginBean: $loginBean")
    }

    override fun loginFailure(errorMsg: String) {
        toast("")
        Log.i("Zero", "failure")
    }


}
