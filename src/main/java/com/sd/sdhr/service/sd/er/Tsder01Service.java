package com.sd.sdhr.service.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.common.Tsder01Request;
import com.sd.sdhr.pojo.sd.er.common.Tsder01Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

import java.util.List;

public interface Tsder01Service {

    EiINfo getAllTsder01(Tsder01Request tsder01);


    List<Tsder01> queryTsder01s(Tsder01Request tsder01);

    Tsder01 selectTsder01ById(Tsder01 tsder01);

    EiINfo saveTsder01(Tsder01 tsder01)throws Exception;

    EiINfo deleteTsder01ByMap(Tsder01 tsder01)throws Exception;

    EiINfo updateTsder01(Tsder01 tsder01)throws Exception;

    /*
     *接收导入的bean进行 新增
     */
    EiINfo saveTsder01sByImp(List<Tsder01Upload> er01UploadS)throws Exception;
}
