package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.AppChannelList;
import com.fise.model.entity.AppChannelListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppChannelListMapper {
    long countByExample(AppChannelListExample example);

    int deleteByExample(AppChannelListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppChannelList record);

    int insertSelective(AppChannelList record);

    List<AppChannelList> selectByExample(AppChannelListExample example);

    AppChannelList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppChannelList record, @Param("example") AppChannelListExample example);

    int updateByExample(@Param("record") AppChannelList record, @Param("example") AppChannelListExample example);

    int updateByPrimaryKeySelective(AppChannelList record);

    int updateByPrimaryKey(AppChannelList record);
    
    List<AppChannelList> selectByPage(@Param("example") AppChannelListExample example,@Param("page") Page<AppChannelList> page);

}