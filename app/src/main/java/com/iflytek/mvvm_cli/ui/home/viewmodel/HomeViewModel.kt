package com.iflytek.mvvm_cli.ui.home.viewmodel

import android.app.Application
import com.iflytek.mvvm_cli.base.BaseViewModel
import com.iflytek.mvvm_cli.extens.postResult
import com.iflytek.mvvm_cli.liveData.MyMutableLiveData
import com.iflytek.mvvm_cli.ui.home.bean.AccountBean
import com.iflytek.mvvm_cli.ui.home.repository.HomeRepository

class HomeViewModel(var homeRepo: HomeRepository, application: Application) :
    BaseViewModel(application) {

    val detailResult = MyMutableLiveData<AccountBean>()

    fun detail(page: Int) {
        launchHttp({
            homeRepo.detail(page)
        }, {
            detailResult.postResult(true, it)
        }, { msg, code ->
            detailResult.postResult(false, errorMessage = msg, code = code)
        })
    }


}