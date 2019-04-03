package com.github.luoyemyy.mall.core.service

import okhttp3.OkHttpClient
import okhttp3.Request
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
}