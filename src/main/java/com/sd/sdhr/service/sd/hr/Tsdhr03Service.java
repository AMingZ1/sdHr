package com.sd.sdhr.service.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr03Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

public interface Tsdhr03Service {

    //获得所有的人才库数据
    /**
     * @ClassName 获取人才库数据
     * @Description TODO
     * @Author Jiangnan Cui
     */
    EiINfo getAllTsdhr03(Tsdhr03Request tsdhr03);

    Tsdhr03 selectTsdhr03ById(Tsdhr03 tsdhr03);

    EiINfo saveTsdhr03(Tsdhr03 tsdhr03)throws Exception;

    EiINfo deleteTsdhr03ByMap(Tsdhr03 tsdhr03)throws Exception;

    EiINfo updateTsdhr03(Tsdhr03 tsdhr03)throws Exception;
}
