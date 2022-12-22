package com.sd.sdhr.service.sd.st;

import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst06;
import com.sd.sdhr.pojo.sd.st.common.Tsdst06Request;

public interface Tsdst06Service {

    EiINfo getAllTsdst06(Tsdst06Request tsdst06);

    Tsdst06 selectTsdst06ById(Tsdst06 tsdst06);

    EiINfo saveTsdst06(Tsdst06 tsdst06);

    EiINfo taskTsdst06Shutdown(Tsdst06 tsdst06);

    EiINfo updateTsdst06(Tsdst06 tsdst06);
}
