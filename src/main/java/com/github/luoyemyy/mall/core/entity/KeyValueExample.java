package com.github.luoyemyy.mall.core.entity;

import java.util.ArrayList;
import java.util.List;

public class KeyValueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KeyValueExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andKeyIsNull() {
            addCriterion("key is null");
            return (Criteria) this;
        }

        public Criteria andKeyIsNotNull() {
            addCriterion("key is not null");
            return (Criteria) this;
        }

        public Criteria andKeyEqualTo(String value) {
            addCriterion("key =", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotEqualTo(String value) {
            addCriterion("key <>", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThan(String value) {
            addCriterion("key >", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThanOrEqualTo(String value) {
            addCriterion("key >=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThan(String value) {
            addCriterion("key <", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThanOrEqualTo(String value) {
            addCriterion("key <=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLike(String value) {
            addCriterion("key like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotLike(String value) {
            addCriterion("key not like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyIn(List<String> values) {
            addCriterion("key in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotIn(List<String> values) {
            addCriterion("key not in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyBetween(String value1, String value2) {
            addCriterion("key between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotBetween(String value1, String value2) {
            addCriterion("key not between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andValueStringIsNull() {
            addCriterion("value_string is null");
            return (Criteria) this;
        }

        public Criteria andValueStringIsNotNull() {
            addCriterion("value_string is not null");
            return (Criteria) this;
        }

        public Criteria andValueStringEqualTo(String value) {
            addCriterion("value_string =", value, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringNotEqualTo(String value) {
            addCriterion("value_string <>", value, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringGreaterThan(String value) {
            addCriterion("value_string >", value, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringGreaterThanOrEqualTo(String value) {
            addCriterion("value_string >=", value, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringLessThan(String value) {
            addCriterion("value_string <", value, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringLessThanOrEqualTo(String value) {
            addCriterion("value_string <=", value, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringLike(String value) {
            addCriterion("value_string like", value, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringNotLike(String value) {
            addCriterion("value_string not like", value, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringIn(List<String> values) {
            addCriterion("value_string in", values, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringNotIn(List<String> values) {
            addCriterion("value_string not in", values, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringBetween(String value1, String value2) {
            addCriterion("value_string between", value1, value2, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueStringNotBetween(String value1, String value2) {
            addCriterion("value_string not between", value1, value2, "valueString");
            return (Criteria) this;
        }

        public Criteria andValueLongIsNull() {
            addCriterion("value_long is null");
            return (Criteria) this;
        }

        public Criteria andValueLongIsNotNull() {
            addCriterion("value_long is not null");
            return (Criteria) this;
        }

        public Criteria andValueLongEqualTo(Long value) {
            addCriterion("value_long =", value, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongNotEqualTo(Long value) {
            addCriterion("value_long <>", value, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongGreaterThan(Long value) {
            addCriterion("value_long >", value, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongGreaterThanOrEqualTo(Long value) {
            addCriterion("value_long >=", value, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongLessThan(Long value) {
            addCriterion("value_long <", value, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongLessThanOrEqualTo(Long value) {
            addCriterion("value_long <=", value, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongIn(List<Long> values) {
            addCriterion("value_long in", values, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongNotIn(List<Long> values) {
            addCriterion("value_long not in", values, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongBetween(Long value1, Long value2) {
            addCriterion("value_long between", value1, value2, "valueLong");
            return (Criteria) this;
        }

        public Criteria andValueLongNotBetween(Long value1, Long value2) {
            addCriterion("value_long not between", value1, value2, "valueLong");
            return (Criteria) this;
        }

        public Criteria andExpireIsNull() {
            addCriterion("expire is null");
            return (Criteria) this;
        }

        public Criteria andExpireIsNotNull() {
            addCriterion("expire is not null");
            return (Criteria) this;
        }

        public Criteria andExpireEqualTo(Long value) {
            addCriterion("expire =", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireNotEqualTo(Long value) {
            addCriterion("expire <>", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireGreaterThan(Long value) {
            addCriterion("expire >", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireGreaterThanOrEqualTo(Long value) {
            addCriterion("expire >=", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireLessThan(Long value) {
            addCriterion("expire <", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireLessThanOrEqualTo(Long value) {
            addCriterion("expire <=", value, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireIn(List<Long> values) {
            addCriterion("expire in", values, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireNotIn(List<Long> values) {
            addCriterion("expire not in", values, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireBetween(Long value1, Long value2) {
            addCriterion("expire between", value1, value2, "expire");
            return (Criteria) this;
        }

        public Criteria andExpireNotBetween(Long value1, Long value2) {
            addCriterion("expire not between", value1, value2, "expire");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}