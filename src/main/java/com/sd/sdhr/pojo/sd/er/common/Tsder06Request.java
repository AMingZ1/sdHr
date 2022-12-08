package com.sd.sdhr.pojo.sd.er.common;

import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.Tsder06;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsder06Request extends Tsder06 {
    private int  pageSize=10;

    private int pageNum=1;
}
