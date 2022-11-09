package com.sd.sdhr.service.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Tsdhr01Service {

    //获得所有的岗位需求信息
    List<Tsdhr01> getAllTsdhr01();

    //通过岗位需求号获取对应的岗位需求信息
    Tsdhr01 getTsdhr01ByReqNo(String reqNo);

    // 新增一个岗位需求信息
    EiINfo saveTsdhr01(Tsdhr01 tsdhr01);

    // 修改一个岗位需求信息
    int updateTsdhr01ByReqNo(Tsdhr01 tsdhr01);

    //新测试》通过岗位需求号获取对应的岗位需求信息
    Tsdhr01 queryTsdhr01ByReqNo(String reqNo);

    //获得所有的岗位需求信息
    EiINfo getAllTsdhr01Test(Tsdhr01 tsdhr01);

    EiINfo deleteTsdhr01ByMap(Tsdhr01 tsdhr01);

    // 修改一个岗位需求信息
    EiINfo updateTsdhr01(Tsdhr01 tsdhr01);
}
