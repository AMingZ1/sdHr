package com.sd.sdhr.service.sd.of;

import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.of.Tsdof01;

public interface Tsdof01Service {
    EiINfo getAllTsdof01(Tsdof01 tsdof01);

    Tsdof01 selectTsdof01ById(Tsdof01 tsdof01);

    EiINfo saveTsdof01(Tsdof01 tsdof01);

    EiINfo deleteTsdof01ByMap(Tsdof01 tsdof01);

    EiINfo updateTsdof01(Tsdof01 tsdof01);
}
