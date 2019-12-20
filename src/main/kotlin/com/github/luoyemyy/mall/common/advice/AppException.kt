package com.github.luoyemyy.mall.common.advice

class AppException(private val appCode: AppCode,
                   private val errorMsg: String? = null,
                   private val throwable: Throwable? = null) : RuntimeException(appCode.msg, throwable) {

    fun code(): Int {
        return appCode.code
    }

    fun codeMsg(): String {
        return appCode.msg
    }

    fun errorMsg(): String? {
        return errorMsg
    }

    fun cause(): Throwable? {
        return throwable
    }
}

fun paramException(errorMsg: String? = null, throwable: Throwable? = null): AppException {
    return AppException(AppCode.INVALID_PARAM, errorMsg, throwable)
}

fun appException(errorMsg: String? = null, throwable: Throwable? = null): AppException {
    return AppException(AppCode.FAIL, errorMsg, throwable)
}

fun authException():AppException{
    return AppException(AppCode.INVALID_TOKEN)
}

