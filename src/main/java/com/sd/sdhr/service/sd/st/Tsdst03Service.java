package com.sd.sdhr.service.sd.st;

import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst03;
import com.sd.sdhr.pojo.sd.st.common.Tsdst03Request;

import java.util.Map;

public interface Tsdst03Service {

    EiINfo getAllTsdst03(Tsdst03Request tsdst03Request);

    Tsdst03 selectTsdst03ById(String codeNo,String codeEname) throws Exception;

    EiINfo saveTsdst03(Tsdst03 tsdst03);

    Map<String,String> selectTsdst03ToMap(String codeNo, String codeEname) throws Exception;



    Tsdst03 selectTsdst032(String codeNo, String codeEname, String codeCname) ;

    Tsdst03 saveTsdst032(Tsdst03 tsdst03);
}
