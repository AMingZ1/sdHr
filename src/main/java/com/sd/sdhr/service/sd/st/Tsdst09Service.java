package com.sd.sdhr.service.sd.st;

import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst09;
import com.sd.sdhr.pojo.sd.st.common.Tsdst09Request;

import java.util.List;

public interface Tsdst09Service {

    //查询获取所以人员消息信息
    List<Tsdst09> getAllTsdst09(Tsdst09Request request);

    // 访谈定时任务调用
    int insertTsdst09ByTalk();

    //关闭消息提醒
    EiINfo closeMsg(Tsdst09 tsdst09);

    // 获取正式访谈未执行人数
    Tsdst09 getFormalInterviewCount(String userId) throws Exception;

    int updateTsder03RemTalkTime(Tsder03 tsder03);
}
