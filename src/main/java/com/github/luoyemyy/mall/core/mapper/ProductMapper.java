package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.Product;
import com.github.luoyemyy.mall.core.entity.ProductExample;
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

public interface ProductMapper {
    @SelectProvider(type=ProductSqlProvider.class, method="countByExample")
    int countByExample(ProductExample example);

    @DeleteProvider(type=ProductSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProductExample example);

    @Delete({
        "delete from product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into product (cover_image, name, ",
        "description, market_price, ",
        "actual_price, stock, ",
        "online, sort, create_time)",
        "values (#{coverImage,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{marketPrice,jdbcType=REAL}, ",
        "#{actualPrice,jdbcType=REAL}, #{stock,jdbcType=INTEGER}, ",
        "#{online,jdbcType=BIT}, #{sort,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Product record);

    @InsertProvider(type=ProductSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Product record);

    @SelectProvider(type=ProductSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="cover_image", property="coverImage", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="market_price", property="marketPrice", jdbcType=JdbcType.REAL),
        @Result(column="actual_price", property="actualPrice", jdbcType=JdbcType.REAL),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="online", property="online", jdbcType=JdbcType.BIT),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Product> selectByExample(ProductExample example);

    @Select({
        "select",
        "id, cover_image, name, description, market_price, actual_price, stock, online, ",
        "sort, create_time",
        "from product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="cover_image", property="coverImage", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="market_price", property="marketPrice", jdbcType=JdbcType.REAL),
        @Result(column="actual_price", property="actualPrice", jdbcType=JdbcType.REAL),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="online", property="online", jdbcType=JdbcType.BIT),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Product selectByPrimaryKey(Long id);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Product record);

    @Update({
        "update product",
        "set cover_image = #{coverImage,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "market_price = #{marketPrice,jdbcType=REAL},",
          "actual_price = #{actualPrice,jdbcType=REAL},",
          "stock = #{stock,jdbcType=INTEGER},",
          "online = #{online,jdbcType=BIT},",
          "sort = #{sort,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Product record);
}