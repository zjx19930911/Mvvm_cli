package com.iflytek.mvvm_cli.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.iflytek.mvvm_cli.MyApplication
import com.iflytek.mvvm_cli.base.BaseViewModel
import com.iflytek.mvvm_cli.extens.logD
import com.iflytek.mvvm_cli.extens.postListResult
import com.iflytek.mvvm_cli.extens.postResult
import com.iflytek.mvvm_cli.liveData.MyListMutableLiveData
import com.iflytek.mvvm_cli.liveData.MyMutableLiveData
import com.iflytek.mvvm_cli.ui.home.bean.AccountBean
import com.iflytek.mvvm_cli.ui.home.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(var homeRepo: HomeRepository, application: Application) :
    BaseViewModel(application) {

    val detailResult = MyListMutableLiveData<AccountBean>()

    fun detail() {
        launchHttp({ homeRepo.detail() }, {
            detailResult.postListResult(true, it)
        }, { msg, code ->
            detailResult.postListResult(false, errorMessage = msg, code = code)
        })
    }


}