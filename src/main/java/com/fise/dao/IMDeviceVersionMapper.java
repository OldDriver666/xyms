
package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.IMDeviceVersion;
import com.fise.model.entity.IMDeviceVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMDeviceVersionMapper {
    long countByExample(IMDeviceVersionExample example);

    int deleteByExample(IMDeviceVersionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMDeviceVersion record);

    int insertSelective(IMDeviceVersion record);

    List<IMDeviceVersion> selectByExample(IMDeviceVersionExample example);

    IMDeviceVersion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMDeviceVersion record, @Param("example") IMDeviceVersionExample example);

    int updateByExample(@Param("record") IMDeviceVersion record, @Param("example") IMDeviceVersionExample example);

    int updateByPrimaryKeySelective(IMDeviceVersion record);

    int updateByPrimaryKey(IMDeviceVersion record);

    List<IMDeviceVersion> selectByPage(@Param("example") IMDeviceVersionExample example,@Param("page")Page<?>page);
}