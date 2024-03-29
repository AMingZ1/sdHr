package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.common.Tsder03Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

import java.util.List;

public interface Tsder03Service {

    EiINfo getAllTsder03(Tsder03Request tsder03);

    List<Tsder03> queryTsder03s(Tsder03Request tsder03);

    Tsder03 selectTsder03ById(Tsder03 tsder03);

    EiINfo saveTsder03(Tsder03 tsder03)throws Exception;

    EiINfo deleteTsder03ByMap(Tsder03 tsder03)throws Exception;

    EiINfo updateTsder03(Tsder03 tsder03)throws Exception;

    //定时任务 判断访谈是否逾期
    EiINfo isOverdueJudge();


}
