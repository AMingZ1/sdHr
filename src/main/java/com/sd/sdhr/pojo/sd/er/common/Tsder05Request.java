package com.sd.sdhr.pojo.sd.er.common;

import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.Tsder05;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsder05Request extends Tsder05 {
    private int  pageSize=10;

    private int pageNum=1;
}
