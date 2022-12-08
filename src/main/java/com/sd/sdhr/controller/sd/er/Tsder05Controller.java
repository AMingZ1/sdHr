package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.Tsder05;
import com.sd.sdhr.pojo.sd.er.common.Tsder05Request;
import com.sd.sdhr.service.sd.er.Tsder01Service;
import com.sd.sdhr.service.sd.er.Tsder05Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sder05")
@CrossOrigin
public class Tsder05Controller {

    @Autowired
    private Tsder05Service tsder05Service;

    @RequestMapping(value = "/query")
    public Object queryTsder05List(Tsder05Request tsder05){
        return tsder05Service.getAllTsder05(tsder05);
    }

    @RequestMapping(value = "/save")
    public Object saveTsder05(Tsder05 tsder05){
        return tsder05Service.saveTsder05(tsder05);
    }

    @RequestMapping(value = "/update")
    public Object updateTsder05(Tsder05 tsder05){
        return tsder05Service.updateTsder05(tsder05);
    }

    @RequestMapping(value = "/delete")
    public Object deleteTsder05(Tsder05 tsder05){
        return tsder05Service.deleteTsder05ByMap(tsder05);
    }

}
