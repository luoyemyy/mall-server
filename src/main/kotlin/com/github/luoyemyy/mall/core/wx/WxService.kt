package com.github.luoyemyy.mall.core.wx

import com.github.luoyemyy.mall.common.properties.AppletInfo
import com.github.luoyemyy.mall.core.wx.bean.AccessToken
import com.github.luoyemyy.mall.core.wx.bean.CodeToSession
import com.github.luoyemyy.mall.core.dao.KeyValueDao
import com.github.luoyemyy.mall.core.entity.KeyValue
import com.github.luoyemyy.mall.core.mapper.KeyValueMapper
import com.github.luoyemyy.mall.util.Const
import com.github.luoyemyy.mall.util.toObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WxService {

    companion object {
        const val URL_GET_OPEN_ID = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code"
        const val URL_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credentialappid=%s&secret=%s"
    }

    @Autowired
    private lateinit var appletInfo: AppletInfo
    @Autowired
    private lateinit var keyValueMapper: KeyValueMapper
    @Autowired
    private lateinit var keyValueDao: KeyValueDao
    @Autowired
    private lateinit var httpService: HttpService

    /**
     * code to openId
     */
    fun codeToOpenId(code: String): CodeToSession? {
        return httpService.get(String.format(URL_GET_OPEN_ID, appletInfo.appId, appletInfo.secret, code)).toObject<CodeToSession>()
    }

    fun accessToken(): String? {
        val keyValue = keyValueDao.selectByKey(Const.ACCESS_TOKEN)?.apply {
            if (this.expire != null && this.expire > System.currentTimeMillis()) {
                return value
            }
        }
        httpService.get(String.format(URL_ACCESS_TOKEN, appletInfo.appId, appletInfo.secret)).toObject<AccessToken>()?.apply {
            if (!access_token.isNullOrEmpty()) {
                if (keyValue != null) {
                    keyValue.value = access_token
                    keyValue.expire = System.currentTimeMillis() + expires_in - 100
                    keyValueMapper.updateByPrimaryKeySelective(keyValue)
                } else {
                    KeyValue().apply {
                        key = Const.ACCESS_TOKEN
                        value = access_token
                        expire = System.currentTimeMillis() + expires_in - 100
                        keyValueMapper.insert(this)
                    }
                }
                return access_token
            }
        }
        return null
    }
}