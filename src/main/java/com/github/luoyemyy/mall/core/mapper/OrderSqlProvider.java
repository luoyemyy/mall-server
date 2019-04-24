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

import com.github.luoyemyy.mall.core.entity.Order;
import com.github.luoyemyy.mall.core.entity.OrderExample.Criteria;
import com.github.luoyemyy.mall.core.entity.OrderExample.Criterion;
import com.github.luoyemyy.mall.core.entity.OrderExample;
import java.util.List;
import java.util.Map;

public class OrderSqlProvider {

    public String countByExample(OrderExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("`order`");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(OrderExample example) {
        BEGIN();
        DELETE_FROM("`order`");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(Order record) {
        BEGIN();
        INSERT_INTO("`order`");
        
        if (record.getOrderNo() != null) {
            VALUES("order_no", "#{orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getState() != null) {
            VALUES("`state`", "#{state,jdbcType=INTEGER}");
        }
        
        if (record.getMoney() != null) {
            VALUES("money", "#{money,jdbcType=REAL}");
        }
        
        if (record.getPostage() != null) {
            VALUES("postage", "#{postage,jdbcType=REAL}");
        }
        
        if (record.getProductCount() != null) {
            VALUES("product_count", "#{productCount,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getPostcode() != null) {
            VALUES("postcode", "#{postcode,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getWxPayId() != null) {
            VALUES("wx_pay_id", "#{wxPayId,jdbcType=VARCHAR}");
        }
        
        if (record.getWxOrderId() != null) {
            VALUES("wx_order_id", "#{wxOrderId,jdbcType=VARCHAR}");
        }
        
        if (record.getExpressCompany() != null) {
            VALUES("express_company", "#{expressCompany,jdbcType=VARCHAR}");
        }
        
        if (record.getExpressNo() != null) {
            VALUES("express_no", "#{expressNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCancelReason() != null) {
            VALUES("cancel_reason", "#{cancelReason,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundMoney() != null) {
            VALUES("refund_money", "#{refundMoney,jdbcType=REAL}");
        }
        
        if (record.getRefuseOrderNo() != null) {
            VALUES("refuse_order_no", "#{refuseOrderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getRefuseWxNo() != null) {
            VALUES("refuse_wx_no", "#{refuseWxNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            VALUES("`status`", "#{status,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String selectByExample(OrderExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("order_no");
        SELECT("user_id");
        SELECT("`state`");
        SELECT("money");
        SELECT("postage");
        SELECT("product_count");
        SELECT("username");
        SELECT("phone");
        SELECT("address");
        SELECT("postcode");
        SELECT("create_time");
        SELECT("update_time");
        SELECT("wx_pay_id");
        SELECT("wx_order_id");
        SELECT("express_company");
        SELECT("express_no");
        SELECT("cancel_reason");
        SELECT("refund_money");
        SELECT("refuse_order_no");
        SELECT("refuse_wx_no");
        SELECT("`status`");
        FROM("`order`");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Order record = (Order) parameter.get("record");
        OrderExample example = (OrderExample) parameter.get("example");
        
        BEGIN();
        UPDATE("`order`");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getOrderNo() != null) {
            SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            SET("user_id = #{record.userId,jdbcType=BIGINT}");
        }
        
        if (record.getState() != null) {
            SET("`state` = #{record.state,jdbcType=INTEGER}");
        }
        
        if (record.getMoney() != null) {
            SET("money = #{record.money,jdbcType=REAL}");
        }
        
        if (record.getPostage() != null) {
            SET("postage = #{record.postage,jdbcType=REAL}");
        }
        
        if (record.getProductCount() != null) {
            SET("product_count = #{record.productCount,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            SET("username = #{record.username,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            SET("phone = #{record.phone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            SET("address = #{record.address,jdbcType=VARCHAR}");
        }
        
        if (record.getPostcode() != null) {
            SET("postcode = #{record.postcode,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getWxPayId() != null) {
            SET("wx_pay_id = #{record.wxPayId,jdbcType=VARCHAR}");
        }
        
        if (record.getWxOrderId() != null) {
            SET("wx_order_id = #{record.wxOrderId,jdbcType=VARCHAR}");
        }
        
        if (record.getExpressCompany() != null) {
            SET("express_company = #{record.expressCompany,jdbcType=VARCHAR}");
        }
        
        if (record.getExpressNo() != null) {
            SET("express_no = #{record.expressNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCancelReason() != null) {
            SET("cancel_reason = #{record.cancelReason,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundMoney() != null) {
            SET("refund_money = #{record.refundMoney,jdbcType=REAL}");
        }
        
        if (record.getRefuseOrderNo() != null) {
            SET("refuse_order_no = #{record.refuseOrderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getRefuseWxNo() != null) {
            SET("refuse_wx_no = #{record.refuseWxNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            SET("`status` = #{record.status,jdbcType=INTEGER}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("`order`");
        
        SET("id = #{record.id,jdbcType=BIGINT}");
        SET("order_no = #{record.orderNo,jdbcType=VARCHAR}");
        SET("user_id = #{record.userId,jdbcType=BIGINT}");
        SET("`state` = #{record.state,jdbcType=INTEGER}");
        SET("money = #{record.money,jdbcType=REAL}");
        SET("postage = #{record.postage,jdbcType=REAL}");
        SET("product_count = #{record.productCount,jdbcType=INTEGER}");
        SET("username = #{record.username,jdbcType=VARCHAR}");
        SET("phone = #{record.phone,jdbcType=VARCHAR}");
        SET("address = #{record.address,jdbcType=VARCHAR}");
        SET("postcode = #{record.postcode,jdbcType=VARCHAR}");
        SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        SET("wx_pay_id = #{record.wxPayId,jdbcType=VARCHAR}");
        SET("wx_order_id = #{record.wxOrderId,jdbcType=VARCHAR}");
        SET("express_company = #{record.expressCompany,jdbcType=VARCHAR}");
        SET("express_no = #{record.expressNo,jdbcType=VARCHAR}");
        SET("cancel_reason = #{record.cancelReason,jdbcType=VARCHAR}");
        SET("refund_money = #{record.refundMoney,jdbcType=REAL}");
        SET("refuse_order_no = #{record.refuseOrderNo,jdbcType=VARCHAR}");
        SET("refuse_wx_no = #{record.refuseWxNo,jdbcType=VARCHAR}");
        SET("`status` = #{record.status,jdbcType=INTEGER}");
        
        OrderExample example = (OrderExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(Order record) {
        BEGIN();
        UPDATE("`order`");
        
        if (record.getOrderNo() != null) {
            SET("order_no = #{orderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getState() != null) {
            SET("`state` = #{state,jdbcType=INTEGER}");
        }
        
        if (record.getMoney() != null) {
            SET("money = #{money,jdbcType=REAL}");
        }
        
        if (record.getPostage() != null) {
            SET("postage = #{postage,jdbcType=REAL}");
        }
        
        if (record.getProductCount() != null) {
            SET("product_count = #{productCount,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getPostcode() != null) {
            SET("postcode = #{postcode,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getWxPayId() != null) {
            SET("wx_pay_id = #{wxPayId,jdbcType=VARCHAR}");
        }
        
        if (record.getWxOrderId() != null) {
            SET("wx_order_id = #{wxOrderId,jdbcType=VARCHAR}");
        }
        
        if (record.getExpressCompany() != null) {
            SET("express_company = #{expressCompany,jdbcType=VARCHAR}");
        }
        
        if (record.getExpressNo() != null) {
            SET("express_no = #{expressNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCancelReason() != null) {
            SET("cancel_reason = #{cancelReason,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundMoney() != null) {
            SET("refund_money = #{refundMoney,jdbcType=REAL}");
        }
        
        if (record.getRefuseOrderNo() != null) {
            SET("refuse_order_no = #{refuseOrderNo,jdbcType=VARCHAR}");
        }
        
        if (record.getRefuseWxNo() != null) {
            SET("refuse_wx_no = #{refuseWxNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            SET("`status` = #{status,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }

    protected void applyWhere(OrderExample example, boolean includeExamplePhrase) {
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