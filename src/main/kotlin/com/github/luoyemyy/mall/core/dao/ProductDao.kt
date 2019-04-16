package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.bean.AppletCart
import com.github.luoyemyy.mall.core.bean.ProductBean
import com.github.luoyemyy.mall.core.bean.ProductDetail
import com.github.luoyemyy.mall.core.bean.SortBean
import com.github.luoyemyy.mall.core.entity.Product
import org.apache.ibatis.annotations.*
import org.apache.ibatis.type.JdbcType

interface ProductDao {


    @Select("""
        select p.*,pc.sort as pc_sort from product_category pc
        inner join product p on pc.product_id = p.id and p.status=1
        where pc.category_id = #{categoryId} order by pc.sort desc limit #{pageStart},10
    """)
    @Results(id = "productList", value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "cover_image", property = "coverImage", jdbcType = JdbcType.VARCHAR),
        Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
        Result(column = "market_price", property = "marketPrice", jdbcType = JdbcType.REAL),
        Result(column = "actual_price", property = "actualPrice", jdbcType = JdbcType.REAL),
        Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
        Result(column = "online", property = "online", jdbcType = JdbcType.BIT),
        Result(column = "pc_sort", property = "sort", jdbcType = JdbcType.INTEGER)])
    fun selectByCategoryAndPage(categoryId: Long, pageStart: Int): List<ProductBean>?

    @Select("""
        select p.*,pc.sort as pc_sort from product_category pc
        inner join product p on pc.product_id = p.id and p.online = true and p.status=1
        where pc.category_id = #{categoryId} order by pc.sort desc limit #{pageStart},10
    """)
    @ResultMap("productBean")
    fun selectOnlineByCategoryAndPage(categoryId: Long, pageStart: Int): List<ProductBean>?

    @Select("select p.* from product p where p.status=1 order by sort desc limit #{pageStart},10 ")
    @Results(id = "productBean", value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "cover_image", property = "coverImage", jdbcType = JdbcType.VARCHAR),
        Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
        Result(column = "market_price", property = "marketPrice", jdbcType = JdbcType.REAL),
        Result(column = "actual_price", property = "actualPrice", jdbcType = JdbcType.REAL),
        Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
        Result(column = "online", property = "online", jdbcType = JdbcType.BIT),
        Result(column = "sort", property = "sort", jdbcType = JdbcType.INTEGER)])
    fun selectAllByPage(pageStart: Int): List<ProductBean>?

    @Select("select p.* from product p where p.online = true and p.status=1 order by sort desc limit #{pageStart},10 ")
    @ResultMap("productBean")
    fun selectAllOnlineByPage(pageStart: Int): List<ProductBean>?

    @Select("select * from product where id = #{id} and status=1 ")
    @Results(id = "productDetail", value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "cover_image", property = "coverImage", jdbcType = JdbcType.VARCHAR),
        Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
        Result(column = "market_price", property = "marketPrice", jdbcType = JdbcType.REAL),
        Result(column = "actual_price", property = "actualPrice", jdbcType = JdbcType.REAL),
        Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
        Result(column = "online", property = "online", jdbcType = JdbcType.BIT),
        Result(column = "sort", property = "sort", jdbcType = JdbcType.INTEGER)])
    fun getDetail(id: Long): ProductDetail?

    @Select("""
        select p.* from hot_product hp
        inner join hot h on hp.hot_id = h.id
        inner join product p on hp.product_id = p.id and p.status=1
        where hp.hot_id = #{hotId}
    """)
    @ResultMap("productBean")
    fun selectByHot(hotId: Long): List<ProductBean>?

    @Select("select sort from product order by sort desc limit 1")
    fun currentSort(): Int?

    @Select("select sort from product_category where category_id = #{categoryId} order by sort desc limit 1")
    fun currentProductCategorySort(categoryId: Long): Int?

    @Select("""
        select p.id,sc.id as cart_id,p.name,p.cover_image,p.market_price,p.actual_price,sc.count from shop_cart sc
        inner join product p on sc.product_id = p.id and p.status=1
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
}