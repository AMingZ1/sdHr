package com.sd.sdhr.mapper.sd.er;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsder03Mapper extends BaseMapper<Tsder03> {

    @Select("select ifnull(right(max(MEMBER_ID),4),0) row from tsder03  where MEMBER_ID like '%'||#{memberId}||'%' ")
    int queryCountByMemberIdLike(@Param("memberId") String memberId);
}
