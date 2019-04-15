package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.Postage;
import com.github.luoyemyy.mall.core.entity.PostageExample;
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

public interface PostageMapper {
    @SelectProvider(type=PostageSqlProvider.class, method="countByExample")
    int countByExample(PostageExample example);

    @DeleteProvider(type=PostageSqlProvider.class, method="deleteByExample")
    int deleteByExample(PostageExample example);

    @Delete({
        "delete from postage",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into postage (area, price, ",
        "post)",
        "values (#{area,jdbcType=VARCHAR}, #{price,jdbcType=REAL}, ",
        "#{post,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Postage record);

    @InsertProvider(type=PostageSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Postage record);

    @SelectProvider(type=PostageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.REAL),
        @Result(column="post", property="post", jdbcType=JdbcType.INTEGER)
    })
    List<Postage> selectByExample(PostageExample example);

    @Select({
        "select",
        "id, area, price, post",
        "from postage",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.REAL),
        @Result(column="post", property="post", jdbcType=JdbcType.INTEGER)
    })
    Postage selectByPrimaryKey(Long id);

    @UpdateProvider(type=PostageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Postage record, @Param("example") PostageExample example);

    @UpdateProvider(type=PostageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Postage record, @Param("example") PostageExample example);

    @UpdateProvider(type=PostageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Postage record);

    @Update({
        "update postage",
        "set area = #{area,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=REAL},",
          "post = #{post,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Postage record);
}