package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.AppAdvert;
import com.fise.model.entity.AppAdvertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppAdvertMapper {
    long countByExample(AppAdvertExample example);

    int deleteByExample(AppAdvertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppAdvert record);

    int insertSelective(AppAdvert record);

    List<AppAdvert> selectByExample(AppAdvertExample example);

    AppAdvert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppAdvert record, @Param("example") AppAdvertExample example);

    int updateByExample(@Param("record") AppAdvert record, @Param("example") AppAdvertExample example);

    int updateByPrimaryKeySelective(AppAdvert record);

    int updateByPrimaryKey(AppAdvert record);
    
    List<AppAdvert> selectByPage(@Param("example") AppAdvertExample example,@Param("page") Page<AppAdvert> page);
}