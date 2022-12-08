package com.sd.sdhr.pojo.sd.er.common;

import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsder03Request extends Tsder03 {
    private int  pageSize=10;

    private int pageNum=1;
}
