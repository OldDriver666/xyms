package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.IMSystemConf;
import com.fise.model.entity.IMSystemConfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMSystemConfMapper {
    long countByExample(IMSystemConfExample example);

    int deleteByExample(IMSystemConfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMSystemConf record);

    int insertSelective(IMSystemConf record);

    List<IMSystemConf> selectByExample(IMSystemConfExample example);

    IMSystemConf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMSystemConf record, @Param("example") IMSystemConfExample example);

    int updateByExample(@Param("record") IMSystemConf record, @Param("example") IMSystemConfExample example);

    int updateByPrimaryKeySelective(IMSystemConf record);

    int updateByPrimaryKey(IMSystemConf record);
    
    List<IMSystemConf> selectByExampleByPage(@Param("example") IMSystemConfExample example,@Param("page") Page<IMSystemConf> page);

}