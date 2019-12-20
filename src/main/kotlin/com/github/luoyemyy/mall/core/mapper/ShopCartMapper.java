package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.ShopCart;
import com.github.luoyemyy.mall.core.entity.ShopCartExample;
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

public interface ShopCartMapper {
    @SelectProvider(type=ShopCartSqlProvider.class, method="countByExample")
    int countByExample(ShopCartExample example);

    @DeleteProvider(type=ShopCartSqlProvider.class, method="deleteByExample")
    int deleteByExample(ShopCartExample example);

    @Delete({
        "delete from `shop_cart`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into `shop_cart` (user_id, product_id, ",
        "`count`)",
        "values (#{userId,jdbcType=BIGINT}, #{productId,jdbcType=BIGINT}, ",
        "#{count,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ShopCart record);

    @InsertProvider(type=ShopCartSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ShopCart record);

    @SelectProvider(type=ShopCartSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER)
    })
    List<ShopCart> selectByExample(ShopCartExample example);

    @Select({
        "select",
        "id, user_id, product_id, `count`",
        "from `shop_cart`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER)
    })
    ShopCart selectByPrimaryKey(Long id);

    @UpdateProvider(type=ShopCartSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    @UpdateProvider(type=ShopCartSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    @UpdateProvider(type=ShopCartSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ShopCart record);

    @Update({
        "update `shop_cart`",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "product_id = #{productId,jdbcType=BIGINT},",
          "`count` = #{count,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ShopCart record);
}