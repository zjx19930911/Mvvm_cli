package com.iflytek.mvvm_cli.ui.home.api

import com.iflytek.mvvm_cli.net.BaseHttpBean
import com.iflytek.mvvm_cli.ui.home.bean.AccountBean
import io.reactivex.Single
import retrofit2.http.GET


interface HomeApi{
    @GET("/wxarticle/chapters/json")
    suspend fun detail(): BaseHttpBean<List<AccountBean>>
}