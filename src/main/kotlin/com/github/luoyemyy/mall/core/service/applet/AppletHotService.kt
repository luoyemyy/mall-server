package com.github.luoyemyy.mall.core.service.applet

import com.github.luoyemyy.mall.core.service.applet.bean.AppletHotBean
import com.github.luoyemyy.mall.core.entity.HotExample
import com.github.luoyemyy.mall.core.entity.HotProductExample
import com.github.luoyemyy.mall.core.mapper.HotMapper
import com.github.luoyemyy.mall.core.mapper.HotProductMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppletHotService {

    @Autowired
    private lateinit var hotMapper: HotMapper
    @Autowired
    private lateinit var hotProductMapper: HotProductMapper

    /**
     * @applet
     */
    fun list(): List<AppletHotBean> {
        return hotMapper.selectByExample(HotExample().apply {
            createCriteria().andStateEqualTo(1)
            orderByClause = "sort desc"
        })?.map { AppletHotBean.map(it, hotProducts(it.id)) } ?: listOf()
    }

    private fun hotProducts(hotId: Long): List<Long>? {
        return hotProductMapper.selectByExample(HotProductExample().apply {
            createCriteria().andHotIdEqualTo(hotId)
        })?.map { it.productId }
    }
}