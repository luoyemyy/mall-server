package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.bean.AppletBookOrderProduct
import com.github.luoyemyy.mall.core.bean.PostageBean
import com.github.luoyemyy.mall.core.bean.SortBean
import com.github.luoyemyy.mall.core.entity.ProductCategory
import com.github.luoyemyy.mall.core.entity.ProductImage
import com.github.luoyemyy.mall.core.entity.ShopCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class BatchDao {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    fun updateCategorySort(list: List<SortBean>): Boolean {
        if (list.isEmpty()) return true
        val sql = list.map {
            "update category set sort = ${it.sort} where id = ${it.id} "
        }.toTypedArray()
        jdbcTemplate.batchUpdate(*sql)
        return true
    }

    fun insertProductCategory(list: List<ProductCategory>) {
        if (list.isEmpty()) return
        val sql = list.map {
            "insert into product_category (product_id, category_id, sort) values (${it.productId},${it.categoryId},${it.sort})"
        }.toTypedArray()
        jdbcTemplate.batchUpdate(*sql)
    }

    fun updateProductCategorySort(list: List<ProductCategory>) {
        if (list.isEmpty()) return
        val sql = list.map {
            "update product_category set sort = ${it.sort} where product_id = ${it.productId} and category_id = ${it.categoryId}"
        }.toTypedArray()
        jdbcTemplate.batchUpdate(*sql)
    }

    fun insertProductImage(list: List<ProductImage>) {
        if (list.isEmpty()) return
        val sql = list.map {
            """
                insert into product_image (product_id, image, type, sort) values (${it.productId},"${it.image}",${it.type},${it.sort})
            """
        }.toTypedArray()
        jdbcTemplate.batchUpdate(*sql)
    }

    fun updateProductImageSort(list: List<SortBean>) {
        if (list.isEmpty()) return
        val sql = list.map {
            "update product_image set sort = ${it.sort} where id = ${it.id}"
        }.toTypedArray()
        jdbcTemplate.batchUpdate(*sql)
    }

    fun updateProductSort(list: List<SortBean>) {
        if (list.isEmpty()) return
        val sql = list.map {
            "update product set sort = ${it.sort} where id = ${it.id}"
        }.toTypedArray()
        jdbcTemplate.batchUpdate(*sql)
    }

    fun updateHotSort(list: List<SortBean>): Boolean {
        if (list.isEmpty()) return true
        val sql = list.map {
            "update hot set sort = ${it.sort} where id = ${it.id}"
        }.toTypedArray()
        jdbcTemplate.batchUpdate(*sql)
        return true
    }

    fun insertShopCart(list: List<ShopCart>) {
        if (list.isEmpty()) return
        val sql = list.map {
            "insert into shop_cart (user_id, product_id, count) values (${it.userId},${it.productId},${it.count})"
        }.toTypedArray()
        jdbcTemplate.batchUpdate(*sql)
    }

    fun insertOrderProduct(orderId: Long, list: List<AppletBookOrderProduct>?): Boolean {
        if (list.isNullOrEmpty()) return false
        val sql = list.map {
            "insert into order_product (order_id, product_id, count, price) values ($orderId,${it.productId},${it.count},${it.price})"
        }.toTypedArray()
        return jdbcTemplate.batchUpdate(*sql).let { r ->
            r.size == list.size && r.none { it == 0 }
        }
    }

    fun updatePostage(list: List<PostageBean>?): Boolean {
        if (list.isNullOrEmpty()) return false
        val sql = list.map {
            "update postage set price = ${it.price},post = ${it.post} where id = ${it.id}"
        }.toTypedArray()
        return jdbcTemplate.batchUpdate(*sql).let { r ->
            r.size == list.size && r.none { it == 0 }
        }
    }


}