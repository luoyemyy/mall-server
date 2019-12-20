package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.service.applet.bean.AppletPostage
import com.github.luoyemyy.mall.core.service.admin.bean.PostageBean
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

    @Select("""
        select p.* from postage p where exists (select 1 from address a where a.province like concat('%',p.area,'%') and a.id = #{addressId});
    """)
    @Results(Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "area", property = "area", jdbcType = JdbcType.VARCHAR),
            Result(column = "price", property = "price", jdbcType = JdbcType.REAL),
            Result(column = "post", property = "post", jdbcType = JdbcType.INTEGER))
    fun match(addressId: Long): AppletPostage?
}