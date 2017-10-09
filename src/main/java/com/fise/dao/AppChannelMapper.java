package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.AppChannel;
import com.fise.model.entity.AppChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppChannelMapper {
    long countByExample(AppChannelExample example);

    int deleteByExample(AppChannelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppChannel record);

    int insertSelective(AppChannel record);

    List<AppChannel> selectByExample(AppChannelExample example);

    AppChannel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppChannel record, @Param("example") AppChannelExample example);

    int updateByExample(@Param("record") AppChannel record, @Param("example") AppChannelExample example);

    int updateByPrimaryKeySelective(AppChannel record);

    int updateByPrimaryKey(AppChannel record);
    
    List<AppChannel> selectByPage(@Param("example") AppChannelExample example,@Param("page") Page<AppChannel> page);
}