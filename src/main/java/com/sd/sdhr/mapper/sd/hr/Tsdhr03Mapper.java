package com.sd.sdhr.mapper.sd.hr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsdhr03Mapper extends BaseMapper<Tsdhr03> {

    @Select("select ifnull(right(max(MEMBER_NO),4),0) row from tsdhr03  where MEMBER_NO like '%'||#{memberNo}||'%' ")
    int queryCountByMemberNoLike(@Param("memberNo") String memberNo);
}
