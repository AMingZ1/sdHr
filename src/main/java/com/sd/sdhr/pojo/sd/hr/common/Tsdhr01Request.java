package com.sd.sdhr.pojo.sd.hr.common;

import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import lombok.*;

@Getter
@Setter
public class Tsdhr01Request extends Tsdhr01 {

    private int  pageSize=10;

    private int pageNum=1;

    boolean queryHis;
}
