package com.github.luoyemyy.mall.core.wx

import com.github.luoyemyy.mall.base.config.AppletInfo
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.security.KeyStore
import java.security.SecureRandom
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext


@Service
class HttpService {

    @Autowired
    private lateinit var appletInfo: AppletInfo

    private val okHttpClient = OkHttpClient.Builder().build()

    private val certOkHttpClient: OkHttpClient by lazy {
        val password = appletInfo.mchId?.toCharArray()
        val keyStore = KeyStore.getInstance("PKCS12")
        keyStore.load(FileInputStream(appletInfo.certPath), password)
        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, password)
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(keyManagerFactory.keyManagers, null, SecureRandom())
        val sslSocketFactory = sslContext.socketFactory
        OkHttpClient.Builder().sslSocketFactory(sslSocketFactory).build()
    }


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

    fun postXmlUseCert(url: String, xml: String): String? {
        return certOkHttpClient.newCall(Request.Builder().url(url).post(RequestBody.create(MediaType.get("text/xml; charset=utf-8"), xml)).build()).execute().let {
            if (it.isSuccessful) {
                it.body()?.string()
            } else {
                null
            }
        }
    }

}