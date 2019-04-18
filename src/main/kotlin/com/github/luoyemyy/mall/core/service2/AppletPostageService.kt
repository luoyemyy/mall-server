package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.core.bean.AppletPostage
import com.github.luoyemyy.mall.core.dao.KeyValueDao
import com.github.luoyemyy.mall.core.dao.PostageDao
import com.github.luoyemyy.mall.util.AppKey
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
        return keyValueDao.selectByKey(AppKey.FREE_POSTAGE)?.valueString?.toFloat() ?: 0f
    }

}