package com.sd.sdhr.controller.sd.st;

import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.st.Tsdst09Mapper;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst09;
import com.sd.sdhr.pojo.sd.st.common.Tsdst09Request;
import com.sd.sdhr.service.sd.st.Tsdst09Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sdst09")
@CrossOrigin
public class Tsdst09Controller {

    @Autowired
    Tsdst09Service tsdst09Service;

    @RequestMapping(value = "/getAllMsgByUser")
    private Object getAllMsgNotificationByUser(Tsdst09Request tsdst09){
        EiINfo eiINfo=new EiINfo();
        try {
            List<Tsdst09> listSt09=tsdst09Service.getAllTsdst09(tsdst09);
            //查询正式访谈条数。
            Tsdst09 tsdst091=tsdst09Service.getFormalInterviewCount(tsdst09.getMessageId());
            listSt09.add(tsdst091);
            if (CollectionUtils.isEmpty(listSt09)){
                throw new Exception("返回结果为null");
            }
            PageInfo pageInfo=new PageInfo(listSt09);
            eiINfo.setMessage("查询成功!");
            eiINfo.setTotalNum(pageInfo.getTotal());
            eiINfo.setData(listSt09);
            eiINfo.setSuccess("1");
        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("查询失败!"+e);
        }

        return eiINfo;
    }
}
