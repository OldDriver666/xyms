package com.fise.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fise.base.Page;
import com.fise.model.entity.Complaints;
import com.fise.model.entity.ComplaintsExample;

public interface ComplaintsMapper {
    long countByExample(ComplaintsExample example);

    int deleteByExample(ComplaintsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Complaints record);

    int insertSelective(Complaints record);

    List<Complaints> selectByExample(ComplaintsExample example);
    
    List<Complaints> selectByExampleByPage(@Param("example") ComplaintsExample example,@Param("page") Page<Complaints> page);

    Complaints selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Complaints record, @Param("example") ComplaintsExample example);

    int updateByExample(@Param("record") Complaints record, @Param("example") ComplaintsExample example);

    int updateByPrimaryKeySelective(Complaints record);

    int updateByPrimaryKey(Complaints record);
}