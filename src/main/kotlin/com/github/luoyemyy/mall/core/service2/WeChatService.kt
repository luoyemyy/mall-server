package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.base.config.AppletInfo
import com.github.luoyemyy.mall.core.bean.AccessToken
import com.github.luoyemyy.mall.core.bean.AppletLoginUser
import com.github.luoyemyy.mall.core.bean.CodeToSession
import com.github.luoyemyy.mall.core.dao.KeyValueDao
import com.github.luoyemyy.mall.core.entity.*
import com.github.luoyemyy.mall.core.mapper.KeyValueMapper
import com.github.luoyemyy.mall.core.mapper.UserMapper
import com.github.luoyemyy.mall.core.mapper.WeChatMapper
import com.github.luoyemyy.mall.core.mapper.WeChatUserMapper
import com.github.luoyemyy.mall.core.service.HttpService
import com.github.luoyemyy.mall.util.toObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class WeChatService {

    @Autowired
    private lateinit var appletInfo: AppletInfo
    @Autowired
    private lateinit var httpService: HttpService
    @Autowired
    private lateinit var keyValueMapper: KeyValueMapper
    @Autowired
    private lateinit var keyValueDao: KeyValueDao
    @Autowired
    private lateinit var weChatUserMapper: WeChatUserMapper
    @Autowired
    private lateinit var weChatMapper: WeChatMapper
    @Autowired
    private lateinit var userMapper: UserMapper

    private val KEY_ACCESS_TOKEN = "access_token"

    private fun getWeChatUserByWeChatId(weChatId: Long): WeChatUser? {
        return weChatUserMapper.selectByExample(WeChatUserExample().apply {
            createCriteria().andWeChatIdEqualTo(weChatId)
        })?.firstOrNull()
    }

    private fun getWeChatByOpenId(openId: String): WeChat? {
        return weChatMapper.selectByExample(WeChatExample().apply {
            createCriteria().andOpenIdEqualTo(openId)
        })?.firstOrNull()
    }

    private fun getUserById(userId: Long): User? {
        return userMapper.selectByPrimaryKey(userId)
    }

    @Transactional
    fun login(code: String): AppletLoginUser {
        val cs = codeToOpenId(code) ?: throw MallException(Code.INVALID_TOKEN)
        val openId = cs.openid
        val token = cs.session_key
        if (openId.isNullOrEmpty() || token.isNullOrEmpty()) {
            throw MallException(Code.INVALID_TOKEN)
        }
        val weChat = getWeChatByOpenId(openId) ?: let {
            WeChat().apply {
                this.openId = openId
                this.unionId = cs.unionid
            }
        }

        if (weChat.id == null || weChat.id == 0L) {
            if (weChatMapper.insert(weChat) <= 0) {
                throw MallException(Code.INVALID_TOKEN)
            }
            val user = User().apply {
                name = "weChat"
                gender = 0
                createTime = Date()
                updateTime = createTime
            }
            if (userMapper.insert(user) <= 0) {
                throw MallException(Code.INVALID_TOKEN)
            }
            val weChatUser = WeChatUser().apply {
                weChatId = weChat.id
                this.token = token
                userId = user.id
            }
            if (weChatUserMapper.insert(weChatUser) <= 0) {
                throw MallException(Code.INVALID_TOKEN)
            }
            return AppletLoginUser.fromUser(user, token)
        } else {
            val weChatUser = getWeChatUserByWeChatId(weChat.id) ?: let {
                WeChatUser().apply {
                    weChatId = weChat.id
                    this.token = token
                }
            }
            if (weChatUser.id == null || weChatUser.id == 0L) {
                val user = User().apply {
                    name = "weChat"
                    gender = 0
                    createTime = Date()
                    updateTime = createTime
                }
                if (userMapper.insert(user) <= 0) {
                    throw MallException(Code.INVALID_TOKEN)
                }
                weChatUser.userId = user.id
                if (weChatUserMapper.insert(weChatUser) <= 0) {
                    throw MallException(Code.INVALID_TOKEN)
                }
                return AppletLoginUser.fromUser(user, token)
            } else {
                val user = getUserById(weChatUser.userId) ?: throw MallException(Code.INVALID_TOKEN)
                weChatUser.token = token
                if (weChatUserMapper.updateByPrimaryKeySelective(weChatUser) <= 0) {
                    throw MallException(Code.INVALID_TOKEN)
                }
                return AppletLoginUser.fromUser(user, token)
            }
        }
    }

    /**
     * code to openId
     */
    private fun codeToOpenId(code: String): CodeToSession? {
        return httpService.get(
                "https://api.weixin.qq.com/sns/jscode2session?appid=${appletInfo.appId}&secret=${appletInfo.secret}&js_code=$code&grant_type=authorization_code"
        ).toObject<CodeToSession>()
    }

    private fun accessToken(): String? {
        val keyValue = keyValueDao.selectByKey(KEY_ACCESS_TOKEN)?.apply {
            if (this.expire != null && this.expire > System.currentTimeMillis()) {
                return value
            }
        }
        httpService.get(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credentialappid=${appletInfo.appId}&secret=${appletInfo.secret}"
        ).toObject<AccessToken>()?.apply {
            if (!access_token.isNullOrEmpty()) {
                if (keyValue != null) {
                    keyValue.value = access_token
                    keyValue.expire = System.currentTimeMillis() + expires_in - 100
                    keyValueMapper.updateByPrimaryKeySelective(keyValue)
                } else {
                    KeyValue().apply {
                        key = KEY_ACCESS_TOKEN
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