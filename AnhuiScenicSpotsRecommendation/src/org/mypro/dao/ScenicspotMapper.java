package org.mypro.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mypro.entity.Scenicspot;
import org.mypro.entity.ScenicspotExample;

public interface ScenicspotMapper {
    long countByExample(ScenicspotExample example);

    int deleteByExample(ScenicspotExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Scenicspot record);

    int insertSelective(Scenicspot record);

    List<Scenicspot> selectByExample(ScenicspotExample example);

    Scenicspot selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Scenicspot record, @Param("example") ScenicspotExample example);

    int updateByExample(@Param("record") Scenicspot record, @Param("example") ScenicspotExample example);

    int updateByPrimaryKeySelective(Scenicspot record);

    int updateByPrimaryKey(Scenicspot record);
}