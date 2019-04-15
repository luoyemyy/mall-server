package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.entity.WeChat
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.type.JdbcType

interface WeChatDao {

    @Select("""
       select wc.* from we_chat_user wcu
       inner join we_chat wc on wcu.we_chat_id = wc.id
       where wcu.user_id = #{userId} limit 1
    """)
    @Results(
            Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "union_id", property = "unionId", jdbcType = JdbcType.VARCHAR),
            Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR))
    fun selectByUser(userId:Long): WeChat?
}
