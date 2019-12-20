package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.service.applet.bean.AppletAddress
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.type.JdbcType

interface AddressDao {

    @Select("""
        select ua.type,a.* from user_address ua
        inner join address a on ua.address_id = a.id
        where ua.user_id = #{userId}
    """)
    @Results(id = "address", value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
        Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
        Result(column = "post_code", property = "postCode", jdbcType = JdbcType.VARCHAR),
        Result(column = "country", property = "country", jdbcType = JdbcType.VARCHAR),
        Result(column = "province", property = "province", jdbcType = JdbcType.VARCHAR),
        Result(column = "city", property = "city", jdbcType = JdbcType.VARCHAR),
        Result(column = "county", property = "county", jdbcType = JdbcType.VARCHAR),
        Result(column = "street", property = "street", jdbcType = JdbcType.VARCHAR),
        Result(column = "summary", property = "summary", jdbcType = JdbcType.VARCHAR),
        Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER)])
    fun selectAddressByUser(userId: Long): List<AppletAddress>?

    @Select("""
        select ua.type,a.* from user_address ua
        inner join address a on ua.address_id = a.id
        where ua.user_id = #{userId} and ua.type=1 limit 1
    """)
    @ResultMap("address")
    fun selectDefaultAddressByUser(userId: Long): AppletAddress?
}