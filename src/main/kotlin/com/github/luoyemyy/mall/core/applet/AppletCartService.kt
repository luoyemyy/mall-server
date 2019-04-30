package com.github.luoyemyy.mall.core.applet

import com.github.luoyemyy.mall.core.applet.bean.AppletCart
import com.github.luoyemyy.mall.core.dao.BatchDao
import com.github.luoyemyy.mall.core.dao.ProductDao
import com.github.luoyemyy.mall.core.entity.ShopCart
import com.github.luoyemyy.mall.core.entity.ShopCartExample
import com.github.luoyemyy.mall.core.mapper.ShopCartMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AppletCartService {

    @Autowired
    private lateinit var shopCartMapper: ShopCartMapper
    @Autowired
    private lateinit var productDao: ProductDao
    @Autowired
    private lateinit var batchDao: BatchDao

    /**
     * @applet
     */
    fun list(userId: Long): List<AppletCart> {
        return productDao.selectCartProducts(userId) ?: listOf()
    }

    /**
     * @applet
     */
    @Transactional
    fun save(userId: Long, carts: List<AppletCart>): Boolean {
        shopCartMapper.deleteByExample(ShopCartExample().apply {
            createCriteria().andUserIdEqualTo(userId)
        })
        carts.map {
            ShopCart().apply {
                this.userId = userId
                this.productId = it.id
                this.count = it.count
            }
        }.apply {
            if (this.isNotEmpty()) {
                batchDao.insertShopCart(this)
            }
        }
        return true
    }


}