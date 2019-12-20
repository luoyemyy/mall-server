package com.github.luoyemyy.mall.base.advice

import com.github.luoyemyy.mall.controller.response.ApiResponse
import com.github.luoyemyy.mall.controller.response.apiResponse
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.util.JsonUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse
import javax.validation.ConstraintViolationException

@Suppress("unused")
@RestControllerAdvice
class Advice {
    private val logger = LoggerFactory.getLogger("Advice")

    @Autowired
    private lateinit var response: HttpServletResponse

    private fun result(code: Int, msg: String): ApiResponse {
        return apiResponse(code, msg).apply {
            logger.error(">>>>>>:{}", JsonUtil.objectMapper.writeValueAsString(this))
        }
    }

    private fun result(code: Int): ApiResponse {
        return apiResponse(code).apply {
            logger.error(">>>>>>:{}", JsonUtil.objectMapper.writeValueAsString(this))
        }
    }

    @ExceptionHandler(AppException::class)
    fun mallException(e: AppException): ApiResponse {
        if (e.cause() != null) {
            logger.error("mallException", e)
        }
        return result(e.code(), e.codeMsg())
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun validException(e: ConstraintViolationException): ApiResponse {
//        logger.error("validException", e)
        return result(1, e.message ?: "")
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validException(e: MethodArgumentNotValidException): ApiResponse {
        logger.error("validException", e)
        return result(1, e.message)
    }

//    @ExceptionHandler(NoHandlerFoundException::class)
//    fun noHandlerFoundException(e: NoHandlerFoundException): ApiResponse {
//        logger.error("noHandlerFoundException", e)
//        return result(Code.INVALID_URL, Code.msg(Code.INVALID_URL))
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
//    fun httpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): ApiResponse {
//        logger.error("httpRequestMethodNotSupportedException", e)
//        return result(Code.INVALID_METHOD)
//    }

    @ExceptionHandler(Throwable::class)
    fun otherException(e: Throwable) {
        logger.error("otherException", e)
        response.status = 400
    }
}