package com.fise.model.entity;

import java.util.ArrayList;
import java.util.List;

public class AppAdvertExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AppAdvertExample() {
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

        public Criteria andAdvIndexIsNull() {
            addCriterion("adv_index is null");
            return (Criteria) this;
        }

        public Criteria andAdvIndexIsNotNull() {
            addCriterion("adv_index is not null");
            return (Criteria) this;
        }

        public Criteria andAdvIndexEqualTo(String value) {
            addCriterion("adv_index =", value, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexNotEqualTo(String value) {
            addCriterion("adv_index <>", value, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexGreaterThan(String value) {
            addCriterion("adv_index >", value, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexGreaterThanOrEqualTo(String value) {
            addCriterion("adv_index >=", value, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexLessThan(String value) {
            addCriterion("adv_index <", value, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexLessThanOrEqualTo(String value) {
            addCriterion("adv_index <=", value, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexLike(String value) {
            addCriterion("adv_index like", value, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexNotLike(String value) {
            addCriterion("adv_index not like", value, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexIn(List<String> values) {
            addCriterion("adv_index in", values, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexNotIn(List<String> values) {
            addCriterion("adv_index not in", values, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexBetween(String value1, String value2) {
            addCriterion("adv_index between", value1, value2, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvIndexNotBetween(String value1, String value2) {
            addCriterion("adv_index not between", value1, value2, "advIndex");
            return (Criteria) this;
        }

        public Criteria andAdvNameIsNull() {
            addCriterion("adv_name is null");
            return (Criteria) this;
        }

        public Criteria andAdvNameIsNotNull() {
            addCriterion("adv_name is not null");
            return (Criteria) this;
        }

        public Criteria andAdvNameEqualTo(String value) {
            addCriterion("adv_name =", value, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameNotEqualTo(String value) {
            addCriterion("adv_name <>", value, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameGreaterThan(String value) {
            addCriterion("adv_name >", value, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameGreaterThanOrEqualTo(String value) {
            addCriterion("adv_name >=", value, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameLessThan(String value) {
            addCriterion("adv_name <", value, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameLessThanOrEqualTo(String value) {
            addCriterion("adv_name <=", value, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameLike(String value) {
            addCriterion("adv_name like", value, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameNotLike(String value) {
            addCriterion("adv_name not like", value, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameIn(List<String> values) {
            addCriterion("adv_name in", values, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameNotIn(List<String> values) {
            addCriterion("adv_name not in", values, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameBetween(String value1, String value2) {
            addCriterion("adv_name between", value1, value2, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvNameNotBetween(String value1, String value2) {
            addCriterion("adv_name not between", value1, value2, "advName");
            return (Criteria) this;
        }

        public Criteria andAdvUrlIsNull() {
            addCriterion("adv_url is null");
            return (Criteria) this;
        }

        public Criteria andAdvUrlIsNotNull() {
            addCriterion("adv_url is not null");
            return (Criteria) this;
        }

        public Criteria andAdvUrlEqualTo(String value) {
            addCriterion("adv_url =", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlNotEqualTo(String value) {
            addCriterion("adv_url <>", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlGreaterThan(String value) {
            addCriterion("adv_url >", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlGreaterThanOrEqualTo(String value) {
            addCriterion("adv_url >=", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlLessThan(String value) {
            addCriterion("adv_url <", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlLessThanOrEqualTo(String value) {
            addCriterion("adv_url <=", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlLike(String value) {
            addCriterion("adv_url like", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlNotLike(String value) {
            addCriterion("adv_url not like", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlIn(List<String> values) {
            addCriterion("adv_url in", values, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlNotIn(List<String> values) {
            addCriterion("adv_url not in", values, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlBetween(String value1, String value2) {
            addCriterion("adv_url between", value1, value2, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlNotBetween(String value1, String value2) {
            addCriterion("adv_url not between", value1, value2, "advUrl");
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

        public Criteria andProrityIsNull() {
            addCriterion("prority is null");
            return (Criteria) this;
        }

        public Criteria andProrityIsNotNull() {
            addCriterion("prority is not null");
            return (Criteria) this;
        }

        public Criteria andProrityEqualTo(Integer value) {
            addCriterion("prority =", value, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityNotEqualTo(Integer value) {
            addCriterion("prority <>", value, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityGreaterThan(Integer value) {
            addCriterion("prority >", value, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityGreaterThanOrEqualTo(Integer value) {
            addCriterion("prority >=", value, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityLessThan(Integer value) {
            addCriterion("prority <", value, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityLessThanOrEqualTo(Integer value) {
            addCriterion("prority <=", value, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityIn(List<Integer> values) {
            addCriterion("prority in", values, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityNotIn(List<Integer> values) {
            addCriterion("prority not in", values, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityBetween(Integer value1, Integer value2) {
            addCriterion("prority between", value1, value2, "prority");
            return (Criteria) this;
        }

        public Criteria andProrityNotBetween(Integer value1, Integer value2) {
            addCriterion("prority not between", value1, value2, "prority");
            return (Criteria) this;
        }

        public Criteria andDelayTimeIsNull() {
            addCriterion("delay_time is null");
            return (Criteria) this;
        }

        public Criteria andDelayTimeIsNotNull() {
            addCriterion("delay_time is not null");
            return (Criteria) this;
        }

        public Criteria andDelayTimeEqualTo(Integer value) {
            addCriterion("delay_time =", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeNotEqualTo(Integer value) {
            addCriterion("delay_time <>", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeGreaterThan(Integer value) {
            addCriterion("delay_time >", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("delay_time >=", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeLessThan(Integer value) {
            addCriterion("delay_time <", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeLessThanOrEqualTo(Integer value) {
            addCriterion("delay_time <=", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeIn(List<Integer> values) {
            addCriterion("delay_time in", values, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeNotIn(List<Integer> values) {
            addCriterion("delay_time not in", values, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeBetween(Integer value1, Integer value2) {
            addCriterion("delay_time between", value1, value2, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("delay_time not between", value1, value2, "delayTime");
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andInnerTypeIsNull() {
            addCriterion("inner_type is null");
            return (Criteria) this;
        }

        public Criteria andInnerTypeIsNotNull() {
            addCriterion("inner_type is not null");
            return (Criteria) this;
        }

        public Criteria andInnerTypeEqualTo(String value) {
            addCriterion("inner_type =", value, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeNotEqualTo(String value) {
            addCriterion("inner_type <>", value, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeGreaterThan(String value) {
            addCriterion("inner_type >", value, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("inner_type >=", value, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeLessThan(String value) {
            addCriterion("inner_type <", value, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeLessThanOrEqualTo(String value) {
            addCriterion("inner_type <=", value, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeLike(String value) {
            addCriterion("inner_type like", value, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeNotLike(String value) {
            addCriterion("inner_type not like", value, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeIn(List<String> values) {
            addCriterion("inner_type in", values, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeNotIn(List<String> values) {
            addCriterion("inner_type not in", values, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeBetween(String value1, String value2) {
            addCriterion("inner_type between", value1, value2, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerTypeNotBetween(String value1, String value2) {
            addCriterion("inner_type not between", value1, value2, "innerType");
            return (Criteria) this;
        }

        public Criteria andInnerIdIsNull() {
            addCriterion("inner_id is null");
            return (Criteria) this;
        }

        public Criteria andInnerIdIsNotNull() {
            addCriterion("inner_id is not null");
            return (Criteria) this;
        }

        public Criteria andInnerIdEqualTo(Integer value) {
            addCriterion("inner_id =", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdNotEqualTo(Integer value) {
            addCriterion("inner_id <>", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdGreaterThan(Integer value) {
            addCriterion("inner_id >", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("inner_id >=", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdLessThan(Integer value) {
            addCriterion("inner_id <", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("inner_id <=", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdIn(List<Integer> values) {
            addCriterion("inner_id in", values, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdNotIn(List<Integer> values) {
            addCriterion("inner_id not in", values, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdBetween(Integer value1, Integer value2) {
            addCriterion("inner_id between", value1, value2, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("inner_id not between", value1, value2, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerNameIsNull() {
            addCriterion("inner_name is null");
            return (Criteria) this;
        }

        public Criteria andInnerNameIsNotNull() {
            addCriterion("inner_name is not null");
            return (Criteria) this;
        }

        public Criteria andInnerNameEqualTo(String value) {
            addCriterion("inner_name =", value, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameNotEqualTo(String value) {
            addCriterion("inner_name <>", value, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameGreaterThan(String value) {
            addCriterion("inner_name >", value, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("inner_name >=", value, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameLessThan(String value) {
            addCriterion("inner_name <", value, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameLessThanOrEqualTo(String value) {
            addCriterion("inner_name <=", value, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameLike(String value) {
            addCriterion("inner_name like", value, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameNotLike(String value) {
            addCriterion("inner_name not like", value, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameIn(List<String> values) {
            addCriterion("inner_name in", values, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameNotIn(List<String> values) {
            addCriterion("inner_name not in", values, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameBetween(String value1, String value2) {
            addCriterion("inner_name between", value1, value2, "innerName");
            return (Criteria) this;
        }

        public Criteria andInnerNameNotBetween(String value1, String value2) {
            addCriterion("inner_name not between", value1, value2, "innerName");
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