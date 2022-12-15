package com.sd.sdhr.mapper.sd.st;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.st.Tsdst09;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Tsdst09Mapper extends BaseMapper<Tsdst09> {

    @Select("select * from tsder03 er03 where where er03.MEMBER_TYPE = #{model.memberType}  and er03 .TALK_PLAN_NOW = #{model.memberType}  ")
    List<Tsder03> queryCountByMemberIdLike(@Param("model") Tsder03 model);

}
