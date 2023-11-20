package com.sd.sdhr.mapper.sd.st;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.sd.st.Tsdst03;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface Tsdst03DefinedMapper extends BaseMapper<Tsdst03> {

    List<Map>  selectTsdst03ByMaxVerNo(@Param("model") Tsdst03 model);
}
