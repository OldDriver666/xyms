package com.fise.dao;

import com.fise.model.entity.MyComment;
import com.fise.model.entity.MyCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyCommentMapper {
    long countByExample(MyCommentExample example);

    int deleteByExample(MyCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyComment record);

    int insertSelective(MyComment record);

    List<MyComment> selectByExample(MyCommentExample example);

    MyComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyComment record, @Param("example") MyCommentExample example);

    int updateByExample(@Param("record") MyComment record, @Param("example") MyCommentExample example);

    int updateByPrimaryKeySelective(MyComment record);

    int updateByPrimaryKey(MyComment record);
}