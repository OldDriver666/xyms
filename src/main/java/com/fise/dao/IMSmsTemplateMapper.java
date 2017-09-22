package com.fise.dao;

import com.fise.model.entity.IMSmsTemplate;
import com.fise.model.entity.IMSmsTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMSmsTemplateMapper {
    long countByExample(IMSmsTemplateExample example);

    int deleteByExample(IMSmsTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMSmsTemplate record);

    int insertSelective(IMSmsTemplate record);

    List<IMSmsTemplate> selectByExample(IMSmsTemplateExample example);

    IMSmsTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMSmsTemplate record, @Param("example") IMSmsTemplateExample example);

    int updateByExample(@Param("record") IMSmsTemplate record, @Param("example") IMSmsTemplateExample example);

    int updateByPrimaryKeySelective(IMSmsTemplate record);

    int updateByPrimaryKey(IMSmsTemplate record);
}