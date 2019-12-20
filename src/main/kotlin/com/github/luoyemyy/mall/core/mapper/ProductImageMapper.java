package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.ProductImage;
import com.github.luoyemyy.mall.core.entity.ProductImageExample;
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

public interface ProductImageMapper {
    @SelectProvider(type=ProductImageSqlProvider.class, method="countByExample")
    int countByExample(ProductImageExample example);

    @DeleteProvider(type=ProductImageSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProductImageExample example);

    @Delete({
        "delete from `product_image`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into `product_image` (product_id, image, ",
        "`type`, sort)",
        "values (#{productId,jdbcType=BIGINT}, #{image,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ProductImage record);

    @InsertProvider(type=ProductImageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ProductImage record);

    @SelectProvider(type=ProductImageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="image", property="image", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<ProductImage> selectByExample(ProductImageExample example);

    @Select({
        "select",
        "id, product_id, image, `type`, sort",
        "from `product_image`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="image", property="image", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    ProductImage selectByPrimaryKey(Long id);

    @UpdateProvider(type=ProductImageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    @UpdateProvider(type=ProductImageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    @UpdateProvider(type=ProductImageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ProductImage record);

    @Update({
        "update `product_image`",
        "set product_id = #{productId,jdbcType=BIGINT},",
          "image = #{image,jdbcType=VARCHAR},",
          "`type` = #{type,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ProductImage record);
}