package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.WeChatServer;
import com.github.luoyemyy.mall.core.entity.WeChatServerExample;
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

public interface WeChatServerMapper {
    @SelectProvider(type=WeChatServerSqlProvider.class, method="countByExample")
    int countByExample(WeChatServerExample example);

    @DeleteProvider(type=WeChatServerSqlProvider.class, method="deleteByExample")
    int deleteByExample(WeChatServerExample example);

    @Delete({
        "delete from we_chat_server",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into we_chat_server (access_token, access_token_expire)",
        "values (#{accessToken,jdbcType=VARCHAR}, #{accessTokenExpire,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(WeChatServer record);

    @InsertProvider(type=WeChatServerSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(WeChatServer record);

    @SelectProvider(type=WeChatServerSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="access_token", property="accessToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_token_expire", property="accessTokenExpire", jdbcType=JdbcType.BIGINT)
    })
    List<WeChatServer> selectByExample(WeChatServerExample example);

    @Select({
        "select",
        "id, access_token, access_token_expire",
        "from we_chat_server",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="access_token", property="accessToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_token_expire", property="accessTokenExpire", jdbcType=JdbcType.BIGINT)
    })
    WeChatServer selectByPrimaryKey(Long id);

    @UpdateProvider(type=WeChatServerSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WeChatServer record, @Param("example") WeChatServerExample example);

    @UpdateProvider(type=WeChatServerSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WeChatServer record, @Param("example") WeChatServerExample example);

    @UpdateProvider(type=WeChatServerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WeChatServer record);

    @Update({
        "update we_chat_server",
        "set access_token = #{accessToken,jdbcType=VARCHAR},",
          "access_token_expire = #{accessTokenExpire,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(WeChatServer record);
}