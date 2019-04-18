package com.github.luoyemyy.mall.base.advice


object Code {

    const val SUCCESS = 0
    const val FAILURE = 1
    const val INVALID_URL = 2
    const val INVALID_PARAM = 3
    const val INVALID_METHOD = 4
    const val INVALID_TOKEN = 5
    const val INVALID_ADMIN = 6

    const val LOGIN_FAILURE = 7
    const val PRODUCT_NOT_EXIST = 8
    const val INVALID_ROLE = 9
    const val EXIST_MANAGER = 10
    const val OLD_PASSWORD_ERROR = 11

    const val BOOK_ORDER_USER_ERROR = 12
    const val BOOK_ORDER_MONEY_ERROR = 13
    const val BOOK_ORDER_ADDRESS_ERROR = 13
    const val BOOK_ORDER_PRODUCT_ERROR = 14
    const val BOOK_ORDER_FAIL = 15


    private val map = mapOf(
            SUCCESS to "请求成功",
            FAILURE to "服务器错误",
            INVALID_URL to "请求地址错误",
            INVALID_PARAM to "请求参数错误",
            INVALID_METHOD to "请求方法错误",
            INVALID_TOKEN to "登录凭证无效",
            INVALID_ADMIN to "管理账号无效",
            LOGIN_FAILURE to "用户名或密码错误",
            PRODUCT_NOT_EXIST to "产品不存在",
            INVALID_ROLE to "无权限",
            EXIST_MANAGER to "该手机号已经注册为管理人员",
            OLD_PASSWORD_ERROR to "原密码错误",
            BOOK_ORDER_USER_ERROR to "用户校验失败",
            BOOK_ORDER_MONEY_ERROR to "金额校验失败",
            BOOK_ORDER_ADDRESS_ERROR to "地址校验失败",
            BOOK_ORDER_PRODUCT_ERROR to "产品校验失败",
            BOOK_ORDER_FAIL to "下单失败"
    )

    fun msg(code: Int): String {
        return map[code] ?: map[FAILURE] ?: ""
    }

}