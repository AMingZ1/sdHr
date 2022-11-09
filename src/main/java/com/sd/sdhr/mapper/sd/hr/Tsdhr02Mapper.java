package com.sd.sdhr.mapper.sd.hr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsdhr02Mapper extends BaseMapper<Tsdhr02> {

    @Select("select ifnull(right(max(PLAN_NO),4),0) row from tsdhr02  where PLAN_NO like '%'||#{planNO}||'%' ")
    int queryCountByPlanNoLike(@Param("planNO") String planNO);
}
