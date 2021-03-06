package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.service.admin.bean.OrderProduct
import com.github.luoyemyy.mall.core.service.admin.bean.ProductBean
import com.github.luoyemyy.mall.core.service.admin.bean.ProductDetail
import com.github.luoyemyy.mall.core.service.applet.bean.AppletCart
import com.github.luoyemyy.mall.core.service.applet.bean.AppletOrderProduct
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.type.JdbcType

interface ProductDao {


    @Select("""
        select p.*,pc.sort as pc_sort from product_category pc
        inner join product p on pc.product_id = p.id and p.status=1
        where pc.category_id = #{categoryId} order by pc.sort desc limit #{pageStart},10
    """)
    @Results(id = "productBean",
            value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
                Result(column = "cover_image", property = "coverImage", jdbcType = JdbcType.VARCHAR),
                Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
                Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
                Result(column = "market_price", property = "marketPrice", jdbcType = JdbcType.REAL),
                Result(column = "actual_price", property = "actualPrice", jdbcType = JdbcType.REAL),
                Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
                Result(column = "online", property = "online", jdbcType = JdbcType.BIT),
                Result(column = "pc_sort", property = "sort", jdbcType = JdbcType.INTEGER)])
    fun selectByCategoryAndPage(categoryId: Long, pageStart: Int): List<ProductBean>?

    @Select("select p.* from product p where p.status=1 order by p.sort desc limit #{pageStart},10 ")
    @ResultMap("productBean")
    fun selectByPage(pageStart: Int): List<ProductBean>?

    @Select("select * from product where id = #{id} and status=1 ")
    @Results(id = "productDetail",
            value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
                Result(column = "cover_image", property = "coverImage", jdbcType = JdbcType.VARCHAR),
                Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
                Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
                Result(column = "market_price", property = "marketPrice", jdbcType = JdbcType.REAL),
                Result(column = "actual_price", property = "actualPrice", jdbcType = JdbcType.REAL),
                Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
                Result(column = "online", property = "online", jdbcType = JdbcType.BIT),
                Result(column = "sort", property = "sort", jdbcType = JdbcType.INTEGER)])
    fun get(id: Long): ProductDetail?

    @Select("""
        select p.* from hot_product hp
        inner join hot h on hp.hot_id = h.id
        inner join product p on hp.product_id = p.id and p.status=1
        where hp.hot_id = #{hotId}
    """)
    @ResultMap("productBean")
    fun selectByHot(hotId: Long): List<ProductBean>?


    @Select("select * from product where id = #{id} and online = true and status=1 ")
    @ResultMap("productDetail")
    fun getOnline(id: Long): ProductDetail?


    @Select("""
        select p.* from hot_product hp
        inner join hot h on hp.hot_id = h.id
        inner join product p on hp.product_id = p.id and p.online = true and p.status=1
        where hp.hot_id = #{hotId}
    """)
    @ResultMap("productBean")
    fun selectOnlineByHot(hotId: Long): List<ProductBean>?

    @Select("""
        select p.*,pc.sort as pc_sort from product_category pc
        inner join product p on pc.product_id = p.id and p.online = true and p.status=1
        where pc.category_id = #{categoryId} order by pc.sort desc limit #{pageStart},10
    """)
    @ResultMap("productBean")
    fun selectOnlineByCategoryAndPage(categoryId: Long, pageStart: Int): List<ProductBean>?

    @Select("select p.* from product p where p.online = true and p.status=1 order by sort desc limit #{pageStart},10 ")
    @ResultMap("productBean")
    fun selectOnlineByPage(pageStart: Int): List<ProductBean>?

    @Select("""
        select p.id,sc.id as cart_id,p.name,p.cover_image,p.market_price,p.actual_price,sc.count from shop_cart sc
        inner join product p on sc.product_id = p.id and p.status=1 and p.online = true
        where sc.user_id=#{userId} and sc.count>0
    """)
    @Results(value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "cart_id", property = "cartId", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "cover_image", property = "coverImage", jdbcType = JdbcType.VARCHAR),
        Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        Result(column = "market_price", property = "marketPrice", jdbcType = JdbcType.REAL),
        Result(column = "actual_price", property = "actualPrice", jdbcType = JdbcType.REAL),
        Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER)])
    fun selectCartProducts(userId: Long): List<AppletCart>?

    @Select("""
        select p.id,p.name,p.cover_image,op.price,op.count from order_product op
        inner join product p on op.product_id = p.id
        where op.order_id=#{orderId}
    """)
    @Results(value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "cover_image", property = "coverImage", jdbcType = JdbcType.VARCHAR),
        Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        Result(column = "price", property = "price", jdbcType = JdbcType.REAL),
        Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER)])
    fun selectAppletOrderProducts(orderId: Long): List<AppletOrderProduct>?

    @Select("""
        select p.id,p.name,p.cover_image,op.price,op.count from order_product op
        inner join product p on op.product_id = p.id
        where op.order_id=#{orderId}
    """)
    @Results(value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "cover_image", property = "coverImage", jdbcType = JdbcType.VARCHAR),
        Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        Result(column = "price", property = "price", jdbcType = JdbcType.REAL),
        Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER)])
    fun selectOrderProducts(orderId: Long): List<OrderProduct>?
}