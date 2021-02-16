package com.iflytek.mvvm_cli.liveData

import com.iflytek.mvvm_cli.net.NetManager

/**
 * Created by Jianxin on 2021/2/4.
 */

data class BaseListLiveData<T>(
    var isSuccess: Boolean,
    var result: List<T>? = null,
    var errorMessage: String? = null,
    var code: Int? = NetManager.SERVER_SUCCESS_CODE
)