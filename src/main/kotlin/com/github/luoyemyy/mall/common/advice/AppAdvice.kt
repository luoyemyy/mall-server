package com.github.luoyemyy.mall.common.advice

import com.github.luoyemyy.mall.controller.response.ApiResponse
import com.github.luoyemyy.mall.util.toJsonString
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolationException
import javax.validation.Path

@RestControllerAdvice
class AppAdvice {
    private val logger = LoggerFactory.getLogger(AppAdvice::class.java)
    @Autowired
    private lateinit var logWriter: LogWriter

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun paramError(e: MethodArgumentNotValidException): ApiResponse {
        val msg = e.bindingResult.fieldErrors.joinToString(",") {
            "${it.field}:${it.defaultMessage}"
        }
        logger.error("AppAdvice.paramError：{}", msg)
        return getResponse(paramException(msg))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun paramError(e: ConstraintViolationException): ApiResponse {
        val msg = e.constraintViolations.joinToString(",") {
            "${getPathName(it.propertyPath)}:${it.message}"
        }
        logger.error("AppAdvice.paramError：{}", msg)
        return getResponse(paramException(msg))
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun paramError(e: MethodArgumentTypeMismatchException): ApiResponse {
        logger.error("AppAdvice.paramError：{}", e.message, e)
        return getResponse(paramException(e.message))
    }

    @ExceptionHandler(AppException::class)
    fun appError(e: AppException): ApiResponse {
        logger.error("AppAdvice.appError：{}", e.errorMsg(), e)
        return getResponse(e)
    }

    @ExceptionHandler(Throwable::class)
    fun throwable(e: Throwable): ApiResponse {
        logger.error("AppAdvice.throwable：{}", e.message, e)
        return getResponse(appException(e.message))
    }

    private fun getResponse(e: AppException): ApiResponse {
        return ApiResponse().apply {
            code = e.code()
            codeMsg = e.codeMsg()
            errorMsg = e.errorMsg()
            logWriter.postLog(this.toJsonString())
        }
    }

    private fun getPathName(path: Path?): String? {
        val iterator: Iterator<Path.Node> = path?.iterator() ?: return null
        var name: String? = null
        while (iterator.hasNext()) {
            name = iterator.next().name
        }
        return name
    }
}