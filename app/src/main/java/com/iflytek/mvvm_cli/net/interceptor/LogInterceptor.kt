package com.iflytek.mvvm_cli.net.interceptor

import com.socks.library.KLog
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset
import kotlin.jvm.Throws

/**
 * Created by Jianxin on on 2021/1/26.
 * 打印请求log
 */
class LogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val responseBody = response.body()
        val source = responseBody!!.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer()
        val contentType = responseBody.contentType()
        var charset =
            UTF8
        if (contentType != null) {
            charset = contentType.charset(UTF8)
            val contentLength = responseBody.contentLength()
            if (contentLength != 0L) {
                KLog.json(buffer.clone().readString(charset))
            }
        }
        return response
    }

    companion object {
        private val UTF8 = Charset.forName("UTF-8")
    }
}