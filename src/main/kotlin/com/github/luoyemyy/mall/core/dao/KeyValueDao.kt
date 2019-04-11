package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.entity.KeyValue
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.type.JdbcType

interface KeyValueDao {

    @Select("select * from key_value where key=#{key} limit 1")
    @Results(id = "keyValue", value = [
        Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "key", property = "key", jdbcType = JdbcType.VARCHAR),
        Result(column = "value", property = "value", jdbcType = JdbcType.VARCHAR),
        Result(column = "expire", property = "expire", jdbcType = JdbcType.BIGINT)
    ])
    fun selectByKey(key: String): KeyValue?
}