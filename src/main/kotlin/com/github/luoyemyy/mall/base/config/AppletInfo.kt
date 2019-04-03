package com.github.luoyemyy.mall.base.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("wechat")
class AppletInfo {
    var appId: String? = null
    var secret: String? = null
}