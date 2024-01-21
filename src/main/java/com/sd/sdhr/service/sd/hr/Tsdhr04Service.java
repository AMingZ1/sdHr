package com.sd.sdhr.service.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr04Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr04Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

import java.util.List;


public interface Tsdhr04Service {

    //获得所有的电联记录表
    EiINfo getAllTsdhr04(Tsdhr04Request tsdhr04);

    List<Tsdhr04> queryTsdhr04s(Tsdhr04Request tsdhr04);

    Tsdhr04 selectTsdhr04ById(Tsdhr04 tsdhr04);

    EiINfo saveTsdhr04sByImp(List<Tsdhr04Upload> hr04Uploads)throws Exception;

    EiINfo saveTsdhr04(Tsdhr04 tsdhr04)throws Exception;

    String saveTsdhr042(Tsdhr04 tsdhr04)throws Exception;

    EiINfo deleteTsdhr04ByMap(Tsdhr04 tsdhr04)throws Exception;


    EiINfo deleteTsdhr04ByItvNos(String itvNos)throws Exception;


    EiINfo updateTsdhr04(Tsdhr04 tsdhr04)throws Exception;

    /**
     * 生成offer.
     * @param tsdhr04：面试测评对象
     * @return
     */
    EiINfo insertOffer(Tsdhr04 tsdhr04)throws Exception;

}
