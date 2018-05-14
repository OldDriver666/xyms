package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.AppInformation;
import com.fise.model.entity.AppInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppInformationMapper {
    long countByExample(AppInformationExample example);

    int deleteByExample(AppInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppInformation record);

    int insertSelective(AppInformation record);

    List<AppInformation> selectByExample(AppInformationExample example);

    AppInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppInformation record, @Param("example") AppInformationExample example);

    int updateByExample(@Param("record") AppInformation record, @Param("example") AppInformationExample example);

    int updateByPrimaryKeySelective(AppInformation record);

    int updateByPrimaryKey(AppInformation record);
    
    List<AppInformation> selectByPage(@Param("example") AppInformationExample example,@Param("page") Page<?> page);
    
    List<AppInformation> selectByIdList(@Param("example") AppInformationExample example);
}