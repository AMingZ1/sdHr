package com.sd.sdhr.pojo.sd.hr.common;

import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsdhr03Request extends Tsdhr03 {
    
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
