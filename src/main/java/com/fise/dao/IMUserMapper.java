package com.fise.dao;

import com.fise.model.entity.IMUser;
import com.fise.model.entity.IMUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMUserMapper {
    long countByExample(IMUserExample example);

    int deleteByExample(IMUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMUser record);

    int insertSelective(IMUser record);

    List<IMUser> selectByExample(IMUserExample example);

    IMUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMUser record, @Param("example") IMUserExample example);

    int updateByExample(@Param("record") IMUser record, @Param("example") IMUserExample example);

    int updateByPrimaryKeySelective(IMUser record);

    int updateByPrimaryKey(IMUser record);
}