package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.IMSuggest;
import com.fise.model.entity.IMSuggestExample;
import com.fise.model.param.SuggestParam;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMSuggestMapper {
    long countByExample(IMSuggestExample example);

    int deleteByExample(IMSuggestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMSuggest record);

    int insertSelective(IMSuggest record);

    List<IMSuggest> selectByExample(IMSuggestExample example);

    IMSuggest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMSuggest record, @Param("example") IMSuggestExample example);

    int updateByExample(@Param("record") IMSuggest record, @Param("example") IMSuggestExample example);

    int updateByPrimaryKeySelective(IMSuggest record);

    int updateByPrimaryKey(IMSuggest record);
    
    List<IMSuggest> selectByExamplebypage(@Param("example") IMSuggestExample example,@Param("page") Page<SuggestParam> page);
}