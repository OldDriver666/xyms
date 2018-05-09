package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.IMDepartConfig;
import com.fise.model.entity.IMDepartConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMDepartConfigMapper {
    long countByExample(IMDepartConfigExample example);

    int deleteByExample(IMDepartConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMDepartConfig record);

    int insertSelective(IMDepartConfig record);

    List<IMDepartConfig> selectByExample(IMDepartConfigExample example);

    IMDepartConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMDepartConfig record, @Param("example") IMDepartConfigExample example);

    int updateByExample(@Param("record") IMDepartConfig record, @Param("example") IMDepartConfigExample example);

    int updateByPrimaryKeySelective(IMDepartConfig record);

    int updateByPrimaryKey(IMDepartConfig record);
    
    List<IMDepartConfig> selectByPage(@Param("example")IMDepartConfigExample example,@Param("page")Page<?>page);
}