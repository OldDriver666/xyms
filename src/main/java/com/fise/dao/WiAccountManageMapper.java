package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.WiAccountManage;
import com.fise.model.entity.WiAccountManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WiAccountManageMapper {
    long countByExample(WiAccountManageExample example);

    int deleteByExample(WiAccountManageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WiAccountManage record);

    int insertSelective(WiAccountManage record);

    List<WiAccountManage> selectByExample(WiAccountManageExample example);

    WiAccountManage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WiAccountManage record, @Param("example") WiAccountManageExample example);

    int updateByExample(@Param("record") WiAccountManage record, @Param("example") WiAccountManageExample example);

    int updateByPrimaryKeySelective(WiAccountManage record);

    int updateByPrimaryKey(WiAccountManage record);
    
    List<WiAccountManage> selectByPage(@Param("example") WiAccountManageExample example,@Param("page")Page<WiAccountManage> page);
}