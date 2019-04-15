package com.github.luoyemyy.mall.core.service

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.stereotype.Service

@Service
class HttpService {

    private val okHttpClient = OkHttpClient.Builder().build()

    fun get(url: String): String? {
        return okHttpClient.newCall(Request.Builder().url(url).build()).execute().let {
            if (it.isSuccessful) {
                it.body()?.string()
            } else {
                null
            }
        }
    }

    fun postXml(url: String, xml: String): String? {
        return okHttpClient.newCall(Request.Builder().url(url).post(RequestBody.create(MediaType.get("text/xml; charset=utf-8"), xml)).build()).execute().let {
            if (it.isSuccessful) {
                it.body()?.string()
            } else {
                null
            }
        }
    }
}