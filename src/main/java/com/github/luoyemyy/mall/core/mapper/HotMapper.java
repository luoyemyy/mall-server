package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.Hot;
import com.github.luoyemyy.mall.core.entity.HotExample;
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

public interface HotMapper {
    @SelectProvider(type=HotSqlProvider.class, method="countByExample")
    int countByExample(HotExample example);

    @DeleteProvider(type=HotSqlProvider.class, method="deleteByExample")
    int deleteByExample(HotExample example);

    @Delete({
        "delete from hot",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into hot (image, description, ",
        "sort, state)",
        "values (#{image,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{sort,jdbcType=INTEGER}, #{state,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Hot record);

    @InsertProvider(type=HotSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Hot record);

    @SelectProvider(type=HotSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="image", property="image", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    List<Hot> selectByExample(HotExample example);

    @Select({
        "select",
        "id, image, description, sort, state",
        "from hot",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="image", property="image", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    Hot selectByPrimaryKey(Long id);

    @UpdateProvider(type=HotSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Hot record, @Param("example") HotExample example);

    @UpdateProvider(type=HotSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Hot record, @Param("example") HotExample example);

    @UpdateProvider(type=HotSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Hot record);

    @Update({
        "update hot",
        "set image = #{image,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER},",
          "state = #{state,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Hot record);
}