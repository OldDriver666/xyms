package com.fise.model.entity;

import java.util.ArrayList;
import java.util.List;

public class IMRelationShipExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public IMRelationShipExample() {
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

        public Criteria andSmallidIsNull() {
            addCriterion("smallId is null");
            return (Criteria) this;
        }

        public Criteria andSmallidIsNotNull() {
            addCriterion("smallId is not null");
            return (Criteria) this;
        }

        public Criteria andSmallidEqualTo(Integer value) {
            addCriterion("smallId =", value, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidNotEqualTo(Integer value) {
            addCriterion("smallId <>", value, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidGreaterThan(Integer value) {
            addCriterion("smallId >", value, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidGreaterThanOrEqualTo(Integer value) {
            addCriterion("smallId >=", value, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidLessThan(Integer value) {
            addCriterion("smallId <", value, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidLessThanOrEqualTo(Integer value) {
            addCriterion("smallId <=", value, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidIn(List<Integer> values) {
            addCriterion("smallId in", values, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidNotIn(List<Integer> values) {
            addCriterion("smallId not in", values, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidBetween(Integer value1, Integer value2) {
            addCriterion("smallId between", value1, value2, "smallid");
            return (Criteria) this;
        }

        public Criteria andSmallidNotBetween(Integer value1, Integer value2) {
            addCriterion("smallId not between", value1, value2, "smallid");
            return (Criteria) this;
        }

        public Criteria andBigidIsNull() {
            addCriterion("bigId is null");
            return (Criteria) this;
        }

        public Criteria andBigidIsNotNull() {
            addCriterion("bigId is not null");
            return (Criteria) this;
        }

        public Criteria andBigidEqualTo(Integer value) {
            addCriterion("bigId =", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotEqualTo(Integer value) {
            addCriterion("bigId <>", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidGreaterThan(Integer value) {
            addCriterion("bigId >", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bigId >=", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidLessThan(Integer value) {
            addCriterion("bigId <", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidLessThanOrEqualTo(Integer value) {
            addCriterion("bigId <=", value, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidIn(List<Integer> values) {
            addCriterion("bigId in", values, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotIn(List<Integer> values) {
            addCriterion("bigId not in", values, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidBetween(Integer value1, Integer value2) {
            addCriterion("bigId between", value1, value2, "bigid");
            return (Criteria) this;
        }

        public Criteria andBigidNotBetween(Integer value1, Integer value2) {
            addCriterion("bigId not between", value1, value2, "bigid");
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

        public Criteria andSmallpriorityIsNull() {
            addCriterion("smallPriority is null");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityIsNotNull() {
            addCriterion("smallPriority is not null");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityEqualTo(Integer value) {
            addCriterion("smallPriority =", value, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityNotEqualTo(Integer value) {
            addCriterion("smallPriority <>", value, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityGreaterThan(Integer value) {
            addCriterion("smallPriority >", value, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("smallPriority >=", value, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityLessThan(Integer value) {
            addCriterion("smallPriority <", value, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityLessThanOrEqualTo(Integer value) {
            addCriterion("smallPriority <=", value, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityIn(List<Integer> values) {
            addCriterion("smallPriority in", values, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityNotIn(List<Integer> values) {
            addCriterion("smallPriority not in", values, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityBetween(Integer value1, Integer value2) {
            addCriterion("smallPriority between", value1, value2, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andSmallpriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("smallPriority not between", value1, value2, "smallpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityIsNull() {
            addCriterion("bigPriority is null");
            return (Criteria) this;
        }

        public Criteria andBigpriorityIsNotNull() {
            addCriterion("bigPriority is not null");
            return (Criteria) this;
        }

        public Criteria andBigpriorityEqualTo(Integer value) {
            addCriterion("bigPriority =", value, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityNotEqualTo(Integer value) {
            addCriterion("bigPriority <>", value, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityGreaterThan(Integer value) {
            addCriterion("bigPriority >", value, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("bigPriority >=", value, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityLessThan(Integer value) {
            addCriterion("bigPriority <", value, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityLessThanOrEqualTo(Integer value) {
            addCriterion("bigPriority <=", value, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityIn(List<Integer> values) {
            addCriterion("bigPriority in", values, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityNotIn(List<Integer> values) {
            addCriterion("bigPriority not in", values, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityBetween(Integer value1, Integer value2) {
            addCriterion("bigPriority between", value1, value2, "bigpriority");
            return (Criteria) this;
        }

        public Criteria andBigpriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("bigPriority not between", value1, value2, "bigpriority");
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