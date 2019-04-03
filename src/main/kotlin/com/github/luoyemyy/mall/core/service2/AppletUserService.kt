package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.core.entity.WeChatUser
import com.github.luoyemyy.mall.core.entity.WeChatUserExample
import com.github.luoyemyy.mall.core.mapper.WeChatUserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppletUserService {

    @Autowired
    private lateinit var weChatUserMapper: WeChatUserMapper

    /**
     * @applet
     */
    fun getWeChatUserByToken(token: String): WeChatUser? {
        return weChatUserMapper.selectByExample(WeChatUserExample().apply {
            createCriteria().andTokenEqualTo(token)
        })?.firstOrNull()
    }

}