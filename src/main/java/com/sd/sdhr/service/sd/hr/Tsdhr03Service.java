package com.sd.sdhr.service.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr03Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr03Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;

import java.util.List;

public interface Tsdhr03Service {

    //获得所有的人才库数据
    /**
     * @ClassName 获取人才库数据
     * @Description TODO
     * @Author Jiangnan Cui
     */
    EiINfo getAllTsdhr03(Tsdhr03Request tsdhr03);

    List<Tsdhr03> queryTsdhr03s(Tsdhr03Request tsdhr03);

    Tsdhr03 selectTsdhr03ById(Tsdhr03 tsdhr03);

    EiINfo saveTsdhr03sByImp(List<Tsdhr03Upload> hr03Uploads)throws Exception;

    EiINfo saveTsdhr03(Tsdhr03 tsdhr03)throws Exception;

    EiINfo deleteTsdhr03ByMap(Tsdhr03 tsdhr03)throws Exception;

    EiINfo deleteTsdhr03ByMemberNos(String MemberNos)throws Exception;


    EiINfo updateTsdhr03(Tsdhr03 tsdhr03)throws Exception;

    String saveTsdhr032(Tsdhr03 tsdhr03)throws Exception;
}
