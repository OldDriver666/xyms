package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.Concern;
import com.fise.model.entity.ConcernExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConcernMapper {
    long countByExample(ConcernExample example);

    int deleteByExample(ConcernExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Concern record);

    int insertSelective(Concern record);

    List<Concern> selectByExample(ConcernExample example);

    Concern selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Concern record, @Param("example") ConcernExample example);

    int updateByExample(@Param("record") Concern record, @Param("example") ConcernExample example);

    int updateByPrimaryKeySelective(Concern record);

    int updateByPrimaryKey(Concern record);
    
    List<Concern> selectByPage(@Param("example") ConcernExample example,@Param("page") Page<Concern> page);
}