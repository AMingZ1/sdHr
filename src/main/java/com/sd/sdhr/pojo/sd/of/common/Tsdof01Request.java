package com.sd.sdhr.pojo.sd.of.common;

import com.sd.sdhr.pojo.sd.of.Tsdof01;
import lombok.Getter;
import lombok.Setter;

/**
 * @Title: Tsdof01Request
 * @Author dems
 * @Package com.sd.sdhr.pojo.sd.of.common
 * @Date 2023/2/24 13:17
 * @description: ${description}
 */
@Getter
@Setter
public class Tsdof01Request extends Tsdof01 {
    private int  pageSize=10;

    private int pageNum=1;
}
