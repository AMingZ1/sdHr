package com.sd.sdhr.service.sd.of;

import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.of.Tsdof01;
import com.sd.sdhr.pojo.sd.of.common.Tsdof01Request;

import java.util.Map;

public interface Tsdof01Service {
    EiINfo getAllTsdof01(Tsdof01Request tsdof01Re);

    Tsdof01 selectTsdof01ById(Tsdof01 tsdof01);

    EiINfo saveTsdof01(Tsdof01 tsdof01)throws Exception;

    EiINfo deleteTsdof01ByMap(Tsdof01 tsdof01)throws Exception;

    EiINfo updateTsdof01(Tsdof01 tsdof01)throws Exception;

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
    EiINfo initiateApproval(Tsdof01 tsdof01) throws Exception;

    /**
     * 审批通过.
     * @param tsdof01Request：审批意见等信息
     * @return
     */
    EiINfo applyApprov(Tsdof01Request tsdof01Request)throws Exception;

    /**
     * 审批驳回.
     * @param tsdof01Request：审批意见等信息
     * @return
     */
    EiINfo applyReject(Tsdof01Request tsdof01Request)throws Exception;
}
