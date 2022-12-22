package com.sd.sdhr.mapper.sd.st;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.st.Tsdst06;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsdst06Mapper extends BaseMapper<Tsdst06> {

    @Select("select ifnull(right(max(TASK_ID),4),0) row from tsdST06  where TASK_ID like '%'||#{memberId}||'%' ")
    int queryCountByMemberIdLike(@Param("memberId") String memberId);
}
