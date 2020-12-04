package com.test.login_mvp.network

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.test.login_mvp.bean.ResponseWrapper
import com.test.login_mvp.view.LoadingDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApiResponse<T>(private val context: Context) : Observer<ResponseWrapper<out T>> {

    private var isShowLoading = true

    constructor(context: Context, isShowLoading: Boolean = false) : this(context) {
        this.isShowLoading = isShowLoading
    }

    abstract fun success(data: T)
    abstract fun failure(statusCode: Int, apiError: ApiError)

    override fun onSubscribe(d: Disposable) {
        //加载一个菊花圈
        if (isShowLoading) {
            LoadingDialog.show(context)
        }
    }



    override fun onNext(t: ResponseWrapper<out T>) {
        Log.i("Zero", "onNext: $t")
        success(t.data)
    }

    override fun onError(e: Throwable) {
        Log.i("Zero", "onError: ${e.message}")
        //取消加载
        LoadingDialog.cancle()
        //失败
        if (e is HttpException) {
            val apiError: ApiError = when (e.code()) {
                ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                    ApiErrorType.INTERNAL_SERVER_ERROR.getApiError(context)
                ApiErrorType.BAD_GATEWAY.code ->
                    ApiErrorType.BAD_GATEWAY.getApiError(context)
                ApiErrorType.NOT_FOUND.code ->
                    ApiErrorType.NOT_FOUND.getApiError(context)
                else -> otherError(e)
            }
            failure(e.code(), apiError)
            return
        }

        val apiErrorType: ApiErrorType = when (e) {
            is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECT_TIMEOUT
            else -> ApiErrorType.UNKNOWN_ERROR
        }
        failure(apiErrorType.code, apiErrorType.getApiError(context))
    }

    private fun otherError(e: HttpException) =
        Gson().fromJson(e.response().errorBody()?.charStream(), ApiError::class.java)

    override fun onComplete() {
        Log.i("Zero", "onComplete")
        //取消加载
        LoadingDialog.cancle()
    }


}