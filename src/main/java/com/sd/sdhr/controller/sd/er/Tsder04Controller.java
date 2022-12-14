package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.er.common.Tsder04Request;
import com.sd.sdhr.service.sd.er.Tsder04Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sder04")
@CrossOrigin
public class Tsder04Controller {

    @Autowired
    private Tsder04Service tsder04Service;

    @RequestMapping(value = "/querySder04")
    public Object queryTsder04List(Tsder04Request tsder04){
        return tsder04Service.getAllTsder04(tsder04);
    }

    @RequestMapping(value = "/saveSder04")
    public Object saveTsder04(@RequestBody Tsder04 tsder04){
        return tsder04Service.saveTsder04(tsder04);
    }

    @RequestMapping(value = "/updateSder04")
    public Object updateTsder04(@RequestBody Tsder04 tsder04){
        return tsder04Service.updateTsder04(tsder04);
    }

    @RequestMapping(value = "/deleteSder04")
    public Object deleteTsder04(@RequestBody Tsder04 tsder04) {
        return tsder04Service.deleteTsder04ByMap(tsder04);
    }

    @RequestMapping(value = "/getInfo")
    public Object getEnMonthStatByTalk(){
        return tsder04Service.getEnMonthStatByTalk(new Tsder04());
    }
}
