package com.iflytek.mvvm_cli.ui.login.api

import com.iflytek.mvvm_cli.net.BaseHttpBean
import com.iflytek.mvvm_cli.ui.login.bean.LoginBean
import retrofit2.http.*


interface LoginApi {
    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseHttpBean<LoginBean>
}