package com.fise.model.entity;

import java.util.ArrayList;
import java.util.List;

public class IMMarkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public IMMarkExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFromUserIsNull() {
            addCriterion("from_user is null");
            return (Criteria) this;
        }

        public Criteria andFromUserIsNotNull() {
            addCriterion("from_user is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserEqualTo(Integer value) {
            addCriterion("from_user =", value, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserNotEqualTo(Integer value) {
            addCriterion("from_user <>", value, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserGreaterThan(Integer value) {
            addCriterion("from_user >", value, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("from_user >=", value, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserLessThan(Integer value) {
            addCriterion("from_user <", value, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserLessThanOrEqualTo(Integer value) {
            addCriterion("from_user <=", value, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserIn(List<Integer> values) {
            addCriterion("from_user in", values, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserNotIn(List<Integer> values) {
            addCriterion("from_user not in", values, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserBetween(Integer value1, Integer value2) {
            addCriterion("from_user between", value1, value2, "fromUser");
            return (Criteria) this;
        }

        public Criteria andFromUserNotBetween(Integer value1, Integer value2) {
            addCriterion("from_user not between", value1, value2, "fromUser");
            return (Criteria) this;
        }

        public Criteria andDestUserIsNull() {
            addCriterion("dest_user is null");
            return (Criteria) this;
        }

        public Criteria andDestUserIsNotNull() {
            addCriterion("dest_user is not null");
            return (Criteria) this;
        }

        public Criteria andDestUserEqualTo(Integer value) {
            addCriterion("dest_user =", value, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserNotEqualTo(Integer value) {
            addCriterion("dest_user <>", value, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserGreaterThan(Integer value) {
            addCriterion("dest_user >", value, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("dest_user >=", value, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserLessThan(Integer value) {
            addCriterion("dest_user <", value, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserLessThanOrEqualTo(Integer value) {
            addCriterion("dest_user <=", value, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserIn(List<Integer> values) {
            addCriterion("dest_user in", values, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserNotIn(List<Integer> values) {
            addCriterion("dest_user not in", values, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserBetween(Integer value1, Integer value2) {
            addCriterion("dest_user between", value1, value2, "destUser");
            return (Criteria) this;
        }

        public Criteria andDestUserNotBetween(Integer value1, Integer value2) {
            addCriterion("dest_user not between", value1, value2, "destUser");
            return (Criteria) this;
        }

        public Criteria andMarkTypeIsNull() {
            addCriterion("mark_type is null");
            return (Criteria) this;
        }

        public Criteria andMarkTypeIsNotNull() {
            addCriterion("mark_type is not null");
            return (Criteria) this;
        }

        public Criteria andMarkTypeEqualTo(Integer value) {
            addCriterion("mark_type =", value, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeNotEqualTo(Integer value) {
            addCriterion("mark_type <>", value, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeGreaterThan(Integer value) {
            addCriterion("mark_type >", value, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mark_type >=", value, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeLessThan(Integer value) {
            addCriterion("mark_type <", value, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeLessThanOrEqualTo(Integer value) {
            addCriterion("mark_type <=", value, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeIn(List<Integer> values) {
            addCriterion("mark_type in", values, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeNotIn(List<Integer> values) {
            addCriterion("mark_type not in", values, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeBetween(Integer value1, Integer value2) {
            addCriterion("mark_type between", value1, value2, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("mark_type not between", value1, value2, "markType");
            return (Criteria) this;
        }

        public Criteria andMarkNameIsNull() {
            addCriterion("mark_name is null");
            return (Criteria) this;
        }

        public Criteria andMarkNameIsNotNull() {
            addCriterion("mark_name is not null");
            return (Criteria) this;
        }

        public Criteria andMarkNameEqualTo(String value) {
            addCriterion("mark_name =", value, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameNotEqualTo(String value) {
            addCriterion("mark_name <>", value, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameGreaterThan(String value) {
            addCriterion("mark_name >", value, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameGreaterThanOrEqualTo(String value) {
            addCriterion("mark_name >=", value, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameLessThan(String value) {
            addCriterion("mark_name <", value, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameLessThanOrEqualTo(String value) {
            addCriterion("mark_name <=", value, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameLike(String value) {
            addCriterion("mark_name like", value, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameNotLike(String value) {
            addCriterion("mark_name not like", value, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameIn(List<String> values) {
            addCriterion("mark_name in", values, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameNotIn(List<String> values) {
            addCriterion("mark_name not in", values, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameBetween(String value1, String value2) {
            addCriterion("mark_name between", value1, value2, "markName");
            return (Criteria) this;
        }

        public Criteria andMarkNameNotBetween(String value1, String value2) {
            addCriterion("mark_name not between", value1, value2, "markName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Integer value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Integer value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Integer value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Integer value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Integer value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Integer> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Integer> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Integer value1, Integer value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Integer value1, Integer value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Integer value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Integer value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Integer value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Integer value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Integer value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Integer> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Integer> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Integer value1, Integer value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Integer value1, Integer value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }
    }

    /**
     */
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