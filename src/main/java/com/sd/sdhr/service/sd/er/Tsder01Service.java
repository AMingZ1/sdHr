package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

public interface Tsder01Service {

    EiINfo getAllTsder01(Tsder01 tsder01);

    Tsder01 selectTsder01ById(Tsder01 tsder01);

    EiINfo saveTsder01(Tsder01 tsder01);

    EiINfo deleteTsder01ByMap(Tsder01 tsder01);

    EiINfo updateTsder01(Tsder01 tsder01);
}
