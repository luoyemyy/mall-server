package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.core.bean.*
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.ProductDao
import com.github.luoyemyy.mall.core.entity.*
import com.github.luoyemyy.mall.core.mapper.*
import com.github.luoyemyy.mall.util.diff
import com.github.luoyemyy.mall.util.minus
import com.github.luoyemyy.mall.util.toPageStart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AppletProductService {

    @Autowired
    private lateinit var productDao: ProductDao
    @Autowired
    private lateinit var productMapper: ProductMapper
    @Autowired
    private lateinit var productImageMapper: ProductImageMapper
    @Autowired
    private lateinit var productCategoryMapper: ProductCategoryMapper
    @Autowired
    private lateinit var batchDao: BatchDao

    /**
     * @applet
     */
    fun getByHot(hotId: Long): List<ProductBean> {
        return productDao.selectByHot(hotId) ?: listOf()
    }

    /**
     * @applet
     */
    fun getByCategoryAndPage(categoryId: Long, page: Int): List<ProductBean> {
        return if (categoryId == 0L) {
            productDao.selectAllOnlineByPage(page.toPageStart()) ?: listOf()
        } else {
            productDao.selectOnlineByCategoryAndPage(categoryId, page.toPageStart()) ?: listOf()
        }
    }

    /**
     * @applet
     * @param type 1 滑动展示图 2 描述图
     */
    private fun getImages(id: Long, type: Int): List<ProductImageBean>? {
        return productImageMapper.selectByExample(ProductImageExample().apply {
            createCriteria().andProductIdEqualTo(id).andTypeEqualTo(type)
            orderByClause = "sort asc"
        })?.map { ProductImageBean.fromProductImage(it) }
    }

    /**
     * @applet
     */
    fun getDetail(id: Long): ProductDetail {
        return productDao.getDetail(id)?.apply {
            swipeImages = getImages(id, 1)
            descImages = getImages(id, 2)
        } ?: throw MallException(Code.PRODUCT_NOT_EXIST)
    }
}