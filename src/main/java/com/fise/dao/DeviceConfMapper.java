package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.DeviceConf;
import com.fise.model.entity.DeviceConfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceConfMapper {
    long countByExample(DeviceConfExample example);

    int deleteByExample(DeviceConfExample example);

    int deleteByPrimaryKey(Integer deviceId);

    int insert(DeviceConf record);

    int insertSelective(DeviceConf record);

    List<DeviceConf> selectByExample(DeviceConfExample example);

    DeviceConf selectByPrimaryKey(Integer deviceId);

    int updateByExampleSelective(@Param("record") DeviceConf record, @Param("example") DeviceConfExample example);

    int updateByExample(@Param("record") DeviceConf record, @Param("example") DeviceConfExample example);

    int updateByPrimaryKeySelective(DeviceConf record);

    int updateByPrimaryKey(DeviceConf record);
    
    List<DeviceConf> selectByPage(@Param("example") DeviceConfExample example,@Param("page") Page<DeviceConf> page);
}