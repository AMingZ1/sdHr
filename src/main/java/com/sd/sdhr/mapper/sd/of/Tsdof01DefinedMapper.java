package com.sd.sdhr.mapper.sd.of;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface Tsdof01DefinedMapper {

    List<Map> getTsdof01ForOffica(@Param("year") String year);
}
