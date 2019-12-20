package com.github.luoyemyy.mall.core.service.admin

import com.github.luoyemyy.mall.common.advice.AppCode
import com.github.luoyemyy.mall.common.advice.AppException
import com.github.luoyemyy.mall.core.service.admin.bean.HotBean
import com.github.luoyemyy.mall.core.service.admin.bean.SortBean
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.SortDao
import com.github.luoyemyy.mall.core.entity.Hot
import com.github.luoyemyy.mall.core.entity.HotExample
import com.github.luoyemyy.mall.core.entity.HotProduct
import com.github.luoyemyy.mall.core.entity.HotProductExample
import com.github.luoyemyy.mall.core.mapper.HotMapper
import com.github.luoyemyy.mall.core.mapper.HotProductMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HotService {

    @Autowired
    private lateinit var hotMapper: HotMapper
    @Autowired
    private lateinit var batchDao: BatchDao
    @Autowired
    private lateinit var hotProductMapper: HotProductMapper
    @Autowired
    private lateinit var sortDao: SortDao

    /**
     * @admin
     */
    fun list(): List<HotBean> {
        return hotMapper.selectByExample(HotExample().apply {
            orderByClause = "sort desc"
        })?.map { HotBean.mapHot(it, countProductByHot(it.id)) } ?: listOf()
    }

    private fun countProductByHot(hotId: Long): Int {
        return hotProductMapper.countByExample(HotProductExample().apply {
            createCriteria().andHotIdEqualTo(hotId)
        })
    }

    /**
     * @admin
     */
    private fun nextSort(): Int {
        return (sortDao.hot() ?: 0) + 1
    }

    /**
     * @admin
     */
    @Transactional
    fun add(image: String, description: String): Boolean {
        val hot = Hot().also {
            it.image = image
            it.description = description
            it.state = 0
            it.sort = nextSort()
        }
        if (hotMapper.insert(hot) > 0) {
            return true
        }
        throw AppException(AppCode.FAIL)
    }

    /**
     * @admin
     */
    @Transactional
    fun addProduct(id: Long, productIds: List<Long>): Boolean {
        productIds.forEach {
            hotProductMapper.insert(HotProduct().also { hp ->
                hp.hotId = id
                hp.productId = it
            })
        }
        return true
    }


    /**
     * @admin
     */
    @Transactional
    fun deleteProduct(id: Long, productId: Long): Boolean {
        return hotProductMapper.deleteByExample(HotProductExample().apply {
            createCriteria().andHotIdEqualTo(id).andProductIdEqualTo(productId)
        }) > 0
    }


    /**
     * @admin
     */
    @Transactional
    fun edit(id: Long, image: String?, description: String?): Boolean {
        return hotMapper.updateByPrimaryKeySelective(Hot().also {
            it.id = id
            it.image = image
            it.description = description
        }) > 0
    }

    /**
     * @admin
     */
    @Transactional
    fun state(id: Long, state: Int): Boolean {
        return hotMapper.updateByPrimaryKeySelective(Hot().also {
            it.id = id
            it.state = state
        }) > 0
    }

    /**
     * @admin
     */
    @Transactional
    fun delete(id: Long): Boolean {
        return if (hotMapper.deleteByPrimaryKey(id) > 0) {
            hotProductMapper.deleteByExample(HotProductExample().apply {
                createCriteria().andHotIdEqualTo(id)
            })
            true
        } else false
    }

    /**
     * @admin
     */
    @Transactional
    fun sort(sort: List<SortBean>): Boolean {
        return batchDao.updateHotSort(sort)
    }
}