package com.sd.sdhr.service.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

import java.util.List;


public interface Tsdhr02Service {

    //获得所有的电联记录表
    EiINfo getAllTsdhr02(Tsdhr02Request tsdhr02 );

    //获得所有的电联记录表
    List<Tsdhr02> queryTsdhr02s(Tsdhr02Request tsdhr02 );

    //通过面试测评号获取对应的面试测评信息
    Tsdhr02 queryTsdhr02ByPlanNo(String planNo);

    Tsdhr02 selectTsdhr02ById(Tsdhr02 tsdhr02);

    EiINfo saveTsdhr02(Tsdhr02 tsdhr02)throws Exception;

    EiINfo deleteTsdhr02ByMap(Tsdhr02 tsdhr02)throws Exception;

    EiINfo deleteTsdhr02ByPlanNos(String planNos)throws Exception;


    EiINfo updateTsdhr02(Tsdhr02 tsdhr02)throws Exception;

    // 生成人才库数据
    EiINfo insertHr04ByHr02(Tsdhr02 tsdhr02)throws Exception;

    EiINfo saveTsdhr02sByImp(List<Tsdhr02Upload> hr02Uploads) throws Exception;

    String saveTsdhr022(Tsdhr02 tsdhr02)throws Exception;
}
