package com.fise.dao;

import com.fise.model.entity.IMMark;
import com.fise.model.entity.IMMarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMMarkMapper {
    long countByExample(IMMarkExample example);

    int deleteByExample(IMMarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMMark record);

    int insertSelective(IMMark record);

    List<IMMark> selectByExample(IMMarkExample example);

    IMMark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMMark record, @Param("example") IMMarkExample example);

    int updateByExample(@Param("record") IMMark record, @Param("example") IMMarkExample example);

    int updateByPrimaryKeySelective(IMMark record);

    int updateByPrimaryKey(IMMark record);
}