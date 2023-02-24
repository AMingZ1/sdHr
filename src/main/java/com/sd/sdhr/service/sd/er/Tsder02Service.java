package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.common.Tsder02Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import liquibase.pro.packaged.E;

public interface Tsder02Service {

    EiINfo getAllTsder02(Tsder02Request tsder02);

    Tsder02 selectTsder02ById(Tsder02 tsder02);

    EiINfo saveTsder02(Tsder02 tsder02) throws Exception;

    EiINfo deleteTsder02ByMap(Tsder02 tsder02)throws Exception;

    EiINfo updateTsder02(Tsder02 tsder02)throws Exception;
}
