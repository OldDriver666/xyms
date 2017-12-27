package com.fise.dao;

import com.fise.model.entity.DeviceControl;
import com.fise.model.entity.DeviceControlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceControlMapper {
    long countByExample(DeviceControlExample example);

    int deleteByExample(DeviceControlExample example);

    int insert(DeviceControl record);

    int insertSelective(DeviceControl record);

    List<DeviceControl> selectByExample(DeviceControlExample example);

    int updateByExampleSelective(@Param("record") DeviceControl record, @Param("example") DeviceControlExample example);

    int updateByExample(@Param("record") DeviceControl record, @Param("example") DeviceControlExample example);
}