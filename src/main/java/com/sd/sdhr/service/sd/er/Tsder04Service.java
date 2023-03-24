package com.sd.sdhr.service.sd.er;

import antlr.collections.impl.LList;
import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.er.common.Tsder03Request;
import com.sd.sdhr.pojo.sd.er.common.Tsder04Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

import java.util.List;
import java.util.Map;

public interface Tsder04Service {

    EiINfo getAllTsder04(Tsder04Request tsder04);

    List<Tsder04> queryTsder04s(Tsder03Request tsder03,Tsder04Request tsder04);

    Tsder04 selectTsder04ById(Tsder04 tsder04);

    EiINfo saveTsder04(Tsder04 tsder04)throws Exception;

    EiINfo deleteTsder04ByMap(Tsder04 tsder04)throws Exception;

    EiINfo updateTsder04(Tsder04 tsder04)throws Exception;

    //获取每月统计数据
    EiINfo getEnMonthStatByTalk(Tsder04 tsder04);
}
