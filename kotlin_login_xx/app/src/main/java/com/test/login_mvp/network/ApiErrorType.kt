package com.test.login_mvp.network

import android.content.Context
import com.test.login_mvp.R

enum class ApiErrorType(val code:Int,private val messageId:Int) {

    INTERNAL_SERVER_ERROR(500, R.string.service_error),
    BAD_GATEWAY(502,R.string.service_error),
    NOT_FOUND(404,R.string.not_found),
    CONNECT_TIMEOUT(408,R.string.network_error),
    NETWORK_NOT_CONNECT(499,R.string.network_error),
    UNKNOWN_ERROR(700,R.string.unknown_error);

    private val DEFAULT_CODE = 1

    fun getApiError(context: Context):ApiError{
        return ApiError(DEFAULT_CODE,context.getString(messageId))
    }



}