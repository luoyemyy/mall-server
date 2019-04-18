package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.Feedback;
import com.github.luoyemyy.mall.core.entity.FeedbackExample;
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

public interface FeedbackMapper {
    @SelectProvider(type=FeedbackSqlProvider.class, method="countByExample")
    int countByExample(FeedbackExample example);

    @DeleteProvider(type=FeedbackSqlProvider.class, method="deleteByExample")
    int deleteByExample(FeedbackExample example);

    @Delete({
        "delete from `feedback`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into `feedback` (user_id, content)",
        "values (#{userId,jdbcType=BIGINT}, #{content,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Feedback record);

    @InsertProvider(type=FeedbackSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(Feedback record);

    @SelectProvider(type=FeedbackSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR)
    })
    List<Feedback> selectByExample(FeedbackExample example);

    @Select({
        "select",
        "id, user_id, content",
        "from `feedback`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR)
    })
    Feedback selectByPrimaryKey(Long id);

    @UpdateProvider(type=FeedbackSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    @UpdateProvider(type=FeedbackSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    @UpdateProvider(type=FeedbackSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Feedback record);

    @Update({
        "update `feedback`",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "content = #{content,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Feedback record);
}