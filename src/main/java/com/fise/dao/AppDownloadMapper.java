package com.fise.dao;

import com.fise.model.entity.AppDownload;
import com.fise.model.entity.AppDownloadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppDownloadMapper {
    long countByExample(AppDownloadExample example);

    int deleteByExample(AppDownloadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppDownload record);
    
    int addListAppDownload(List<AppDownload> records);

    int insertSelective(AppDownload record);

    List<AppDownload> selectByExample(AppDownloadExample example);

    AppDownload selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppDownload record, @Param("example") AppDownloadExample example);

    int updateByExample(@Param("record") AppDownload record, @Param("example") AppDownloadExample example);

    int updateByPrimaryKeySelective(AppDownload record);

    int updateByPrimaryKey(AppDownload record);
}