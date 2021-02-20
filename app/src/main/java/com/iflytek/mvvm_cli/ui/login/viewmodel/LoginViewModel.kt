package com.iflytek.mvvm_cli.ui.login.viewmodel

import android.app.Application
import com.iflytek.mvvm_cli.base.BaseViewModel
import com.iflytek.mvvm_cli.extens.postResult
import com.iflytek.mvvm_cli.liveData.MyMutableLiveData
import com.iflytek.mvvm_cli.ui.login.bean.LoginBean
import com.iflytek.mvvm_cli.ui.login.repository.LoginRepository

class LoginViewModel(var loginRepo: LoginRepository, application: Application) :
    BaseViewModel(application) {

    val loginResult = MyMutableLiveData<LoginBean>()

    fun login(user: String, password: String) {
        launchHttp({
            loginRepo.login(user, password)
        }, {
            loginResult.postResult(true, it)
        }, { msg, code ->
            loginResult.postResult(false, errorMessage = msg, code = code)
        })
    }


}