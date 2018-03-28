package com.fise.dao;

import com.fise.model.entity.WiPermission;
import com.fise.model.entity.WiPermissionExample;
import com.fise.model.result.ModulePermissResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WiPermissionMapper {
    long countByExample(WiPermissionExample example);

    int deleteByExample(WiPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WiPermission record);

    int insertSelective(WiPermission record);

    List<WiPermission> selectByExample(WiPermissionExample example);

    WiPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WiPermission record, @Param("example") WiPermissionExample example);

    int updateByExample(@Param("record") WiPermission record, @Param("example") WiPermissionExample example);

    int updateByPrimaryKeySelective(WiPermission record);

    int updateByPrimaryKey(WiPermission record);
    
    List<ModulePermissResult> selectAuthByRole(@Param("company") Integer company, @Param("role") Integer role, @Param("parent")Integer parent, @Param("needall")Integer needall);
    
    List<ModulePermissResult> selectAuthByName(@Param("company") Integer company, @Param("role") Integer role, @Param("name")String name);
}