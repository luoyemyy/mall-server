package com.github.luoyemyy.mall.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("alioss")
class AliOss {
    var accessKey: String? = null
    var secretKey: String? = null
    var bucket: String? = null
    var endPoint: String? = null
}