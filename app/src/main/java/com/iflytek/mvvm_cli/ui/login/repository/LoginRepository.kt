package com.iflytek.mvvm_cli.ui.login.repository

import com.iflytek.mvvm_cli.ui.login.api.LoginApi

class LoginRepository(private val mLoginApi: LoginApi) {

    suspend fun login(user: String, password: String) = mLoginApi.login(user, password)

}