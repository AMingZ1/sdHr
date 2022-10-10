package com.sd.sdhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.Tsdhr01;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsdhr01Mapper extends BaseMapper<Tsdhr01> {

    @Select("select * from tsdhr01  where REQ_NO=#{reqNo}")
    Tsdhr01 queryTsdhr01ByReqNo(@Param("reqNo") String reqNo);
}
