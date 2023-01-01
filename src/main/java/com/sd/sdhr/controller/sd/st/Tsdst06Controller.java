package com.sd.sdhr.controller.sd.st;

import com.sd.sdhr.pojo.sd.st.Tsdst06;
import com.sd.sdhr.pojo.sd.st.common.Tsdst06Request;
import com.sd.sdhr.service.sd.st.Tsdst06Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Sdst06")
@CrossOrigin
public class Tsdst06Controller {

    @Autowired
    Tsdst06Service tsdst06Service;

    @RequestMapping(value = "/querySdst06")
    public Object queryTsdhr02List(Tsdst06Request tsdst06){

        return tsdst06Service.getAllTsdst06(tsdst06);
    }

    @RequestMapping(value = "/saveSdst06")
    public Object saveTsdst06(@RequestBody Tsdst06 tsdst06){
        return tsdst06Service.saveTsdst06(tsdst06);
    }

    @RequestMapping(value = "/updateSdst06")
    public Object updateTsdst06(@RequestBody Tsdst06 tsdst06){
        return tsdst06Service.updateTsdst06(tsdst06);
    }

    //关闭
    @RequestMapping(value = "/shutdownSdst06")
    public Object shutdownSdst06(@RequestBody Tsdst06 tsdt06){
        return tsdst06Service.taskTsdst06Shutdown(tsdt06);
    }

}
