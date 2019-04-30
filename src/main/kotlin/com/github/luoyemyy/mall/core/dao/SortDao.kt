package com.github.luoyemyy.mall.core.dao

import org.apache.ibatis.annotations.Select

interface SortDao {

    @Select("select sort from hot order by sort desc limit 1")
    fun hot(): Int?

    @Select("select sort from category order by sort desc limit 1")
    fun category(): Int?

    @Select("select sort from product order by sort desc limit 1")
    fun product(): Int?

    @Select("select sort from product_category where category_id = #{categoryId} order by sort desc limit 1")
    fun productCategory(categoryId: Long): Int?
}