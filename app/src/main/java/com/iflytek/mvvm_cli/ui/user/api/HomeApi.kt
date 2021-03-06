package com.iflytek.mvvm_cli.ui.user.api

import com.iflytek.mvvm_cli.net.BaseHttpBean
import com.iflytek.mvvm_cli.ui.user.bean.UserBean
import retrofit2.http.GET


interface UserApi{
    @GET("/wenda/list/1/json")
    suspend fun detail(): BaseHttpBean<UserBean>
}