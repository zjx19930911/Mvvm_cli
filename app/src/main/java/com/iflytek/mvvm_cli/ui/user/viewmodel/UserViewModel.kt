package com.iflytek.mvvm_cli.ui.user.viewmodel

import android.app.Application
import com.iflytek.mvvm_cli.base.BaseViewModel
import com.iflytek.mvvm_cli.extens.postResult
import com.iflytek.mvvm_cli.liveData.MyMutableLiveData
import com.iflytek.mvvm_cli.room.User
import com.iflytek.mvvm_cli.ui.user.bean.UserBean
import com.iflytek.mvvm_cli.ui.user.repository.UserRepository
import kotlin.random.Random

class UserViewModel(var userRepo: UserRepository, application: Application) :
    BaseViewModel(application) {

    val detailResult = MyMutableLiveData<UserBean>()
    val queryResult = MyMutableLiveData<List<User>>()
    val insertResult = MyMutableLiveData<Long>()

    fun detail() {
        launchHttp({ userRepo.detail() }, {
            detailResult.postResult(true, it)
        }, { msg, code ->
            detailResult.postResult(false, errorMessage = msg, code = code)
        })
    }

    fun insert() {
        launcherRoom({ userRepo.userInsert(User("插入的user" + Random.nextInt(1000))) }, {
            insertResult.postResult(true, it)
        }, {
            insertResult.postResult(false, errorMessage = "插入失败")
        })
    }

    fun query() {
        launcherRoom({ userRepo.userQuery() }, {
            queryResult.postResult(true, it)
        }, {
            queryResult.postResult(false, errorMessage = "查询失败")
        })
    }
}