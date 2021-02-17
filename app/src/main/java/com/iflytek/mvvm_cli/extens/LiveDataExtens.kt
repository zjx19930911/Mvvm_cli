package com.iflytek.mvvm_cli.extens

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.iflytek.mvvm_cli.liveData.BaseLiveData
import com.iflytek.mvvm_cli.liveData.MyMutableLiveData
import com.iflytek.mvvm_cli.net.NetManager.SERVER_SUCCESS_CODE

/**
 * Created by Jianxin on 2021/2/4.
 */

/**
 * View层注册此函数来接受成功与失败结果
 * LiveData数据过滤的扩展函数
 */
fun <T> MyMutableLiveData<T>.observerFilter(
    mainActivity: LifecycleOwner,
    successCallback: ((t: T?) -> Unit)? = null,
    errorCallback: ((message: String?, code: Int?) -> Unit)? = null
) =
    this.observe(mainActivity, Observer<BaseLiveData<T>> {
        if (it.isSuccess) {
            successCallback?.invoke(it.result)
        } else {
            errorCallback?.invoke(it.errorMessage, it.code)
        }
    })



/**
 * ViewModel层发送LiveData数据给View
 * @param isSuccess 必须填写，成功或者失败
 */
fun <T> MyMutableLiveData<T>.postResult(
    isSuccess: Boolean,
    t: T? = null,
    errorMessage: String? = null,
    code: Int? = SERVER_SUCCESS_CODE
) {
    this.postValue(BaseLiveData(isSuccess, t, errorMessage, code))
}
