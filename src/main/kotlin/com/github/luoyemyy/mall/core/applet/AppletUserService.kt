package com.github.luoyemyy.mall.core.applet

import com.github.luoyemyy.mall.common.advice.AppCode
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.core.applet.bean.AppletLoginUser
import com.github.luoyemyy.mall.core.entity.*
import com.github.luoyemyy.mall.core.mapper.UserMapper
import com.github.luoyemyy.mall.core.mapper.WeChatMapper
import com.github.luoyemyy.mall.core.mapper.WeChatUserMapper
import com.github.luoyemyy.mall.core.wx.WxService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AppletUserService {

    @Autowired
    private lateinit var wxService: WxService
    @Autowired
    private lateinit var weChatUserMapper: WeChatUserMapper
    @Autowired
    private lateinit var weChatMapper: WeChatMapper
    @Autowired
    private lateinit var userMapper: UserMapper

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
        val cs = wxService.codeToOpenId(code) ?: throw AppException(AppCode.INVALID_TOKEN)
        val openId = cs.openid
        val token = cs.session_key
        if (openId.isNullOrEmpty() || token.isNullOrEmpty()) {
            throw AppException(AppCode.INVALID_TOKEN)
        }
        val weChat = getWeChatByOpenId(openId) ?: let {
            WeChat().apply {
                this.openId = openId
                this.unionId = cs.unionid
            }
        }

        if (weChat.id == null || weChat.id == 0L) {
            if (weChatMapper.insert(weChat) <= 0) {
                throw AppException(AppCode.INVALID_TOKEN)
            }
            val user = User().apply {
                name = "weChat"
                gender = 0
                createTime = Date()
                updateTime = createTime
            }
            if (userMapper.insert(user) <= 0) {
                throw AppException(AppCode.INVALID_TOKEN)
            }
            val weChatUser = WeChatUser().apply {
                weChatId = weChat.id
                this.token = token
                userId = user.id
            }
            if (weChatUserMapper.insert(weChatUser) <= 0) {
                throw AppException(AppCode.INVALID_TOKEN)
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
                    throw AppException(AppCode.INVALID_TOKEN)
                }
                weChatUser.userId = user.id
                if (weChatUserMapper.insert(weChatUser) <= 0) {
                    throw AppException(AppCode.INVALID_TOKEN)
                }
                return AppletLoginUser.fromUser(user, token)
            } else {
                val user = getUserById(weChatUser.userId) ?: throw AppException(AppCode.INVALID_TOKEN)
                weChatUser.token = token
                if (weChatUserMapper.updateByPrimaryKeySelective(weChatUser) <= 0) {
                    throw AppException(AppCode.INVALID_TOKEN)
                }
                return AppletLoginUser.fromUser(user, token)
            }
        }
    }

    /**
     * @applet
     */
    fun getWeChatUserByToken(token: String): WeChatUser? {
        return weChatUserMapper.selectByExample(WeChatUserExample().apply {
            createCriteria().andTokenEqualTo(token)
        })?.firstOrNull()
    }

}