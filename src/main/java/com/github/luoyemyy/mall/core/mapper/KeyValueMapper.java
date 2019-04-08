package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.KeyValue;
import com.github.luoyemyy.mall.core.entity.KeyValueExample;
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

public interface KeyValueMapper {
    @SelectProvider(type=KeyValueSqlProvider.class, method="countByExample")
    int countByExample(KeyValueExample example);

    @DeleteProvider(type=KeyValueSqlProvider.class, method="deleteByExample")
    int deleteByExample(KeyValueExample example);

    @Delete({
        "delete from key_value",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into key_value (key, value, ",
        "expire)",
        "values (#{key,jdbcType=VARCHAR}, #{value,jdbcType=VARCHAR}, ",
        "#{expire,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(KeyValue record);

    @InsertProvider(type=KeyValueSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(KeyValue record);

    @SelectProvider(type=KeyValueSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="key", property="key", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="expire", property="expire", jdbcType=JdbcType.BIGINT)
    })
    List<KeyValue> selectByExample(KeyValueExample example);

    @Select({
        "select",
        "id, key, value, expire",
        "from key_value",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="key", property="key", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="expire", property="expire", jdbcType=JdbcType.BIGINT)
    })
    KeyValue selectByPrimaryKey(Long id);

    @UpdateProvider(type=KeyValueSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") KeyValue record, @Param("example") KeyValueExample example);

    @UpdateProvider(type=KeyValueSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") KeyValue record, @Param("example") KeyValueExample example);

    @UpdateProvider(type=KeyValueSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(KeyValue record);

    @Update({
        "update key_value",
        "set key = #{key,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR},",
          "expire = #{expire,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(KeyValue record);
}