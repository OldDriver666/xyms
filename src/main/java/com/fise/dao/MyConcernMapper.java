package com.fise.dao;

import com.fise.model.entity.MyConcern;
import com.fise.model.entity.MyConcernExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyConcernMapper {
    long countByExample(MyConcernExample example);

    int deleteByExample(MyConcernExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyConcern record);

    int insertSelective(MyConcern record);

    List<MyConcern> selectByExample(MyConcernExample example);

    MyConcern selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyConcern record, @Param("example") MyConcernExample example);

    int updateByExample(@Param("record") MyConcern record, @Param("example") MyConcernExample example);

    int updateByPrimaryKeySelective(MyConcern record);

    int updateByPrimaryKey(MyConcern record);
}