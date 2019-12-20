package com.github.luoyemyy.mall.core.service.wx

import com.github.luoyemyy.mall.common.properties.AppletInfo
import org.apache.http.client.fluent.Request
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.ssl.SSLContextBuilder
import org.apache.http.util.EntityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import javax.net.ssl.SSLContext


@Service
class HttpService {

    private val logger: Logger = LoggerFactory.getLogger(HttpService::class.java)

    @Autowired
    private lateinit var appletInfo: AppletInfo

    @Throws(Exception::class)
    private fun getCertClient(): CloseableHttpClient? {
        val password: CharArray = appletInfo.mchId?.toCharArray() ?: return null
        val certPath: String = appletInfo.certPath ?: return null
        val sslContext: SSLContext = SSLContextBuilder.create()
                .setKeyStoreType("PKCS12")
                .loadKeyMaterial(File(certPath), password, password).build()
        return HttpClientBuilder.create().setSSLContext(sslContext).build()
    }

    fun get(url: String): String? {
        try {
            return Request.Get(url).execute().returnContent().asString()
        } catch (e: Throwable) {
            logger.error("HttpService.get", e)
        }
        return null
    }

    fun postXml(url: String, xml: String): String? {
        try {
            return Request.Post(url).bodyString(xml, ContentType.TEXT_XML).execute().returnContent().asString()
        } catch (e: Throwable) {
            logger.error("HttpService.postXml", e)
        }
        return null
    }

    fun postXmlUseCert(url: String, xml: String): String? {
        try {
            val client = getCertClient()
            val post = HttpPost(url)
            post.entity = StringEntity(xml, ContentType.TEXT_XML)
            val response = client!!.execute(post)
            val s = EntityUtils.toString(response.entity, "UTF-8")
            response.close()
            return s
        } catch (e: Throwable) {
            logger.error("HttpService.postXmlBodyCert", e)
        }
        return null
    }

}