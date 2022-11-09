package com.sd.sdhr.mapper.sd.er;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.Tsder04;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tsder04Mapper extends BaseMapper<Tsder04> {

    @Select("select ifnull(right(max(TALK_NO),4),0) row from tsder04  where MEMBER_ID=#{memberId} AND TALK_TYPE=#{talkType} AND TALK_NO like '%'||#{talkNo}||'%' ")
    int queryCountByTalkNoLike(@Param("memberId") String memberId,@Param("talkType") String talkType,@Param("talkNo") String talkNo);
}
