package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.Answer;
import com.fise.model.entity.AnswerExample;
import com.fise.model.entity.Problems;
import com.fise.model.entity.ProblemsExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerMapper {
    long countByExample(AnswerExample example);

    int deleteByExample(AnswerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    List<Answer> selectByExample(AnswerExample example);

    Answer selectByPrimaryKey(Integer id);
    
    List<Answer> selectBypage(@Param("example") AnswerExample example,@Param("page") Page<Answer> page);

    int updateByExampleSelective(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExample(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}