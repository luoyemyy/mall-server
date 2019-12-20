package com.github.luoyemyy.mall.core.service.wx.bean

open class WeChatApiBean {
    var errcode: Int = 0
    var errmsg: String? = null
}

class CodeToSession : WeChatApiBean() {
    var unionid: String? = null
    var openid: String? = null
    var session_key: String? = null
}

class AccessToken : WeChatApiBean() {
    var access_token: String? = null
    var expires_in: Int = 0
}