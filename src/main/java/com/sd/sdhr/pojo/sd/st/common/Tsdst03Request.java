package com.sd.sdhr.pojo.sd.st.common;

import com.sd.sdhr.pojo.sd.st.Tsdst03;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tsdst03Request extends Tsdst03 {

    private int  pageSize=10;

    private int pageNum=1;
}
