package com.fise.model.entity;

import java.util.ArrayList;
import java.util.List;

public class DeviceConfExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public DeviceConfExample() {
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

        public Criteria andDeviceIdIsNull() {
            addCriterion("device_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("device_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(Integer value) {
            addCriterion("device_id =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(Integer value) {
            addCriterion("device_id <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(Integer value) {
            addCriterion("device_id >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_id >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(Integer value) {
            addCriterion("device_id <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(Integer value) {
            addCriterion("device_id <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<Integer> values) {
            addCriterion("device_id in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<Integer> values) {
            addCriterion("device_id not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(Integer value1, Integer value2) {
            addCriterion("device_id between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("device_id not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNull() {
            addCriterion("master_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNotNull() {
            addCriterion("master_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterIdEqualTo(Integer value) {
            addCriterion("master_id =", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotEqualTo(Integer value) {
            addCriterion("master_id <>", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThan(Integer value) {
            addCriterion("master_id >", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("master_id >=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThan(Integer value) {
            addCriterion("master_id <", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThanOrEqualTo(Integer value) {
            addCriterion("master_id <=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIn(List<Integer> values) {
            addCriterion("master_id in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotIn(List<Integer> values) {
            addCriterion("master_id not in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdBetween(Integer value1, Integer value2) {
            addCriterion("master_id between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("master_id not between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffIsNull() {
            addCriterion("alarm_takeoff is null");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffIsNotNull() {
            addCriterion("alarm_takeoff is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffEqualTo(Integer value) {
            addCriterion("alarm_takeoff =", value, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffNotEqualTo(Integer value) {
            addCriterion("alarm_takeoff <>", value, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffGreaterThan(Integer value) {
            addCriterion("alarm_takeoff >", value, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_takeoff >=", value, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffLessThan(Integer value) {
            addCriterion("alarm_takeoff <", value, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_takeoff <=", value, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffIn(List<Integer> values) {
            addCriterion("alarm_takeoff in", values, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffNotIn(List<Integer> values) {
            addCriterion("alarm_takeoff not in", values, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffBetween(Integer value1, Integer value2) {
            addCriterion("alarm_takeoff between", value1, value2, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmTakeoffNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_takeoff not between", value1, value2, "alarmTakeoff");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryIsNull() {
            addCriterion("alarm_battery is null");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryIsNotNull() {
            addCriterion("alarm_battery is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryEqualTo(Integer value) {
            addCriterion("alarm_battery =", value, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryNotEqualTo(Integer value) {
            addCriterion("alarm_battery <>", value, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryGreaterThan(Integer value) {
            addCriterion("alarm_battery >", value, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_battery >=", value, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryLessThan(Integer value) {
            addCriterion("alarm_battery <", value, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_battery <=", value, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryIn(List<Integer> values) {
            addCriterion("alarm_battery in", values, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryNotIn(List<Integer> values) {
            addCriterion("alarm_battery not in", values, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryBetween(Integer value1, Integer value2) {
            addCriterion("alarm_battery between", value1, value2, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmBatteryNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_battery not between", value1, value2, "alarmBattery");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffIsNull() {
            addCriterion("alarm_poweroff is null");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffIsNotNull() {
            addCriterion("alarm_poweroff is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffEqualTo(Integer value) {
            addCriterion("alarm_poweroff =", value, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffNotEqualTo(Integer value) {
            addCriterion("alarm_poweroff <>", value, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffGreaterThan(Integer value) {
            addCriterion("alarm_poweroff >", value, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_poweroff >=", value, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffLessThan(Integer value) {
            addCriterion("alarm_poweroff <", value, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_poweroff <=", value, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffIn(List<Integer> values) {
            addCriterion("alarm_poweroff in", values, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffNotIn(List<Integer> values) {
            addCriterion("alarm_poweroff not in", values, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffBetween(Integer value1, Integer value2) {
            addCriterion("alarm_poweroff between", value1, value2, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andAlarmPoweroffNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_poweroff not between", value1, value2, "alarmPoweroff");
            return (Criteria) this;
        }

        public Criteria andListenSilentIsNull() {
            addCriterion("listen_silent is null");
            return (Criteria) this;
        }

        public Criteria andListenSilentIsNotNull() {
            addCriterion("listen_silent is not null");
            return (Criteria) this;
        }

        public Criteria andListenSilentEqualTo(Integer value) {
            addCriterion("listen_silent =", value, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentNotEqualTo(Integer value) {
            addCriterion("listen_silent <>", value, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentGreaterThan(Integer value) {
            addCriterion("listen_silent >", value, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentGreaterThanOrEqualTo(Integer value) {
            addCriterion("listen_silent >=", value, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentLessThan(Integer value) {
            addCriterion("listen_silent <", value, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentLessThanOrEqualTo(Integer value) {
            addCriterion("listen_silent <=", value, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentIn(List<Integer> values) {
            addCriterion("listen_silent in", values, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentNotIn(List<Integer> values) {
            addCriterion("listen_silent not in", values, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentBetween(Integer value1, Integer value2) {
            addCriterion("listen_silent between", value1, value2, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andListenSilentNotBetween(Integer value1, Integer value2) {
            addCriterion("listen_silent not between", value1, value2, "listenSilent");
            return (Criteria) this;
        }

        public Criteria andStepModeIsNull() {
            addCriterion("step_mode is null");
            return (Criteria) this;
        }

        public Criteria andStepModeIsNotNull() {
            addCriterion("step_mode is not null");
            return (Criteria) this;
        }

        public Criteria andStepModeEqualTo(Integer value) {
            addCriterion("step_mode =", value, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeNotEqualTo(Integer value) {
            addCriterion("step_mode <>", value, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeGreaterThan(Integer value) {
            addCriterion("step_mode >", value, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_mode >=", value, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeLessThan(Integer value) {
            addCriterion("step_mode <", value, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeLessThanOrEqualTo(Integer value) {
            addCriterion("step_mode <=", value, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeIn(List<Integer> values) {
            addCriterion("step_mode in", values, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeNotIn(List<Integer> values) {
            addCriterion("step_mode not in", values, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeBetween(Integer value1, Integer value2) {
            addCriterion("step_mode between", value1, value2, "stepMode");
            return (Criteria) this;
        }

        public Criteria andStepModeNotBetween(Integer value1, Integer value2) {
            addCriterion("step_mode not between", value1, value2, "stepMode");
            return (Criteria) this;
        }

        public Criteria andAlarmCallIsNull() {
            addCriterion("alarm_call is null");
            return (Criteria) this;
        }

        public Criteria andAlarmCallIsNotNull() {
            addCriterion("alarm_call is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmCallEqualTo(Integer value) {
            addCriterion("alarm_call =", value, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallNotEqualTo(Integer value) {
            addCriterion("alarm_call <>", value, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallGreaterThan(Integer value) {
            addCriterion("alarm_call >", value, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_call >=", value, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallLessThan(Integer value) {
            addCriterion("alarm_call <", value, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_call <=", value, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallIn(List<Integer> values) {
            addCriterion("alarm_call in", values, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallNotIn(List<Integer> values) {
            addCriterion("alarm_call not in", values, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallBetween(Integer value1, Integer value2) {
            addCriterion("alarm_call between", value1, value2, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andAlarmCallNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_call not between", value1, value2, "alarmCall");
            return (Criteria) this;
        }

        public Criteria andElectricizeIsNull() {
            addCriterion("electricize is null");
            return (Criteria) this;
        }

        public Criteria andElectricizeIsNotNull() {
            addCriterion("electricize is not null");
            return (Criteria) this;
        }

        public Criteria andElectricizeEqualTo(Integer value) {
            addCriterion("electricize =", value, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeNotEqualTo(Integer value) {
            addCriterion("electricize <>", value, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeGreaterThan(Integer value) {
            addCriterion("electricize >", value, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("electricize >=", value, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeLessThan(Integer value) {
            addCriterion("electricize <", value, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeLessThanOrEqualTo(Integer value) {
            addCriterion("electricize <=", value, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeIn(List<Integer> values) {
            addCriterion("electricize in", values, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeNotIn(List<Integer> values) {
            addCriterion("electricize not in", values, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeBetween(Integer value1, Integer value2) {
            addCriterion("electricize between", value1, value2, "electricize");
            return (Criteria) this;
        }

        public Criteria andElectricizeNotBetween(Integer value1, Integer value2) {
            addCriterion("electricize not between", value1, value2, "electricize");
            return (Criteria) this;
        }

        public Criteria andModeIsNull() {
            addCriterion("mode is null");
            return (Criteria) this;
        }

        public Criteria andModeIsNotNull() {
            addCriterion("mode is not null");
            return (Criteria) this;
        }

        public Criteria andModeEqualTo(Integer value) {
            addCriterion("mode =", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotEqualTo(Integer value) {
            addCriterion("mode <>", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThan(Integer value) {
            addCriterion("mode >", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mode >=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThan(Integer value) {
            addCriterion("mode <", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThanOrEqualTo(Integer value) {
            addCriterion("mode <=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeIn(List<Integer> values) {
            addCriterion("mode in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotIn(List<Integer> values) {
            addCriterion("mode not in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeBetween(Integer value1, Integer value2) {
            addCriterion("mode between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotBetween(Integer value1, Integer value2) {
            addCriterion("mode not between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andBellModeIsNull() {
            addCriterion("bell_mode is null");
            return (Criteria) this;
        }

        public Criteria andBellModeIsNotNull() {
            addCriterion("bell_mode is not null");
            return (Criteria) this;
        }

        public Criteria andBellModeEqualTo(Integer value) {
            addCriterion("bell_mode =", value, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeNotEqualTo(Integer value) {
            addCriterion("bell_mode <>", value, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeGreaterThan(Integer value) {
            addCriterion("bell_mode >", value, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bell_mode >=", value, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeLessThan(Integer value) {
            addCriterion("bell_mode <", value, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeLessThanOrEqualTo(Integer value) {
            addCriterion("bell_mode <=", value, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeIn(List<Integer> values) {
            addCriterion("bell_mode in", values, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeNotIn(List<Integer> values) {
            addCriterion("bell_mode not in", values, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeBetween(Integer value1, Integer value2) {
            addCriterion("bell_mode between", value1, value2, "bellMode");
            return (Criteria) this;
        }

        public Criteria andBellModeNotBetween(Integer value1, Integer value2) {
            addCriterion("bell_mode not between", value1, value2, "bellMode");
            return (Criteria) this;
        }

        public Criteria andSchoolidIsNull() {
            addCriterion("schoolId is null");
            return (Criteria) this;
        }

        public Criteria andSchoolidIsNotNull() {
            addCriterion("schoolId is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolidEqualTo(Integer value) {
            addCriterion("schoolId =", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidNotEqualTo(Integer value) {
            addCriterion("schoolId <>", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidGreaterThan(Integer value) {
            addCriterion("schoolId >", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidGreaterThanOrEqualTo(Integer value) {
            addCriterion("schoolId >=", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidLessThan(Integer value) {
            addCriterion("schoolId <", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidLessThanOrEqualTo(Integer value) {
            addCriterion("schoolId <=", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidIn(List<Integer> values) {
            addCriterion("schoolId in", values, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidNotIn(List<Integer> values) {
            addCriterion("schoolId not in", values, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidBetween(Integer value1, Integer value2) {
            addCriterion("schoolId between", value1, value2, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidNotBetween(Integer value1, Integer value2) {
            addCriterion("schoolId not between", value1, value2, "schoolid");
            return (Criteria) this;
        }

        public Criteria andLightTimeIsNull() {
            addCriterion("light_time is null");
            return (Criteria) this;
        }

        public Criteria andLightTimeIsNotNull() {
            addCriterion("light_time is not null");
            return (Criteria) this;
        }

        public Criteria andLightTimeEqualTo(Integer value) {
            addCriterion("light_time =", value, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeNotEqualTo(Integer value) {
            addCriterion("light_time <>", value, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeGreaterThan(Integer value) {
            addCriterion("light_time >", value, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("light_time >=", value, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeLessThan(Integer value) {
            addCriterion("light_time <", value, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeLessThanOrEqualTo(Integer value) {
            addCriterion("light_time <=", value, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeIn(List<Integer> values) {
            addCriterion("light_time in", values, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeNotIn(List<Integer> values) {
            addCriterion("light_time not in", values, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeBetween(Integer value1, Integer value2) {
            addCriterion("light_time between", value1, value2, "lightTime");
            return (Criteria) this;
        }

        public Criteria andLightTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("light_time not between", value1, value2, "lightTime");
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