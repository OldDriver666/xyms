package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.WiOrganization;
import com.fise.model.entity.WiOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WiOrganizationMapper {
    long countByExample(WiOrganizationExample example);

    int deleteByExample(WiOrganizationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WiOrganization record);

    int insertSelective(WiOrganization record);

    List<WiOrganization> selectByExample(WiOrganizationExample example);

    WiOrganization selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WiOrganization record, @Param("example") WiOrganizationExample example);

    int updateByExample(@Param("record") WiOrganization record, @Param("example") WiOrganizationExample example);

    int updateByPrimaryKeySelective(WiOrganization record);

    int updateByPrimaryKey(WiOrganization record);
    
    List<WiOrganization> selectByPage(@Param("example") WiOrganizationExample example,@Param("page")Page<?>page);
}