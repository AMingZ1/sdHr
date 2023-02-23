package com.sd.sdhr.mapper.sd.of;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.of.Tsdof01;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsdof01Mapper extends BaseMapper<Tsdof01> {

    @Select("select ifnull(right(max(OFFER_NO),4),0) row from tsdof01  where OFFER_NO like '%'||#{offerNo}||'%' ")
    int queryCountByOfferNoLike(@Param("offerNo") String offerNo);
}
