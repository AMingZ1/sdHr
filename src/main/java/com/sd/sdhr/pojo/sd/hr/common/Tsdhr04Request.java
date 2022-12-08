package com.sd.sdhr.pojo.sd.hr.common;

import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsdhr04Request extends Tsdhr04 {

    private int  pageSize=10;

    private int pageNum=1;
}
