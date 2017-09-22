package com.fise.dao;

import com.fise.model.entity.WiMessageType;
import com.fise.model.entity.WiMessageTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WiMessageTypeMapper {
    long countByExample(WiMessageTypeExample example);

    int deleteByExample(WiMessageTypeExample example);

    int deleteByPrimaryKey(Integer msgType);

    int insert(WiMessageType record);

    int insertSelective(WiMessageType record);

    List<WiMessageType> selectByExample(WiMessageTypeExample example);

    WiMessageType selectByPrimaryKey(Integer msgType);

    int updateByExampleSelective(@Param("record") WiMessageType record, @Param("example") WiMessageTypeExample example);

    int updateByExample(@Param("record") WiMessageType record, @Param("example") WiMessageTypeExample example);

    int updateByPrimaryKeySelective(WiMessageType record);

    int updateByPrimaryKey(WiMessageType record);
}