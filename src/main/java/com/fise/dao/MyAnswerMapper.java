package com.fise.dao;

import com.fise.model.entity.MyAnswer;
import com.fise.model.entity.MyAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyAnswerMapper {
    long countByExample(MyAnswerExample example);

    int deleteByExample(MyAnswerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyAnswer record);

    int insertSelective(MyAnswer record);

    List<MyAnswer> selectByExample(MyAnswerExample example);

    MyAnswer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyAnswer record, @Param("example") MyAnswerExample example);

    int updateByExample(@Param("record") MyAnswer record, @Param("example") MyAnswerExample example);

    int updateByPrimaryKeySelective(MyAnswer record);

    int updateByPrimaryKey(MyAnswer record);
}