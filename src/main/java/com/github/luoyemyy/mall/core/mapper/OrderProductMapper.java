package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.OrderProduct;
import com.github.luoyemyy.mall.core.entity.OrderProductExample;
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

public interface OrderProductMapper {
    @SelectProvider(type=OrderProductSqlProvider.class, method="countByExample")
    int countByExample(OrderProductExample example);

    @DeleteProvider(type=OrderProductSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrderProductExample example);

    @Delete({
        "delete from order_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into order_product (order_id, product_id, ",
        "count, price)",
        "values (#{orderId,jdbcType=BIGINT}, #{productId,jdbcType=BIGINT}, ",
        "#{count,jdbcType=INTEGER}, #{price,jdbcType=REAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(OrderProduct record);

    @InsertProvider(type=OrderProductSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(OrderProduct record);

    @SelectProvider(type=OrderProductSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.REAL)
    })
    List<OrderProduct> selectByExample(OrderProductExample example);

    @Select({
        "select",
        "id, order_id, product_id, count, price",
        "from order_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.REAL)
    })
    OrderProduct selectByPrimaryKey(Long id);

    @UpdateProvider(type=OrderProductSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    @UpdateProvider(type=OrderProductSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    @UpdateProvider(type=OrderProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrderProduct record);

    @Update({
        "update order_product",
        "set order_id = #{orderId,jdbcType=BIGINT},",
          "product_id = #{productId,jdbcType=BIGINT},",
          "count = #{count,jdbcType=INTEGER},",
          "price = #{price,jdbcType=REAL}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(OrderProduct record);
}