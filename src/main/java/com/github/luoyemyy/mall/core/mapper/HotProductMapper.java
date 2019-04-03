package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.HotProduct;
import com.github.luoyemyy.mall.core.entity.HotProductExample;
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

public interface HotProductMapper {
    @SelectProvider(type=HotProductSqlProvider.class, method="countByExample")
    int countByExample(HotProductExample example);

    @DeleteProvider(type=HotProductSqlProvider.class, method="deleteByExample")
    int deleteByExample(HotProductExample example);

    @Delete({
        "delete from hot_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into hot_product (hot_id, product_id)",
        "values (#{hotId,jdbcType=BIGINT}, #{productId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(HotProduct record);

    @InsertProvider(type=HotProductSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(HotProduct record);

    @SelectProvider(type=HotProductSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="hot_id", property="hotId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT)
    })
    List<HotProduct> selectByExample(HotProductExample example);

    @Select({
        "select",
        "id, hot_id, product_id",
        "from hot_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="hot_id", property="hotId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT)
    })
    HotProduct selectByPrimaryKey(Long id);

    @UpdateProvider(type=HotProductSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") HotProduct record, @Param("example") HotProductExample example);

    @UpdateProvider(type=HotProductSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") HotProduct record, @Param("example") HotProductExample example);

    @UpdateProvider(type=HotProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HotProduct record);

    @Update({
        "update hot_product",
        "set hot_id = #{hotId,jdbcType=BIGINT},",
          "product_id = #{productId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(HotProduct record);
}