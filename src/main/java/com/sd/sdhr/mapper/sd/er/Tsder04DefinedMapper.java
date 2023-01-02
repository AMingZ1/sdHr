package com.sd.sdhr.mapper.sd.er;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface Tsder04DefinedMapper {

    List<Map> getTsder04ForOffica(@Param("year") String year);
}
