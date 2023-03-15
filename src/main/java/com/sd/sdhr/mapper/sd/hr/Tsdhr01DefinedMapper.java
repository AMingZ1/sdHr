package com.sd.sdhr.mapper.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Request;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Title: Tsdhr01DefinedMapper
 * @Author dems
 * @Package com.sd.sdhr.mapper.sd.hr
 * @Date 2023/3/9 16:06
 * @description: ${description}
 */
@Mapper
public interface Tsdhr01DefinedMapper {

    List<Tsdhr01> queryExportByHr01(Tsdhr01Request tsdhr01Request);
}
