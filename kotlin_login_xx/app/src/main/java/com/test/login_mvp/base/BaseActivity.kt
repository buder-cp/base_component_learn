package com.test.login_mvp.base

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import java.io.Serializable
//<p extends M & M1>
abstract class BaseActivity<P> :RxAppCompatActivity() where P: IBasePresenter, P:Serializable{

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createP()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

     abstract fun createP():P

}