package com.iflytek.mvvm_cli.liveData

import com.iflytek.mvvm_cli.net.NetManager.SERVER_SUCCESS_CODE

/**
 * Created by Jianxin on 2021/2/4.
 * 把vm层成功和失败的结果合并成一个实体类给view层
 * 这样就不用写一个成功和一个失败LiveData了
 */

data class BaseLiveData<T>(
    var isSuccess: Boolean,
    var result: T? = null,
    var errorMessage: String? = null,
    var code: Int? = SERVER_SUCCESS_CODE
)