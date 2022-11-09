package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

public interface Tsder04Service {

    EiINfo getAllTsder04(Tsder04 tsder04);

    Tsder04 selectTsder04ById(Tsder04 tsder04);

    EiINfo saveTsder04(Tsder04 tsder04);

    EiINfo deleteTsder04ByMap(Tsder04 tsder04);

    EiINfo updateTsder04(Tsder04 tsder04);
}
