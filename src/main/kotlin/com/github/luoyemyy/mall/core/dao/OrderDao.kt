package com.github.luoyemyy.mall.core.dao

import com.github.luoyemyy.mall.core.bean.AppletOrderInfo
import com.github.luoyemyy.mall.core.bean.AppletOrderItem
import com.github.luoyemyy.mall.core.bean.AppletOrderProduct
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.type.JdbcType

interface OrderDao {

    @Select("""
        <script>
        select o.id,o.state,o.money from `order` o where o.user_id=#{userId} and o.status=1 and o.state in
        <foreach item="item" index="index" collection="state" open="(" separator="," close=")">
        #{item}
        </foreach>
        order by o.create_time desc limit #{page},10
        </script>
    """)
    @Results(value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "state", property = "state", jdbcType = JdbcType.INTEGER),
        Result(column = "money", property = "money", jdbcType = JdbcType.REAL)])
    fun selectOrderItemByStatePage(@Param("userId")userId: Long,@Param("state") state: List<Int>,@Param("page") page: Int): List<AppletOrderItem>?

    @Select("""
        select o.id,o.state,o.money from `order` o where o.user_id=#{userId} and o.status=1 order by o.create_time desc limit #{page},10
    """)
    @Results(value = [Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        Result(column = "state", property = "state", jdbcType = JdbcType.INTEGER),
        Result(column = "money", property = "money", jdbcType = JdbcType.REAL)])
    fun selectOrderItemByPage(userId: Long, page: Int): List<AppletOrderItem>?

    @Select("""
        select * from `order` where id = #{orderId} and user_id=#{userId} and status=1
    """)
    @Results(Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "order_no", property = "orderNo", jdbcType = JdbcType.VARCHAR),
            Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
            Result(column = "state", property = "state", jdbcType = JdbcType.INTEGER),
            Result(column = "money", property = "money", jdbcType = JdbcType.REAL),
            Result(column = "postage", property = "postage", jdbcType = JdbcType.REAL),
            Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
            Result(column = "postcode", property = "postcode", jdbcType = JdbcType.VARCHAR),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "wx_pay_id", property = "wxPayId", jdbcType = JdbcType.VARCHAR),
            Result(column = "wx_order_id", property = "wxOrderId", jdbcType = JdbcType.VARCHAR))
    fun selectOrderInfo(userId: Long, orderId: Long): AppletOrderInfo?


}