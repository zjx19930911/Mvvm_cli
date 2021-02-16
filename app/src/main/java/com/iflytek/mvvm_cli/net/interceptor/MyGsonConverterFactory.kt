package com.iflytek.mvvm_cli.net.interceptor

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.iflytek.mvvm_cli.net.BaseHttpBean
import com.iflytek.mvvm_cli.net.NetManager.SERVER_SUCCESS_CODE
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset
import kotlin.jvm.Throws

/**
 * 服务端返回的异常code时，剔除data，返回错误信息
 * 防止retrofit取不到错误message，直接报解析异常，
 * Created by Jianxin on 2020/6/23.
 */
class MyGsonConverterFactory private constructor(gson: Gson?) : Converter.Factory() {
    private val gson: Gson
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return MyGsonResponseBodyConverter<Any>(gson, type)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }

    internal inner class MyGsonRequestBodyConverter<T>(
        private val gson: Gson,
        private val adapter: TypeAdapter<T>
    ) : Converter<T, RequestBody> {
        private val MEDIA_TYPE =
            MediaType.parse("application/json; charset=UTF-8")
        private val UTF_8 = Charset.forName("UTF-8")

        @Throws(IOException::class)
        override fun convert(value: T): RequestBody {
            val buffer = Buffer()
            val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
            val jsonWriter = gson.newJsonWriter(writer)
            adapter.write(jsonWriter, value)
            jsonWriter.close()
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
        }

    }

    inner class MyGsonResponseBodyConverter<T> internal constructor(
        private val gson: Gson,
        private val type: Type
    ) : Converter<ResponseBody, T> {
        @Throws(IOException::class)
        override fun convert(value: ResponseBody): T {
            val response = value.string()
            value.use {
                return try {
                    val baseBean = gson.fromJson(response, BaseHttpBean::class.java);//拦截code
                    if (baseBean.errorCode != SERVER_SUCCESS_CODE) {
                        gson.fromJson(gson.toJson(baseBean), type);
                    } else {
                        gson.fromJson(response, type);
                    }
                } catch (e: Exception) {
                    throw e
                }
            }
        }

    }

    companion object {
        /**
         * Create an instance using `gson` for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         */
        /**
         * Create an instance using a default [Gson] instance for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         */
        @JvmOverloads
        fun create(gson: Gson? = Gson()): MyGsonConverterFactory {
            return MyGsonConverterFactory(
                gson
            )
        }
    }

    init {
        if (gson == null) throw NullPointerException("gson == null")
        this.gson = gson
    }
}