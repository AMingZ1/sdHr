package com.sd.sdhr.service.impl;

import com.sd.sdhr.mapper.Tsdhr01Mapper;
import com.sd.sdhr.pojo.Tsdhr01;
import com.sd.sdhr.service.Tsdhr01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Tsdhr01ServiceImpl implements Tsdhr01Service {


    @Autowired
    private Tsdhr01Mapper tsdhr01Mapper;

    @Override
    public List<Tsdhr01> getAllTsdhr01() {
        return tsdhr01Mapper.selectList(null);
    }

    @Override
    public Tsdhr01 getTsdhr01ByReqNo(String reqNo) {
        return tsdhr01Mapper.selectById(reqNo);
    }

    @Override
    public int saveTsdhr01(Tsdhr01 tsdhr01) {
        return tsdhr01Mapper.insert(tsdhr01);
    }

    @Override
    public int updateTsdhr01ByReqNo(Tsdhr01 tsdhr01) {
        return tsdhr01Mapper.updateById(tsdhr01);
    }

    @Override
    public Tsdhr01 queryTsdhr01ByReqNo(String reqNo) {
        return tsdhr01Mapper.queryTsdhr01ByReqNo(reqNo);
    }
}
