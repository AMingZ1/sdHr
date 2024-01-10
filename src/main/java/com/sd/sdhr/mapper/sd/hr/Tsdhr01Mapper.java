package com.sd.sdhr.mapper.sd.hr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsdhr01Mapper extends BaseMapper<Tsdhr01> {

    @Select("select * from tsdhr01  where REQ_NO=#{reqNo}")
    Tsdhr01 queryTsdhr01ByReqNo(@Param("reqNo") String reqNo);

    @Select("select ifnull(right(max(REQ_NO),4),0) row from tsdhr01  where REQ_NO like '%'||#{reqNo}||'%' ")
    int queryCountByReqNoLike(@Param("reqNo") String reqNo);


}
