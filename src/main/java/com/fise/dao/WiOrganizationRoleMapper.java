package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.WiOrganizationRole;
import com.fise.model.entity.WiOrganizationRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WiOrganizationRoleMapper {
    long countByExample(WiOrganizationRoleExample example);

    int deleteByExample(WiOrganizationRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WiOrganizationRole record);

    int insertSelective(WiOrganizationRole record);

    List<WiOrganizationRole> selectByExample(WiOrganizationRoleExample example);

    WiOrganizationRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WiOrganizationRole record, @Param("example") WiOrganizationRoleExample example);

    int updateByExample(@Param("record") WiOrganizationRole record, @Param("example") WiOrganizationRoleExample example);

    int updateByPrimaryKeySelective(WiOrganizationRole record);

    int updateByPrimaryKey(WiOrganizationRole record);
    
    List<WiOrganizationRole> selectByExampleByPage(@Param("example") WiOrganizationRoleExample example,@Param("page") Page<WiOrganizationRole> page);

}