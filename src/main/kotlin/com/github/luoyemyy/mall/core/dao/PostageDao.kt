package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.bean.PostageBean
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.type.JdbcType

interface PostageDao {

    @Select("select id, area, price, post from postage")
    @Results(Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "area", property = "area", jdbcType = JdbcType.VARCHAR),
            Result(column = "price", property = "price", jdbcType = JdbcType.REAL),
            Result(column = "post", property = "post", jdbcType = JdbcType.INTEGER))
    fun selectAll(): List<PostageBean>?

    @Select("select id, area, price, post from postage where post=1")
    @Results(Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "area", property = "area", jdbcType = JdbcType.VARCHAR),
            Result(column = "price", property = "price", jdbcType = JdbcType.REAL),
            Result(column = "post", property = "post", jdbcType = JdbcType.INTEGER))
    fun selectValid(): List<PostageBean>?
}