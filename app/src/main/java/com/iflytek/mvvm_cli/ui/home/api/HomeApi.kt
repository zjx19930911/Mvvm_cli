package com.iflytek.mvvm_cli.ui.home.api

import com.iflytek.mvvm_cli.net.BaseHttpBean
import com.iflytek.mvvm_cli.ui.home.bean.AccountBean
import retrofit2.http.GET
import retrofit2.http.Path


interface HomeApi {
    @GET("/article/list/{page}/json")
    suspend fun detail(@Path("page") page: Int): BaseHttpBean<AccountBean>
}