package com.sd.sdhr.service.sd.st;

import com.sd.sdhr.pojo.sd.st.Tsdst11;

import java.util.List;

/**
 * @Title: Tsdst11Service 人员关系
 * @Author dems
 * @Package com.sd.sdhr.service.sd.st
 * @Date 2023/2/22 10:35
 * @description: ${description}
 */
public interface Tsdst11Service {

    //查询获取所以人员消息信息
    List<Tsdst11> getAllTsdst11(Tsdst11 tsdst11);

    /**
     * 根据角色代码获取人员信息.
     *
     * @param roleCode：角色代码
     * @return
     */
    List<Tsdst11> getAllTsdst11(String roleCode);
}
