package com.sd.sdhr.pojo.sd.hr.common;

import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.Tsdhr05;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsdhr05Request extends Tsdhr05 {

    private int  pageSize=10;

    private int pageNum=1;
}
