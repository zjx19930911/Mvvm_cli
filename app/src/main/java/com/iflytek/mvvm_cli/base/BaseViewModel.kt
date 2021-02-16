package com.iflytek.mvvm_cli.base

import android.app.Application
import android.net.ParseException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParseException
import com.iflytek.mvvm_cli.extens.logD
import com.iflytek.mvvm_cli.net.BaseHttpBean
import com.iflytek.mvvm_cli.net.NetManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.HttpException

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * 统一处理http请求的错误
     * 函数直接返回正确的结果
     */
    fun <T> launchHttp(
        block: suspend CoroutineScope.() -> BaseHttpBean<T>,
        httpSuccess: ((T?) -> Unit)? = null,
        httpError: ((errorMsg: String?, errorCode: Int?) -> Unit)? = null
    ) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) { block() }
                if (result.errorCode == NetManager.SERVER_SUCCESS_CODE) {
                    httpSuccess?.invoke(result.data)
                } else {
                    httpError?.invoke(result.errorMsg, result.errorCode)
                }
            } catch (e: Throwable) {
                logD(e.message)
                if (e is HttpException) {
                    httpError?.invoke("网络错误", 500)
                } else if (e is JsonParseException
                    || e is JSONException
                    || e is ParseException
                ) {
                    httpError?.invoke("解析错误", 501)
                } else {
                    httpError?.invoke("未知错误", 502)
                }
            } finally {

            }

        }
    }

    fun <T> launcherRoom(
        block: suspend CoroutineScope.() -> T?,
        roomSuccess: ((T?) -> Unit)? = null,
        roomError: ((errorMsg: String?) -> Unit)? = null
    ) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) { block() }
                roomSuccess?.invoke(result)
            } catch (e: Throwable) {
                logD(e.message)
                roomError?.invoke("查询错误")
            } finally {

            }
        }
    }

}