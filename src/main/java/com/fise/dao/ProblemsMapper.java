package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.Problems;
import com.fise.model.entity.ProblemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProblemsMapper {
    long countByExample(ProblemsExample example);

    int deleteByExample(ProblemsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Problems record);

    int insertSelective(Problems record);

    List<Problems> selectByExample(ProblemsExample example);

    Problems selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Problems record, @Param("example") ProblemsExample example);

    int updateByExample(@Param("record") Problems record, @Param("example") ProblemsExample example);

    int updateByPrimaryKeySelective(Problems record);

    int updateByPrimaryKey(Problems record);
    
    List<Problems> selectBypage(@Param("example") ProblemsExample example,@Param("page") Page<Problems> page);
    
    List<Problems> querytitle(@Param("example") ProblemsExample example,@Param("title") String title);
    
    List<Problems> querytitlebypage(@Param("example") ProblemsExample example,@Param("page") Page<Problems> page);
}