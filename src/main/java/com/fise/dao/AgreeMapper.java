package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.Agree;
import com.fise.model.entity.AgreeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgreeMapper {
    long countByExample(AgreeExample example);

    int deleteByExample(AgreeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Agree record);

    int insertSelective(Agree record);

    List<Agree> selectByExample(AgreeExample example);

    Agree selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Agree record, @Param("example") AgreeExample example);

    int updateByExample(@Param("record") Agree record, @Param("example") AgreeExample example);

    int updateByPrimaryKeySelective(Agree record);

    int updateByPrimaryKey(Agree record);
    
    List<Agree> selectBypage(@Param("example") AgreeExample example,@Param("page") Page<Agree> page);
}