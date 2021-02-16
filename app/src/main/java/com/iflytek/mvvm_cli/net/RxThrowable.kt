package com.iflytek.mvvm_cli.net

/**
 * Created by Jianxin on 2021/2/2.
 * 自定义rx的异常类
 */

open class RxThrowable(override var message: String?, open var code: Int? = 1000) : Throwable()

