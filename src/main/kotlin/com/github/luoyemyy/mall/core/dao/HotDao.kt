package com.github.luoyemyy.mall.core.dao

import org.apache.ibatis.annotations.Select

interface HotDao {

    @Select("select sort from hot order by sort desc limit 1")
    fun currentSort(): Int?
}