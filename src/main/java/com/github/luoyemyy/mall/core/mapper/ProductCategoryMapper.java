package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.ProductCategory;
import com.github.luoyemyy.mall.core.entity.ProductCategoryExample;
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

public interface ProductCategoryMapper {
    @SelectProvider(type=ProductCategorySqlProvider.class, method="countByExample")
    int countByExample(ProductCategoryExample example);

    @DeleteProvider(type=ProductCategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(ProductCategoryExample example);

    @Delete({
        "delete from product_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into product_category (product_id, category_id, ",
        "sort)",
        "values (#{productId,jdbcType=BIGINT}, #{categoryId,jdbcType=BIGINT}, ",
        "#{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(ProductCategory record);

    @InsertProvider(type=ProductCategorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(ProductCategory record);

    @SelectProvider(type=ProductCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<ProductCategory> selectByExample(ProductCategoryExample example);

    @Select({
        "select",
        "id, product_id, category_id, sort",
        "from product_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    ProductCategory selectByPrimaryKey(Long id);

    @UpdateProvider(type=ProductCategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    @UpdateProvider(type=ProductCategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    @UpdateProvider(type=ProductCategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ProductCategory record);

    @Update({
        "update product_category",
        "set product_id = #{productId,jdbcType=BIGINT},",
          "category_id = #{categoryId,jdbcType=BIGINT},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ProductCategory record);
}