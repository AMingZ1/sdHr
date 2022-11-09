package com.sd.sdhr.mapper.sd.er;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.er.Tsder02;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsder02Mapper extends BaseMapper<Tsder02> {

    @Select("select ifnull(right(max(MP_RELATION_NO),4),0) row from tsder02  where MP_RELATION_NO like '%'||#{mpRelationNo}||'%' ")
    int queryCountByMpRelationNoLike(@Param("mpRelationNo") String mpRelationNo);
}
