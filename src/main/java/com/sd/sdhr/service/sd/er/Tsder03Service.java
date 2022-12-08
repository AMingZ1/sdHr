package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.common.Tsder03Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

public interface Tsder03Service {

    EiINfo getAllTsder03(Tsder03Request tsder03);

    Tsder03 selectTsder03ById(Tsder03 tsder03);

    EiINfo saveTsder03(Tsder03 tsder03);

    EiINfo deleteTsder03ByMap(Tsder03 tsder03);

    EiINfo updateTsder03(Tsder03 tsder03);
}
