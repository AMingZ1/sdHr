package com.sd.sdhr.mapper.sd.st;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.st.Tsdst09;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface Tsdst09DefinedMapper {


    List<Tsder03> getTsder03ForTalk(@Param("model") Tsder03 model);

    int updateEr03TalkStart(@Param("model") Tsder04 model);

}
