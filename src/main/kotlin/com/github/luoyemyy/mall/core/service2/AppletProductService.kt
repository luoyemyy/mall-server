package com.github.luoyemyy.mall.core.service2

import com.github.luoyemyy.mall.base.advice.Code
import com.github.luoyemyy.mall.base.advice.MallException
import com.github.luoyemyy.mall.core.bean.ProductBean
import com.github.luoyemyy.mall.core.bean.ProductDetail
import com.github.luoyemyy.mall.core.bean.ProductImageBean
import com.github.luoyemyy.mall.core.dao.ProductDao
import com.github.luoyemyy.mall.core.entity.ProductImageExample
import com.github.luoyemyy.mall.core.mapper.ProductImageMapper
import com.github.luoyemyy.mall.util.toPageStart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppletProductService {

    @Autowired
    private lateinit var productDao: ProductDao
    @Autowired
    private lateinit var productImageMapper: ProductImageMapper

    /**
     * @applet
     */
    fun getByHot(hotId: Long): List<ProductBean> {
        return productDao.selectOnlineByHot(hotId) ?: listOf()
    }

    /**
     * @applet
     */
    fun getByCategoryAndPage(categoryId: Long, page: Int): List<ProductBean> {
        return if (categoryId == 0L) {
            productDao.selectOnlineByPage(page.toPageStart()) ?: listOf()
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
        return productDao.getOnline(id)?.apply {
            swipeImages = getImages(id, 1)
            descImages = getImages(id, 2)
        } ?: throw MallException(Code.PRODUCT_NOT_EXIST)
    }
}