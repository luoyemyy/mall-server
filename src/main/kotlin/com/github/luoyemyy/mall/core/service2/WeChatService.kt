package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.base.config.AppletInfo
import com.github.luoyemyy.mall.core.bean.AccessToken
import com.github.luoyemyy.mall.core.bean.CodeToSession
import com.github.luoyemyy.mall.core.service.HttpService
import com.github.luoyemyy.mall.util.toObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WeChatService {

    @Autowired
    private lateinit var appletInfo: AppletInfo

    @Autowired
    private lateinit var httpService: HttpService

    /**
     * code to openId
     */
    fun codeToOpenId(code: String): CodeToSession? {
        val value = httpService.get("""
                "https://api.weixin.qq.com/sns/jscode2session?appid=${appletInfo.appId}&secret=${appletInfo.secret}&js_code=$code&grant_type=authorization_code
        """)
        return value.toObject<CodeToSession>()
    }

    fun accessToken(): String {
        val value = httpService.get("""
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credentialappid=${appletInfo.appId}&secret=${appletInfo.secret}
        """)
        return value.toObject<AccessToken>()?.access_token ?: ""
    }
}