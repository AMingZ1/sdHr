package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.service.sd.er.Tsder04Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sder04")
@CrossOrigin
public class Tsder04Controller {

    @Autowired
    private Tsder04Service tsder04Service;

    @RequestMapping(value = "/query")
    public Object queryTsder04List(Tsder04 tsder04){
        return tsder04Service.getAllTsder04(tsder04);
    }

    @RequestMapping(value = "/save")
    public Object saveTsder04(Tsder04 tsder04){
        return tsder04Service.saveTsder04(tsder04);
    }

    @RequestMapping(value = "/update")
    public Object updateTsder04(Tsder04 tsder04){
        return tsder04Service.updateTsder04(tsder04);
    }

    @RequestMapping(value = "/delete")
    public Object deleteTsder04(Tsder04 tsder04) {
        return tsder04Service.deleteTsder04ByMap(tsder04);
    }

}
