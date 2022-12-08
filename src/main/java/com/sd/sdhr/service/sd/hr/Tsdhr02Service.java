package com.sd.sdhr.service.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;


public interface Tsdhr02Service {

    //获得所有的电联记录表
    EiINfo getAllTsdhr02(Tsdhr02Request tsdhr02 );

    Tsdhr02 selectTsdhr02ById(Tsdhr02 tsdhr02);

    EiINfo saveTsdhr02(Tsdhr02 tsdhr02);

    EiINfo deleteTsdhr02ByMap(Tsdhr02 tsdhr02);

    EiINfo updateTsdhr02(Tsdhr02 tsdhr02);
}
