package com.sd.sdhr.service.sd.st;

import com.sd.sdhr.pojo.sd.st.Tsdst12;

import java.util.List;

/**
 * @Title: Tsdst12Service,附件转换
 * @Author dems
 * @Package com.sd.sdhr.service.sd.st
 * @Date 2023/2/20 14:20
 * @description: ${description}
 */
public interface Tsdst12Service {

    //查询获取所以人员消息信息
    List<Tsdst12> getAllTsdst12(Tsdst12 tsdst12);

    //新增附件关系信息
    String saveTsdst12(String businessNo,String businessKeyword,String filePath,String fileName,String fileSuffix);

    void delectTsdst12ByFileId(String fileId);
}
