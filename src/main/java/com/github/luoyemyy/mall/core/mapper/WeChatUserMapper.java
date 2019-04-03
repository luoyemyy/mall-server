package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.WeChatUser;
import com.github.luoyemyy.mall.core.entity.WeChatUserExample;
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

public interface WeChatUserMapper {
    @SelectProvider(type=WeChatUserSqlProvider.class, method="countByExample")
    int countByExample(WeChatUserExample example);

    @DeleteProvider(type=WeChatUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(WeChatUserExample example);

    @Delete({
        "delete from we_chat_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into we_chat_user (we_chat_id, user_id, ",
        "token)",
        "values (#{weChatId,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
        "#{token,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(WeChatUser record);

    @InsertProvider(type=WeChatUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(WeChatUser record);

    @SelectProvider(type=WeChatUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="we_chat_id", property="weChatId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR)
    })
    List<WeChatUser> selectByExample(WeChatUserExample example);

    @Select({
        "select",
        "id, we_chat_id, user_id, token",
        "from we_chat_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="we_chat_id", property="weChatId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR)
    })
    WeChatUser selectByPrimaryKey(Long id);

    @UpdateProvider(type=WeChatUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WeChatUser record, @Param("example") WeChatUserExample example);

    @UpdateProvider(type=WeChatUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WeChatUser record, @Param("example") WeChatUserExample example);

    @UpdateProvider(type=WeChatUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeChatUser record);

    @Update({
        "update we_chat_user",
        "set we_chat_id = #{weChatId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "token = #{token,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(WeChatUser record);
}