package com.fise.dao;

import com.fise.model.entity.DeviceCrontab;
import com.fise.model.entity.DeviceCrontabExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceCrontabMapper {
    long countByExample(DeviceCrontabExample example);

    int deleteByExample(DeviceCrontabExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(DeviceCrontab record);

    int insertSelective(DeviceCrontab record);

    List<DeviceCrontab> selectByExample(DeviceCrontabExample example);

    DeviceCrontab selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") DeviceCrontab record, @Param("example") DeviceCrontabExample example);

    int updateByExample(@Param("record") DeviceCrontab record, @Param("example") DeviceCrontabExample example);

    int updateByPrimaryKeySelective(DeviceCrontab record);

    int updateByPrimaryKey(DeviceCrontab record);
}