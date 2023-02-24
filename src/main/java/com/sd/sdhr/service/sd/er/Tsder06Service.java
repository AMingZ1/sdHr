package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder06;
import com.sd.sdhr.pojo.sd.er.common.Tsder06Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

public interface Tsder06Service {

    EiINfo getAllTsder06(Tsder06Request tsder06);

    Tsder06 selectTsder06ById(Tsder06 tsder06);

    EiINfo saveTsder06(Tsder06 tsder06)throws Exception;

    EiINfo deleteTsder06ByMap(Tsder06 tsder06)throws Exception;

    EiINfo updateTsder06(Tsder06 tsder06)throws Exception;
}
