package com.github.luoyemyy.mall.core.service.applet

import com.github.luoyemyy.mall.core.service.applet.bean.AppletPostage
import com.github.luoyemyy.mall.core.dao.KeyValueDao
import com.github.luoyemyy.mall.core.dao.PostageDao
import com.github.luoyemyy.mall.util.Const
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppletPostageService {

    @Autowired
    private lateinit var keyValueDao: KeyValueDao
    @Autowired
    private lateinit var postageDao: PostageDao

    /**
     * @applet
     */
    fun match(addressId: Long): AppletPostage? {
        return postageDao.match(addressId)
    }

    /**
     * @applet
     */
    fun free(): Float {
        return keyValueDao.selectByKey(Const.FREE_POSTAGE)?.value?.toFloat() ?: 0f
    }

}