package com.sd.sdhr.pojo.sd.st.common;

import com.sd.sdhr.pojo.sd.st.Tsdst06;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsdst06Request extends Tsdst06 {
    private int  pageSize=10;

    private int pageNum=1;
}
