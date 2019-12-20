package com.github.luoyemyy.mall.common.advice


enum class AppCode(val code: Int, val msg: String) {

    OK(0, "请求成功"),
    FAIL(1, "服务器错误"),

    INVALID_URL(101, "请求地址错误"),
    INVALID_PARAM(102, "请求参数错误"),
    INVALID_METHOD(103, "请求方法错误"),
    INVALID_TOKEN(104, "登录凭证无效"),
    INVALID_ADMIN(105, "管理账号无效"),
    LOGIN_FAILURE(106, "用户名或密码错误"),
    OLD_PASSWORD_ERROR(107, "原密码错误"),
    EXIST_MANAGER(108, "该手机号已经注册为管理人员"),
    INVALID_ROLE(109, "无权限"),
    PRODUCT_NOT_EXIST(201, "产品不存在"),
    BOOK_ORDER_USER_ERROR(202, "用户校验失败"),
    BOOK_ORDER_MONEY_ERROR(203, "金额校验失败"),
    BOOK_ORDER_ADDRESS_ERROR(204, "地址校验失败"),
    BOOK_ORDER_PRODUCT_ERROR(205, "产品校验失败"),
    BOOK_ORDER_FAIL(206, "下单失败"),
    ORDER_NOT_EXIST(207, "订单不存在"),
    ORDER_CANCELED(208, "订单已取消");
}