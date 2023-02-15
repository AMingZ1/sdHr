package com.sd.sdhr.controller.sd.st;

import com.sd.sdhr.pojo.sd.st.Tsdst03;
import com.sd.sdhr.pojo.sd.st.common.Tsdst03Request;
import com.sd.sdhr.service.sd.st.Tsdst03Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Sdst03")
@CrossOrigin
public class Tsdst03Controller {

    @Autowired
    Tsdst03Service tsdst03Service;

    @RequestMapping(value = "/getItemList")
    public Object queryTsdhr02List(Tsdst03Request tsdst03){

        return tsdst03Service.getAllTsdst03(tsdst03);
    }

    @RequestMapping(value = "/saveSdst03")
    public Object saveTsdst03(@RequestBody Tsdst03 tsdst03){
        return tsdst03Service.saveTsdst03(tsdst03);
    }

}
