package com.iflytek.mvvm_cli.net

/**
 * Created by Jianxin on 2021/1/8.
 * 处理请求data为对象的数据
 * {
 *  "errorCode":"结果代号",
 *  "errorMsg":"消息文本"
 *  "data":{}
 * }
 *
 */
class BaseHttpBean<T> {
    var data: T? = null
    var errorMsg: String? = null
    var errorCode = 0
}