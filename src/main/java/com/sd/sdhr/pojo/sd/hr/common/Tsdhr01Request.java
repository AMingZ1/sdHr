package com.sd.sdhr.pojo.sd.hr.common;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import lombok.*;

@Getter
@Setter
public class Tsdhr01Request extends Tsdhr01 {

    private int  pageSize=10;

    private int pageNum=1;

    boolean queryHis;

    /**
     * 创建时间
     */
    private String startRecCreateTime;
    /**
     * 创建时间
     */
    private String endRecCreateTime;

}
