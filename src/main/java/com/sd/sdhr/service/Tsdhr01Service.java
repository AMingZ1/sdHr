package com.sd.sdhr.service;

import com.sd.sdhr.pojo.Tsdhr01;

import java.util.List;

public interface Tsdhr01Service {

    //获得所有的岗位需求信息
    List<Tsdhr01> getAllTsdhr01();

    //通过岗位需求号获取对应的岗位需求信息
    Tsdhr01 getTsdhr01ByReqNo(String reqNo);

    // 新增一个岗位需求信息
    int saveTsdhr01(Tsdhr01 tsdhr01);

    // 修改一个岗位需求信息
    int updateTsdhr01ByReqNo(Tsdhr01 tsdhr01);

    //新测试》通过岗位需求号获取对应的岗位需求信息
    Tsdhr01 queryTsdhr01ByReqNo(String reqNo);
}
