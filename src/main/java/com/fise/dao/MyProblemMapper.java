package com.fise.dao;

import com.fise.model.entity.MyProblem;
import com.fise.model.entity.MyProblemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyProblemMapper {
    long countByExample(MyProblemExample example);

    int deleteByExample(MyProblemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyProblem record);

    int insertSelective(MyProblem record);

    List<MyProblem> selectByExample(MyProblemExample example);

    MyProblem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyProblem record, @Param("example") MyProblemExample example);

    int updateByExample(@Param("record") MyProblem record, @Param("example") MyProblemExample example);

    int updateByPrimaryKeySelective(MyProblem record);

    int updateByPrimaryKey(MyProblem record);
}