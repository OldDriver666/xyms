package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.AppSplash;
import com.fise.model.entity.AppSplashExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppSplashMapper {
    long countByExample(AppSplashExample example);

    int deleteByExample(AppSplashExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppSplash record);

    int insertSelective(AppSplash record);

    List<AppSplash> selectByExample(AppSplashExample example);

    AppSplash selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppSplash record, @Param("example") AppSplashExample example);

    int updateByExample(@Param("record") AppSplash record, @Param("example") AppSplashExample example);

    int updateByPrimaryKeySelective(AppSplash record);

    int updateByPrimaryKey(AppSplash record);
    
    List<AppSplash> selectByPage(@Param("example") AppSplashExample example,@Param("page") Page<AppSplash> page);
}