package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.Order;
import com.github.luoyemyy.mall.core.entity.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface OrderMapper {
    @SelectProvider(type=OrderSqlProvider.class, method="countByExample")
    int countByExample(OrderExample example);

    @DeleteProvider(type=OrderSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrderExample example);

    @Delete({
        "delete from `order`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into `order` (order_no, user_id, ",
        "`state`, money, postage, ",
        "product_count, username, ",
        "phone, address, ",
        "postcode, create_time, ",
        "update_time, wx_pay_id, ",
        "wx_order_id, express_company, ",
        "express_no, cancel_reason, ",
        "refuse_order_no, refuse_wx_no, ",
        "`status`)",
        "values (#{orderNo,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{state,jdbcType=INTEGER}, #{money,jdbcType=REAL}, #{postage,jdbcType=REAL}, ",
        "#{productCount,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{postcode,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{wxPayId,jdbcType=VARCHAR}, ",
        "#{wxOrderId,jdbcType=VARCHAR}, #{expressCompany,jdbcType=VARCHAR}, ",
        "#{expressNo,jdbcType=VARCHAR}, #{cancelReason,jdbcType=VARCHAR}, ",
        "#{refuseOrderNo,jdbcType=VARCHAR}, #{refuseWxNo,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Order record);

    @InsertProvider(type=OrderSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Order record);

    @SelectProvider(type=OrderSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="money", property="money", jdbcType=JdbcType.REAL),
        @Result(column="postage", property="postage", jdbcType=JdbcType.REAL),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="postcode", property="postcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="wx_pay_id", property="wxPayId", jdbcType=JdbcType.VARCHAR),
        @Result(column="wx_order_id", property="wxOrderId", jdbcType=JdbcType.VARCHAR),
        @Result(column="express_company", property="expressCompany", jdbcType=JdbcType.VARCHAR),
        @Result(column="express_no", property="expressNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="cancel_reason", property="cancelReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="refuse_order_no", property="refuseOrderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="refuse_wx_no", property="refuseWxNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Order> selectByExample(OrderExample example);

    @Select({
        "select",
        "id, order_no, user_id, `state`, money, postage, product_count, username, phone, ",
        "address, postcode, create_time, update_time, wx_pay_id, wx_order_id, express_company, ",
        "express_no, cancel_reason, refuse_order_no, refuse_wx_no, `status`",
        "from `order`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_no", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="money", property="money", jdbcType=JdbcType.REAL),
        @Result(column="postage", property="postage", jdbcType=JdbcType.REAL),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="postcode", property="postcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="wx_pay_id", property="wxPayId", jdbcType=JdbcType.VARCHAR),
        @Result(column="wx_order_id", property="wxOrderId", jdbcType=JdbcType.VARCHAR),
        @Result(column="express_company", property="expressCompany", jdbcType=JdbcType.VARCHAR),
        @Result(column="express_no", property="expressNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="cancel_reason", property="cancelReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="refuse_order_no", property="refuseOrderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="refuse_wx_no", property="refuseWxNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    Order selectByPrimaryKey(Long id);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Order record);

    @Update({
        "update `order`",
        "set order_no = #{orderNo,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "`state` = #{state,jdbcType=INTEGER},",
          "money = #{money,jdbcType=REAL},",
          "postage = #{postage,jdbcType=REAL},",
          "product_count = #{productCount,jdbcType=INTEGER},",
          "username = #{username,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "postcode = #{postcode,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "wx_pay_id = #{wxPayId,jdbcType=VARCHAR},",
          "wx_order_id = #{wxOrderId,jdbcType=VARCHAR},",
          "express_company = #{expressCompany,jdbcType=VARCHAR},",
          "express_no = #{expressNo,jdbcType=VARCHAR},",
          "cancel_reason = #{cancelReason,jdbcType=VARCHAR},",
          "refuse_order_no = #{refuseOrderNo,jdbcType=VARCHAR},",
          "refuse_wx_no = #{refuseWxNo,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Order record);
}