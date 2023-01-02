package com.sd.sdhr.service.sd.of.impl;

import com.sd.sdhr.mapper.sd.of.Tsdof01DefinedMapper;
import com.sd.sdhr.mapper.sd.of.Tsdof01Mapper;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.of.Tsdof01;
import com.sd.sdhr.service.sd.of.Tsdof01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Tsdof01ServiceImpl implements Tsdof01Service {

    @Autowired
    Tsdof01Mapper tsdof01Mapper;

    @Autowired
    Tsdof01DefinedMapper tsdof01DefinedMapper;

    @Override
    public EiINfo getAllTsdof01(Tsdof01 tsdof01) {
        return null;
    }

    @Override
    public Tsdof01 selectTsdof01ById(Tsdof01 tsdof01) {
        return null;
    }

    @Override
    public EiINfo saveTsdof01(Tsdof01 tsdof01) {
        return null;
    }

    @Override
    public EiINfo deleteTsdof01ByMap(Tsdof01 tsdof01) {
        return null;
    }

    @Override
    public EiINfo updateTsdof01(Tsdof01 tsdof01) {
        return null;
    }

    @Override
    public EiINfo getEnMonthStat(Tsdof01 tsdof01) {
        EiINfo eiINfo=new EiINfo();
        try {
            //获取当前年份
            Calendar calendar = Calendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            List<Map> maps=tsdof01DefinedMapper.getTsdof01ForOffica(year);
            List<String> strList1= new ArrayList<>();
            if (maps.size()>0) {
                Map map=maps.get(0);
                strList1.add(map.get("1month").toString());
                strList1.add(map.get("2month").toString());
                strList1.add(map.get("3month").toString());
                strList1.add(map.get("4month").toString());
                strList1.add(map.get("5month").toString());
                strList1.add(map.get("6month").toString());
                strList1.add(map.get("7month").toString());
                strList1.add(map.get("8month").toString());
                strList1.add(map.get("9month").toString());
                strList1.add(map.get("10month").toString());
                strList1.add(map.get("11month").toString());
                strList1.add(map.get("12month").toString());
                //strList1 = new ArrayList<>(map.get(0).values());
            }else {
                strList1= Arrays.asList(new String[]{"0","0","0","0","0","0","0","0","0","0","0","0"});
            }
            eiINfo.setMessage("查询成功!");
            eiINfo.setData(strList1);
            eiINfo.setSuccess("1");
        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("查询失败！"+e);
        }
        return eiINfo;
    }
}
