package com.sd.sdhr.controller.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr04Request;
import com.sd.sdhr.service.sd.hr.Tsdhr04Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sdhr04")
@CrossOrigin
public class Tsdhr04Controller {

    @Autowired
    private Tsdhr04Service tsdhr04Service;

    @RequestMapping(value = "/query")
    public Object queryTsdhr04List(Tsdhr04Request tsdhr04){
        return tsdhr04Service.getAllTsdhr04(tsdhr04);
    }

    @RequestMapping(value = "/saveSdhr04")
    public Object saveTsdhr04(@RequestBody Tsdhr04 tsdhr04){
        return tsdhr04Service.saveTsdhr04(tsdhr04);
    }

    @RequestMapping(value = "/updateSdhr04")
    public Object updateTsdhr04(@RequestBody Tsdhr04 tsdhr04){
        return tsdhr04Service.updateTsdhr04(tsdhr04);
    }

    @RequestMapping(value = "/deleteSdhr04")
    public Object deleteTsdhr04(@RequestBody Tsdhr04 tsdhr04){
        return tsdhr04Service.deleteTsdhr04ByMap(tsdhr04);
    }
}
