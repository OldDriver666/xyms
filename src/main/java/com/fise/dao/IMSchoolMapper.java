package com.fise.dao;

import com.fise.model.entity.IMSchool;
import com.fise.model.entity.IMSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMSchoolMapper {
    long countByExample(IMSchoolExample example);

    int deleteByExample(IMSchoolExample example);

    int deleteByPrimaryKey(Integer schoolid);

    int insert(IMSchool record);

    int insertSelective(IMSchool record);

    List<IMSchool> selectByExample(IMSchoolExample example);

    IMSchool selectByPrimaryKey(Integer schoolid);

    int updateByExampleSelective(@Param("record") IMSchool record, @Param("example") IMSchoolExample example);

    int updateByExample(@Param("record") IMSchool record, @Param("example") IMSchoolExample example);

    int updateByPrimaryKeySelective(IMSchool record);

    int updateByPrimaryKey(IMSchool record);
}