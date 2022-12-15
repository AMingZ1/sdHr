package com.sd.sdhr.pojo.sd.hr.common;

import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdhr01Request extends Tsdhr01 {

    private int  pageSize=10;

    private int pageNum=1;

    boolean queryHis;
}
