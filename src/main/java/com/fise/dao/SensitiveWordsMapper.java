package com.fise.dao;

import com.fise.base.Page;
import com.fise.model.entity.SensitiveWords;
import com.fise.model.entity.SensitiveWordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SensitiveWordsMapper {
    long countByExample(SensitiveWordsExample example);

    int deleteByExample(SensitiveWordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SensitiveWords record);

    int insertSelective(SensitiveWords record);

    List<SensitiveWords> selectByExample(SensitiveWordsExample example);

    SensitiveWords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SensitiveWords record, @Param("example") SensitiveWordsExample example);

    int updateByExample(@Param("record") SensitiveWords record, @Param("example") SensitiveWordsExample example);

    int updateByPrimaryKeySelective(SensitiveWords record);

    int updateByPrimaryKey(SensitiveWords record);
    
    List<SensitiveWords> selectByPage(@Param("example") SensitiveWordsExample example,@Param("page") Page<SensitiveWords> page);
}