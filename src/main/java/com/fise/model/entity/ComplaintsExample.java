package com.fise.model.entity;

import java.util.ArrayList;
import java.util.List;

public class ComplaintsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ComplaintsExample() {
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

        public Criteria andPlaintiffIdIsNull() {
            addCriterion("plaintiff_id is null");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdIsNotNull() {
            addCriterion("plaintiff_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdEqualTo(String value) {
            addCriterion("plaintiff_id =", value, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdNotEqualTo(String value) {
            addCriterion("plaintiff_id <>", value, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdGreaterThan(String value) {
            addCriterion("plaintiff_id >", value, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdGreaterThanOrEqualTo(String value) {
            addCriterion("plaintiff_id >=", value, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdLessThan(String value) {
            addCriterion("plaintiff_id <", value, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdLessThanOrEqualTo(String value) {
            addCriterion("plaintiff_id <=", value, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdLike(String value) {
            addCriterion("plaintiff_id like", value, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdNotLike(String value) {
            addCriterion("plaintiff_id not like", value, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdIn(List<String> values) {
            addCriterion("plaintiff_id in", values, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdNotIn(List<String> values) {
            addCriterion("plaintiff_id not in", values, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdBetween(String value1, String value2) {
            addCriterion("plaintiff_id between", value1, value2, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffIdNotBetween(String value1, String value2) {
            addCriterion("plaintiff_id not between", value1, value2, "plaintiffId");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameIsNull() {
            addCriterion("plaintiff_name is null");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameIsNotNull() {
            addCriterion("plaintiff_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameEqualTo(String value) {
            addCriterion("plaintiff_name =", value, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameNotEqualTo(String value) {
            addCriterion("plaintiff_name <>", value, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameGreaterThan(String value) {
            addCriterion("plaintiff_name >", value, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameGreaterThanOrEqualTo(String value) {
            addCriterion("plaintiff_name >=", value, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameLessThan(String value) {
            addCriterion("plaintiff_name <", value, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameLessThanOrEqualTo(String value) {
            addCriterion("plaintiff_name <=", value, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameLike(String value) {
            addCriterion("plaintiff_name like", value, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameNotLike(String value) {
            addCriterion("plaintiff_name not like", value, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameIn(List<String> values) {
            addCriterion("plaintiff_name in", values, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameNotIn(List<String> values) {
            addCriterion("plaintiff_name not in", values, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameBetween(String value1, String value2) {
            addCriterion("plaintiff_name between", value1, value2, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andPlaintiffNameNotBetween(String value1, String value2) {
            addCriterion("plaintiff_name not between", value1, value2, "plaintiffName");
            return (Criteria) this;
        }

        public Criteria andDefendantIdIsNull() {
            addCriterion("defendant_id is null");
            return (Criteria) this;
        }

        public Criteria andDefendantIdIsNotNull() {
            addCriterion("defendant_id is not null");
            return (Criteria) this;
        }

        public Criteria andDefendantIdEqualTo(String value) {
            addCriterion("defendant_id =", value, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdNotEqualTo(String value) {
            addCriterion("defendant_id <>", value, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdGreaterThan(String value) {
            addCriterion("defendant_id >", value, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdGreaterThanOrEqualTo(String value) {
            addCriterion("defendant_id >=", value, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdLessThan(String value) {
            addCriterion("defendant_id <", value, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdLessThanOrEqualTo(String value) {
            addCriterion("defendant_id <=", value, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdLike(String value) {
            addCriterion("defendant_id like", value, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdNotLike(String value) {
            addCriterion("defendant_id not like", value, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdIn(List<String> values) {
            addCriterion("defendant_id in", values, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdNotIn(List<String> values) {
            addCriterion("defendant_id not in", values, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdBetween(String value1, String value2) {
            addCriterion("defendant_id between", value1, value2, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantIdNotBetween(String value1, String value2) {
            addCriterion("defendant_id not between", value1, value2, "defendantId");
            return (Criteria) this;
        }

        public Criteria andDefendantNameIsNull() {
            addCriterion("defendant_name is null");
            return (Criteria) this;
        }

        public Criteria andDefendantNameIsNotNull() {
            addCriterion("defendant_name is not null");
            return (Criteria) this;
        }

        public Criteria andDefendantNameEqualTo(String value) {
            addCriterion("defendant_name =", value, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameNotEqualTo(String value) {
            addCriterion("defendant_name <>", value, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameGreaterThan(String value) {
            addCriterion("defendant_name >", value, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameGreaterThanOrEqualTo(String value) {
            addCriterion("defendant_name >=", value, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameLessThan(String value) {
            addCriterion("defendant_name <", value, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameLessThanOrEqualTo(String value) {
            addCriterion("defendant_name <=", value, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameLike(String value) {
            addCriterion("defendant_name like", value, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameNotLike(String value) {
            addCriterion("defendant_name not like", value, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameIn(List<String> values) {
            addCriterion("defendant_name in", values, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameNotIn(List<String> values) {
            addCriterion("defendant_name not in", values, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameBetween(String value1, String value2) {
            addCriterion("defendant_name between", value1, value2, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantNameNotBetween(String value1, String value2) {
            addCriterion("defendant_name not between", value1, value2, "defendantName");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeIsNull() {
            addCriterion("defendant_type is null");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeIsNotNull() {
            addCriterion("defendant_type is not null");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeEqualTo(String value) {
            addCriterion("defendant_type =", value, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeNotEqualTo(String value) {
            addCriterion("defendant_type <>", value, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeGreaterThan(String value) {
            addCriterion("defendant_type >", value, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeGreaterThanOrEqualTo(String value) {
            addCriterion("defendant_type >=", value, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeLessThan(String value) {
            addCriterion("defendant_type <", value, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeLessThanOrEqualTo(String value) {
            addCriterion("defendant_type <=", value, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeLike(String value) {
            addCriterion("defendant_type like", value, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeNotLike(String value) {
            addCriterion("defendant_type not like", value, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeIn(List<String> values) {
            addCriterion("defendant_type in", values, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeNotIn(List<String> values) {
            addCriterion("defendant_type not in", values, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeBetween(String value1, String value2) {
            addCriterion("defendant_type between", value1, value2, "defendantType");
            return (Criteria) this;
        }

        public Criteria andDefendantTypeNotBetween(String value1, String value2) {
            addCriterion("defendant_type not between", value1, value2, "defendantType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIsNull() {
            addCriterion("complaint_type is null");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIsNotNull() {
            addCriterion("complaint_type is not null");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeEqualTo(String value) {
            addCriterion("complaint_type =", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotEqualTo(String value) {
            addCriterion("complaint_type <>", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeGreaterThan(String value) {
            addCriterion("complaint_type >", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeGreaterThanOrEqualTo(String value) {
            addCriterion("complaint_type >=", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLessThan(String value) {
            addCriterion("complaint_type <", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLessThanOrEqualTo(String value) {
            addCriterion("complaint_type <=", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeLike(String value) {
            addCriterion("complaint_type like", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotLike(String value) {
            addCriterion("complaint_type not like", value, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeIn(List<String> values) {
            addCriterion("complaint_type in", values, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotIn(List<String> values) {
            addCriterion("complaint_type not in", values, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeBetween(String value1, String value2) {
            addCriterion("complaint_type between", value1, value2, "complaintType");
            return (Criteria) this;
        }

        public Criteria andComplaintTypeNotBetween(String value1, String value2) {
            addCriterion("complaint_type not between", value1, value2, "complaintType");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andPictureIsNull() {
            addCriterion("picture is null");
            return (Criteria) this;
        }

        public Criteria andPictureIsNotNull() {
            addCriterion("picture is not null");
            return (Criteria) this;
        }

        public Criteria andPictureEqualTo(String value) {
            addCriterion("picture =", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotEqualTo(String value) {
            addCriterion("picture <>", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThan(String value) {
            addCriterion("picture >", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThanOrEqualTo(String value) {
            addCriterion("picture >=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThan(String value) {
            addCriterion("picture <", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThanOrEqualTo(String value) {
            addCriterion("picture <=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLike(String value) {
            addCriterion("picture like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotLike(String value) {
            addCriterion("picture not like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureIn(List<String> values) {
            addCriterion("picture in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotIn(List<String> values) {
            addCriterion("picture not in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureBetween(String value1, String value2) {
            addCriterion("picture between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotBetween(String value1, String value2) {
            addCriterion("picture not between", value1, value2, "picture");
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