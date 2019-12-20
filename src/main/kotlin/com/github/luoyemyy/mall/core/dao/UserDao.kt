package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.service.admin.bean.Manager
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.type.JdbcType

interface UserDao {


    @Select("""
        select u.id,u.head_image,u.name,u.phone,u.gender,au.role,au.state from user u
        inner join admin_user au on au.user_id = u.id and au.role = #{roleId}
        limit #{pageStart},10
    """)
    @Results(Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "head_image", property = "headImage", jdbcType = JdbcType.VARCHAR),
            Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            Result(column = "gender", property = "gender", jdbcType = JdbcType.INTEGER),
            Result(column = "state", property = "state", jdbcType = JdbcType.INTEGER),
            Result(column = "role", property = "roleId", jdbcType = JdbcType.INTEGER))
    fun selectByPage(roleId: Int, pageStart: Int): List<Manager>?
}
