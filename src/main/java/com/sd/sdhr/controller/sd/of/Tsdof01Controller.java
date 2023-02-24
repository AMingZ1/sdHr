package com.sd.sdhr.controller.sd.of;


import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.of.Tsdof01;
import com.sd.sdhr.pojo.sd.of.common.Tsdof01Request;
import com.sd.sdhr.service.sd.of.Tsdof01Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/Sdof01")
public class Tsdof01Controller {

    @Autowired
    Tsdof01Service tsdof01Service;



    @RequestMapping(value = "/getInfo")
    public Object queryTsdhr01List(){

        return tsdof01Service.getEnMonthStat(new Tsdof01());
    }

    //发起审批
    @RequestMapping(value = "/initiateApproval")
    public Object initiateApprovalOf01(@RequestBody Tsdof01 tsdof01){
        log.info("发起审批："+tsdof01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo = tsdof01Service.initiateApproval(tsdof01);
        }catch (Exception e){
            log.error("发请审批失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    /**
     * 审批通过.
     * @param tsdof01：审批意见等信息(待完善)
     * @return
     */
    @RequestMapping(value = "/applyApprov")
    public Object applyApprovOf01(@RequestBody Tsdof01Request tsdof01){
        log.info("审批通过："+tsdof01);
        EiINfo outINfo = new EiINfo();
        try {
            Map<String,String> map = new HashMap();
            outINfo = tsdof01Service.applyApprov(tsdof01);
        }catch (Exception e){
            log.error("审批失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    /**
     * 审批驳回.
     * @param tsdof01：审批意见等信息(待完善)
     * @return
     */
    @RequestMapping(value = "/applyReject")
    public Object applyRejectOf01(@RequestBody Tsdof01Request tsdof01){
        log.info("审批驳回："+tsdof01);
        EiINfo outINfo = new EiINfo();
        try {
            Map<String,String> map = new HashMap();
            outINfo = tsdof01Service.applyReject(tsdof01);
        }catch (Exception e){
            log.error("审批失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("操作失败！"+e.getMessage());
        }
        return outINfo;
    }
}
