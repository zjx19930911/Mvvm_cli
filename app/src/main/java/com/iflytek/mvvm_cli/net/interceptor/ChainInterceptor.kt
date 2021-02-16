package com.iflytek.mvvm_cli.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Created by Jianxin on 2021/1/26.
 * 添加固定的请求参数或者header
 */
class ChainInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
//                .header("", "")
            .method(request.method(), request.body())
            .build()
        val response = chain.proceed(request)


        return response
    }
}