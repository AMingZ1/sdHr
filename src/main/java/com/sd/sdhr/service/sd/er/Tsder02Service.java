package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

public interface Tsder02Service {

    EiINfo getAllTsder02(Tsder02 tsder02);

    Tsder02 selectTsder02ById(Tsder02 tsder02);

    EiINfo saveTsder02(Tsder02 tsder02);

    EiINfo deleteTsder02ByMap(Tsder02 tsder02);

    EiINfo updateTsder02(Tsder02 tsder02);
}
