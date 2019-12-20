package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.WeChat;
import com.github.luoyemyy.mall.core.entity.WeChatExample;
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

public interface WeChatMapper {
    @SelectProvider(type=WeChatSqlProvider.class, method="countByExample")
    int countByExample(WeChatExample example);

    @DeleteProvider(type=WeChatSqlProvider.class, method="deleteByExample")
    int deleteByExample(WeChatExample example);

    @Delete({
        "delete from `we_chat`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into `we_chat` (union_id, open_id)",
        "values (#{unionId,jdbcType=VARCHAR}, #{openId,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(WeChat record);

    @InsertProvider(type=WeChatSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(WeChat record);

    @SelectProvider(type=WeChatSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR)
    })
    List<WeChat> selectByExample(WeChatExample example);

    @Select({
        "select",
        "id, union_id, open_id",
        "from `we_chat`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR)
    })
    WeChat selectByPrimaryKey(Long id);

    @UpdateProvider(type=WeChatSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WeChat record, @Param("example") WeChatExample example);

    @UpdateProvider(type=WeChatSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WeChat record, @Param("example") WeChatExample example);

    @UpdateProvider(type=WeChatSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeChat record);

    @Update({
        "update `we_chat`",
        "set union_id = #{unionId,jdbcType=VARCHAR},",
          "open_id = #{openId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(WeChat record);
}