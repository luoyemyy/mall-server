package com.github.luoyemyy.mall.core.dao

import org.apache.ibatis.annotations.Select

interface CategoryDao {

    @Select("select sort from category order by sort desc limit 1")
    fun currentSort(): Int?
}