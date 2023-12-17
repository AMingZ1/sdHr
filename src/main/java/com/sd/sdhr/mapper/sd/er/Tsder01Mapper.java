package com.sd.sdhr.mapper.sd.er;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.er.Tsder01;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsder01Mapper extends BaseMapper<Tsder01> {

    @Select("select ifnull(right(max(MEMBER_ID),5),0) row from tsder01  where MEMBER_ID like #{memberId}||'%' ")
    int queryCountByMemberIdLike(@Param("memberId") String memberId);


    @Select("select ifnull(right(max(MEMBER_ID),5),0) row from tsder01  where MEMBER_ID like 'DSSH%' ")
    int queryCountByMemberIdLike2(@Param("memberId") String memberId);
}
