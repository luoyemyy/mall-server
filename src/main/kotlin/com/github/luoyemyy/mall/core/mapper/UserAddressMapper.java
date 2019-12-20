package com.github.luoyemyy.mall.core.mapper;

import com.github.luoyemyy.mall.core.entity.UserAddress;
import com.github.luoyemyy.mall.core.entity.UserAddressExample;
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

public interface UserAddressMapper {
    @SelectProvider(type=UserAddressSqlProvider.class, method="countByExample")
    int countByExample(UserAddressExample example);

    @DeleteProvider(type=UserAddressSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserAddressExample example);

    @Delete({
        "delete from `user_address`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into `user_address` (user_id, address_id, ",
        "`type`)",
        "values (#{userId,jdbcType=BIGINT}, #{addressId,jdbcType=BIGINT}, ",
        "#{type,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserAddress record);

    @InsertProvider(type=UserAddressSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserAddress record);

    @SelectProvider(type=UserAddressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="address_id", property="addressId", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<UserAddress> selectByExample(UserAddressExample example);

    @Select({
        "select",
        "id, user_id, address_id, `type`",
        "from `user_address`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="address_id", property="addressId", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    UserAddress selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserAddressSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    @UpdateProvider(type=UserAddressSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    @UpdateProvider(type=UserAddressSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAddress record);

    @Update({
        "update `user_address`",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "address_id = #{addressId,jdbcType=BIGINT},",
          "`type` = #{type,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserAddress record);
}