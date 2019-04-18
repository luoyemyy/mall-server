package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.Category;
import com.github.luoyemyy.mall.core.entity.CategoryExample;
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

public interface CategoryMapper {
    @SelectProvider(type=CategorySqlProvider.class, method="countByExample")
    int countByExample(CategoryExample example);

    @DeleteProvider(type=CategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(CategoryExample example);

    @Delete({
        "delete from `category`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into `category` (`name`, sort, ",
        "`state`)",
        "values (#{name,jdbcType=VARCHAR}, #{sort,jdbcType=INTEGER}, ",
        "#{state,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Category record);

    @InsertProvider(type=CategorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Category record);

    @SelectProvider(type=CategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    List<Category> selectByExample(CategoryExample example);

    @Select({
        "select",
        "id, `name`, sort, `state`",
        "from `category`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER)
    })
    Category selectByPrimaryKey(Long id);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Category record);

    @Update({
        "update `category`",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER},",
          "`state` = #{state,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Category record);
}