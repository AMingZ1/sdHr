package com.sd.sdhr.service.sd.of;

import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.of.Tsdof01;

import java.util.Map;

public interface Tsdof01Service {
    EiINfo getAllTsdof01(Tsdof01 tsdof01);

    Tsdof01 selectTsdof01ById(Tsdof01 tsdof01);

    EiINfo saveTsdof01(Tsdof01 tsdof01);

    EiINfo deleteTsdof01ByMap(Tsdof01 tsdof01);

    EiINfo updateTsdof01(Tsdof01 tsdof01);

    /**
     * 获取每月统计数据.
     * @param tsdof01：off信息bean
     * @return
     */
    EiINfo getEnMonthStat(Tsdof01 tsdof01);

    /**
     * 发起审批.
     * @param tsdof01：off信息
     * @return
     */
    EiINfo initiateApproval(Tsdof01 tsdof01);

    /**
     * 审批通过.
     * @param map：审批意见等信息
     * @return
     */
    EiINfo applyApprov(Map<String,String> map);

    /**
     * 审批通过.
     * @param map：审批意见等信息
     * @return
     */
    EiINfo applyReject(Map<String,String> map);
}
