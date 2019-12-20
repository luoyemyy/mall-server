package com.github.luoyemyy.mall.common.advice

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class LogWriter {
    private val logger = LoggerFactory.getLogger(LogWriter::class.java)

    fun preLog(method: String?, pathId: Int, path: String?, token: String?, params: String?) {
        val preLog = "\n\n" +
                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n" +
                "method:{}, path:{}, token:{}, params:{}\n" +
                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n"
        logger.info(preLog, method, path, token, params ?: "")
    }

    fun postLog(response: String?) {
        val postLog = "\n\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" +
                "{}\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
        logger.info(postLog, response)
    }
}