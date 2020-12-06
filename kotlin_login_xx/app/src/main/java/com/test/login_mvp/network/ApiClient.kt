package com.test.login_mvp.network

import android.util.Log
import com.test.login_mvp.api.WanAndroidApi
import com.trello.rxlifecycle2.kotlin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {//这里应该是一个单例

    //需要返回一个单例

    lateinit var service: WanAndroidApi
    lateinit var retrofit: Retrofit

    private object Holder {
        val INSTANCE = ApiClient()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    internal class HttpLogger : HttpLoggingInterceptor.Logger {
        private val mMessage = StringBuilder()
        override fun log(message: String) { // 请求或者响应开始
            var message = message
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0)
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            val isJson = (message.startsWith("{") && message.endsWith("}")
                    || message.startsWith("[") && message.endsWith("]"))
            if (isJson) {
                message = JsonUtil.formatJson(message)
            }
            mMessage.append(message + "\n")
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Log.d("Zero", mMessage.toString())
            }
        }
    }

    fun createRetrofit() {
//        val okHttpClient = OkHttpClient().newBuilder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(//if else
//                if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
//                else HttpLoggingInterceptor.Level.NONE
//            ))
//            .build()

        val okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(
                HttpLoggingInterceptor().setLevel(//if else
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )
            )
        }.build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        service = retrofit.create(WanAndroidApi::class.java)
    }

    fun <T> getService(service: Class<T>): T = retrofit.create(service)


}