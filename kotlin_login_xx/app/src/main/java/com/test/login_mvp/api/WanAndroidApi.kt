package com.test.login_mvp.api

import com.test.login_mvp.bean.LoginBean
import com.test.login_mvp.bean.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanAndroidApi {

    //    username，password
    @POST("/user/login")
    @FormUrlEncoded
    fun loginWanAndroid(
        @Field("username") username: String
        , @Field("password") password: String
    ): Observable<ResponseWrapper<LoginBean>>

    //    username,password,repassword
    @POST("/user/register")
    @FormUrlEncoded
    fun registerWanAndroid(
        @Field("username") username: String
        , @Field("password") password: String
        , @Field("repassword") repassword: String
    ): Observable<ResponseWrapper<LoginBean>>

}