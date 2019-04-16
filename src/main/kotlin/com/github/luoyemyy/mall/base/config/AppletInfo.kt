package com.github.luoyemyy.mall.base.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("wechat")
class AppletInfo {
    var appId: String? = null
    var secret: String? = null
    var mchId: String? = null
    var mchKey: String? = null
    var spbillCreateIp: String? = null
    var notifyUrl: String? = null
    var tradeType: String? = null
    var body: String? = null
    var payMock: Boolean = false
}