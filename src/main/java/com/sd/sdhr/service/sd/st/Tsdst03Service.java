package com.sd.sdhr.service.sd.st;

import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst03;
import com.sd.sdhr.pojo.sd.st.Tsdst06;
import com.sd.sdhr.pojo.sd.st.common.Tsdst03Request;

public interface Tsdst03Service {

    EiINfo getAllTsdst03(Tsdst03Request tsdst03Request);

    Tsdst06 selectTsdst03ById(Tsdst03 tsdst03);

    EiINfo saveTsdst03(Tsdst03 tsdst03);
}
