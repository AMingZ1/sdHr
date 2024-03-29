package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.er.Tsder05;
import com.sd.sdhr.pojo.sd.er.common.Tsder05Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

public interface Tsder05Service {

    EiINfo getAllTsder05(Tsder05Request tsder05);

    Tsder05 selectTsder05ById(Tsder05 tsder05);

    EiINfo saveTsder05(Tsder05 tsder05)throws Exception;

    EiINfo deleteTsder05ByMap(Tsder05 tsder05)throws Exception;

    EiINfo updateTsder05(Tsder05 tsder05)throws Exception;
}
