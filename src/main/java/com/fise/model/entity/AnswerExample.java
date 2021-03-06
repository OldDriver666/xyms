package com.fise.model.entity;
 
import java.util.ArrayList;
import java.util.List;

import com.fise.model.entity.ProblemsExample.Criteria;
 
public class AnswerExample {
    protected String orderByClause;
 
    protected boolean distinct;
 
    protected List<Criteria> oredCriteria;
 
    private Integer limit;
 
    private Integer offset;
 
    public AnswerExample() {
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
 
        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }
 
        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }
 
        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }
 
        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdIsNull() {
            addCriterion("problem_id is null");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdIsNotNull() {
            addCriterion("problem_id is not null");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdEqualTo(Integer value) {
            addCriterion("problem_id =", value, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdNotEqualTo(Integer value) {
            addCriterion("problem_id <>", value, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdGreaterThan(Integer value) {
            addCriterion("problem_id >", value, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("problem_id >=", value, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdLessThan(Integer value) {
            addCriterion("problem_id <", value, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdLessThanOrEqualTo(Integer value) {
            addCriterion("problem_id <=", value, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdIn(List<Integer> values) {
            addCriterion("problem_id in", values, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdNotIn(List<Integer> values) {
            addCriterion("problem_id not in", values, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdBetween(Integer value1, Integer value2) {
            addCriterion("problem_id between", value1, value2, "problemId");
            return (Criteria) this;
        }
 
        public Criteria andProblemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("problem_id not between", value1, value2, "problemId");
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
 
        public Criteria andAgreeNumIsNull() {
            addCriterion("agree_num is null");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumIsNotNull() {
            addCriterion("agree_num is not null");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumEqualTo(Integer value) {
            addCriterion("agree_num =", value, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumNotEqualTo(Integer value) {
            addCriterion("agree_num <>", value, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumGreaterThan(Integer value) {
            addCriterion("agree_num >", value, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("agree_num >=", value, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumLessThan(Integer value) {
            addCriterion("agree_num <", value, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumLessThanOrEqualTo(Integer value) {
            addCriterion("agree_num <=", value, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumIn(List<Integer> values) {
            addCriterion("agree_num in", values, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumNotIn(List<Integer> values) {
            addCriterion("agree_num not in", values, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumBetween(Integer value1, Integer value2) {
            addCriterion("agree_num between", value1, value2, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andAgreeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("agree_num not between", value1, value2, "agreeNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumIsNull() {
            addCriterion("comment_num is null");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumIsNotNull() {
            addCriterion("comment_num is not null");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumEqualTo(Integer value) {
            addCriterion("comment_num =", value, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumNotEqualTo(Integer value) {
            addCriterion("comment_num <>", value, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumGreaterThan(Integer value) {
            addCriterion("comment_num >", value, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_num >=", value, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumLessThan(Integer value) {
            addCriterion("comment_num <", value, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumLessThanOrEqualTo(Integer value) {
            addCriterion("comment_num <=", value, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumIn(List<Integer> values) {
            addCriterion("comment_num in", values, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumNotIn(List<Integer> values) {
            addCriterion("comment_num not in", values, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumBetween(Integer value1, Integer value2) {
            addCriterion("comment_num between", value1, value2, "commentNum");
            return (Criteria) this;
        }
 
        public Criteria andCommentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_num not between", value1, value2, "commentNum");
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
        
        public Criteria andNickLike(String value) {
            addCriterion("nick like", value, "nick");
            return (Criteria) this;
        }
        
        public Criteria andTitleLike(String value) {
            addCriterion("title like", value , "title");
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