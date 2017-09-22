package com.fise.dao;

import com.fise.model.entity.WiDepartment;
import com.fise.model.entity.WiDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WiDepartmentMapper {
    long countByExample(WiDepartmentExample example);

    int deleteByExample(WiDepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WiDepartment record);

    int insertSelective(WiDepartment record);

    List<WiDepartment> selectByExample(WiDepartmentExample example);

    WiDepartment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WiDepartment record, @Param("example") WiDepartmentExample example);

    int updateByExample(@Param("record") WiDepartment record, @Param("example") WiDepartmentExample example);

    int updateByPrimaryKeySelective(WiDepartment record);

    int updateByPrimaryKey(WiDepartment record);
}