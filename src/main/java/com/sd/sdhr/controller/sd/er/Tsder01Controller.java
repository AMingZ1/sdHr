package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.service.sd.er.Tsder01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sder01")
@CrossOrigin
public class Tsder01Controller {

    @Autowired
    private Tsder01Service tsder01Service;

    @RequestMapping(value = "/query")
    public Object queryTsder01List(Tsder01 tsder01){
        return tsder01Service.getAllTsder01(tsder01);
    }

    @RequestMapping(value = "/save")
    public Object saveTsder01(Tsder01 tsder01){
        return tsder01Service.saveTsder01(tsder01);
    }

    @RequestMapping(value = "/update")
    public Object updateTsder01(Tsder01 tsder01){
        return tsder01Service.updateTsder01(tsder01);
    }

    @RequestMapping(value = "/delete")
    public Object deleteTsder01(Tsder01 tsder01){
        return tsder01Service.deleteTsder01ByMap(tsder01);
    }

}
