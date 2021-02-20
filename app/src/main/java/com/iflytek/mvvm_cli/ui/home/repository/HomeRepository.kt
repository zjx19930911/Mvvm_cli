package com.iflytek.mvvm_cli.ui.home.repository

import com.iflytek.mvvm_cli.ui.home.api.HomeApi

class HomeRepository(private val mHomeApi: HomeApi) {

    suspend fun detail(page: Int) = mHomeApi.detail(page)

}