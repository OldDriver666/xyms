package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.ExcelFiseDevice;
import com.fise.model.entity.FiseDevice;
import com.fise.model.entity.FiseDeviceExample;
import com.fise.model.param.QueryFiseDeviceParam;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FiseDeviceMapper {
    long countByExample(FiseDeviceExample example);

    int deleteByExample(FiseDeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FiseDevice record);

    int insertSelective(FiseDevice record);

    List<FiseDevice> selectByExample(FiseDeviceExample example);

    FiseDevice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FiseDevice record, @Param("example") FiseDeviceExample example);

    int updateByExample(@Param("record") FiseDevice record, @Param("example") FiseDeviceExample example);

    int updateByPrimaryKeySelective(FiseDevice record);

    int updateByPrimaryKey(FiseDevice record);
    
    List<FiseDevice> selectByPage(@Param("example") FiseDeviceExample example,@Param("page") Page<QueryFiseDeviceParam> page);
    
    int excelImport(List<ExcelFiseDevice> list);
}