package com.sd.sdhr.service.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr03Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

public interface Tsdhr03Service {

    //获得所有的人才库数据
    EiINfo getAllTsdhr03(Tsdhr03Request tsdhr03);

    Tsdhr03 selectTsdhr03ById(Tsdhr03 tsdhr03);

    EiINfo saveTsdhr03(Tsdhr03 tsdhr03);

    EiINfo deleteTsdhr03ByMap(Tsdhr03 tsdhr03);

    EiINfo updateTsdhr03(Tsdhr03 tsdhr03);
}
