package com.fise.dao;

import com.fise.model.entity.IMRelationShip;
import com.fise.model.entity.IMRelationShipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMRelationShipMapper {
    long countByExample(IMRelationShipExample example);

    int deleteByExample(IMRelationShipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IMRelationShip record);

    int insertSelective(IMRelationShip record);

    List<IMRelationShip> selectByExample(IMRelationShipExample example);

    IMRelationShip selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IMRelationShip record, @Param("example") IMRelationShipExample example);

    int updateByExample(@Param("record") IMRelationShip record, @Param("example") IMRelationShipExample example);

    int updateByPrimaryKeySelective(IMRelationShip record);

    int updateByPrimaryKey(IMRelationShip record);
    
    List<Integer> findrelation(Integer id);
}