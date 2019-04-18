package com.github.luoyemyy.mall.core.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.github.luoyemyy.mall.core.entity.Address;
import com.github.luoyemyy.mall.core.entity.AddressExample.Criteria;
import com.github.luoyemyy.mall.core.entity.AddressExample.Criterion;
import com.github.luoyemyy.mall.core.entity.AddressExample;
import java.util.List;
import java.util.Map;

public class AddressSqlProvider {

    public String countByExample(AddressExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("`address`");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(AddressExample example) {
        BEGIN();
        DELETE_FROM("`address`");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(Address record) {
        BEGIN();
        INSERT_INTO("`address`");
        
        if (record.getName() != null) {
            VALUES("`name`", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getPostCode() != null) {
            VALUES("post_code", "#{postCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            VALUES("country", "#{country,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            VALUES("province", "#{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            VALUES("city", "#{city,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            VALUES("county", "#{county,jdbcType=VARCHAR}");
        }
        
        if (record.getStreet() != null) {
            VALUES("street", "#{street,jdbcType=VARCHAR}");
        }
        
        if (record.getSummary() != null) {
            VALUES("summary", "#{summary,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(AddressExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("`name`");
        SELECT("phone");
        SELECT("post_code");
        SELECT("country");
        SELECT("province");
        SELECT("city");
        SELECT("county");
        SELECT("street");
        SELECT("summary");
        FROM("`address`");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Address record = (Address) parameter.get("record");
        AddressExample example = (AddressExample) parameter.get("example");
        
        BEGIN();
        UPDATE("`address`");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            SET("`name` = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            SET("phone = #{record.phone,jdbcType=VARCHAR}");
        }
        
        if (record.getPostCode() != null) {
            SET("post_code = #{record.postCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            SET("country = #{record.country,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            SET("province = #{record.province,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            SET("city = #{record.city,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            SET("county = #{record.county,jdbcType=VARCHAR}");
        }
        
        if (record.getStreet() != null) {
            SET("street = #{record.street,jdbcType=VARCHAR}");
        }
        
        if (record.getSummary() != null) {
            SET("summary = #{record.summary,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("`address`");
        
        SET("id = #{record.id,jdbcType=BIGINT}");
        SET("`name` = #{record.name,jdbcType=VARCHAR}");
        SET("phone = #{record.phone,jdbcType=VARCHAR}");
        SET("post_code = #{record.postCode,jdbcType=VARCHAR}");
        SET("country = #{record.country,jdbcType=VARCHAR}");
        SET("province = #{record.province,jdbcType=VARCHAR}");
        SET("city = #{record.city,jdbcType=VARCHAR}");
        SET("county = #{record.county,jdbcType=VARCHAR}");
        SET("street = #{record.street,jdbcType=VARCHAR}");
        SET("summary = #{record.summary,jdbcType=VARCHAR}");
        
        AddressExample example = (AddressExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(Address record) {
        BEGIN();
        UPDATE("`address`");
        
        if (record.getName() != null) {
            SET("`name` = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getPostCode() != null) {
            SET("post_code = #{postCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            SET("country = #{country,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            SET("province = #{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            SET("city = #{city,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            SET("county = #{county,jdbcType=VARCHAR}");
        }
        
        if (record.getStreet() != null) {
            SET("street = #{street,jdbcType=VARCHAR}");
        }
        
        if (record.getSummary() != null) {
            SET("summary = #{summary,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    protected void applyWhere(AddressExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}