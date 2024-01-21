package com.sd.sdhr.pojo.sd.hr.common;

import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import lombok.*;

@Getter
@Setter
public class Tsdhr02Request extends Tsdhr02 {

    private int  pageSize=10;

    private int pageNum=1;


    /**
     * 创建时间
     */
    private String startRecCreateTime;
    /**
     * 创建时间
     */
    private String endRecCreateTime;
}
