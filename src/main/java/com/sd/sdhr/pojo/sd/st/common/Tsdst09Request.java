package com.sd.sdhr.pojo.sd.st.common;

import com.sd.sdhr.pojo.sd.st.Tsdst09;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsdst09Request extends Tsdst09 {

    private int  pageSize=10;

    private int pageNum=1;
}
