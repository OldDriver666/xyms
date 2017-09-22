package com.fise.dao;

import com.fise.model.entity.WiModule;
import com.fise.model.entity.WiModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WiModuleMapper {
    long countByExample(WiModuleExample example);

    int deleteByExample(WiModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WiModule record);

    int insertSelective(WiModule record);

    List<WiModule> selectByExample(WiModuleExample example);

    WiModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WiModule record, @Param("example") WiModuleExample example);

    int updateByExample(@Param("record") WiModule record, @Param("example") WiModuleExample example);

    int updateByPrimaryKeySelective(WiModule record);

    int updateByPrimaryKey(WiModule record);
}