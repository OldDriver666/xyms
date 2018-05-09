package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.IMClientType;
import com.fise.model.entity.IMClientTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMClientTypeMapper {
    long countByExample(IMClientTypeExample example);

    int deleteByExample(IMClientTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMClientType record);

    int insertSelective(IMClientType record);

    List<IMClientType> selectByExample(IMClientTypeExample example);

    IMClientType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMClientType record, @Param("example") IMClientTypeExample example);

    int updateByExample(@Param("record") IMClientType record, @Param("example") IMClientTypeExample example);

    int updateByPrimaryKeySelective(IMClientType record);

    int updateByPrimaryKey(IMClientType record);
    
    List<IMClientType> selectByPage(@Param("example")IMClientTypeExample example,@Param("page")Page<?> page);
}