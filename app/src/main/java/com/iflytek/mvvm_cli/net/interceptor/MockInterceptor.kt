package com.iflytek.mvvm_cli.net.interceptor

import okhttp3.*
import java.io.IOException
import kotlin.jvm.Throws


/**
 * 模拟返回请求的拦截器
 * Created by Jianxin on 2021/1/29.
 */
class MockInterceptor(private val responseString: String? = null) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        responseString?.let {
            return Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        }
        return chain.proceed(chain.request())

    }

}