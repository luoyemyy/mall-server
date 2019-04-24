package com.github.luoyemyy.mall.core.service

import com.github.luoyemyy.mall.base.config.AppletInfo
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.util.*
import javax.net.ssl.*


@Service
class HttpService {

    @Autowired
    private lateinit var appletInfo: AppletInfo

    private val okHttpClient = OkHttpClient.Builder().build()

    private val certOkHttpClient: OkHttpClient by lazy {
        val trustManager: X509TrustManager
        val sslSocketFactory: SSLSocketFactory
        try {
            trustManager = trustManagerForCertificates(trustedCertificatesInputStream())
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
            sslSocketFactory = sslContext.socketFactory
        } catch (e: GeneralSecurityException) {
            throw RuntimeException(e)
        }
        OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, trustManager).build()
    }

    private fun trustedCertificatesInputStream(): InputStream {
        return FileInputStream(appletInfo.certPath)
    }

    @Throws(GeneralSecurityException::class)
    private fun trustManagerForCertificates(`in`: InputStream): X509TrustManager {
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val certificates = certificateFactory.generateCertificates(`in`)
        if (certificates.isEmpty()) {
            throw IllegalArgumentException("expected non-empty set of trusted certificates")
        }

        // Put the certificates a key store.
        val password = appletInfo.mchId?.toCharArray()!! // Any password will work.
        val keyStore = newEmptyKeyStore(password)
        for ((index, certificate) in certificates.withIndex()) {
            val certificateAlias = Integer.toString(index)
            keyStore.setCertificateEntry(certificateAlias, certificate)
        }

        // Use it to build an X509 trust manager.
        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, password)
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(keyStore)
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        return trustManagers[0] as X509TrustManager
    }

    @Throws(GeneralSecurityException::class)
    private fun newEmptyKeyStore(password: CharArray): KeyStore {
        try {
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            val `in`: InputStream? = null // By convention, 'null' creates an empty key store.
            keyStore.load(`in`, password)
            return keyStore
        } catch (e: IOException) {
            throw AssertionError(e)
        }

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