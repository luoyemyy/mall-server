package com.github.luoyemyy.mall.base.advice

class MallException(private val errorCode: Int, private val errorMsg: String? = null, private val throwable: Throwable? = null) : RuntimeException() {

    constructor(errorCode: Int = Code.FAILURE) : this(errorCode, null, null)

    fun code(): Int {
        return errorCode
    }

    fun msg(): String {
        return errorMsg ?: Code.msg(errorCode)
    }

    fun cause(): Throwable? {
        return throwable
    }
}