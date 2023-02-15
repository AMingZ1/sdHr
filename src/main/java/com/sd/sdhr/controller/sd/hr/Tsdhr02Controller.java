package com.sd.sdhr.controller.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.service.sd.hr.Tsdhr02Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Sdhr02")
@CrossOrigin
public class Tsdhr02Controller {

    @Autowired
    private Tsdhr02Service tsdhr02Service;

    @RequestMapping(value = "/querySdhr02")
    public Object queryTsdhr02List(Tsdhr02Request tsdhr02){

        return tsdhr02Service.getAllTsdhr02(tsdhr02);
    }

    @RequestMapping(value = "/saveSdhr02")
    public Object saveTsdhr02(@RequestBody Tsdhr02 tsdhr02){
        return tsdhr02Service.saveTsdhr02(tsdhr02);
    }

    @RequestMapping(value = "/updateSdhr02")
    public Object updateTsdhr02(@RequestBody Tsdhr02 tsdhr02){
        return tsdhr02Service.updateTsdhr02(tsdhr02);
    }

    @RequestMapping(value = "/deleteSdhr02")
    public Object deleteTsdhr02(@RequestBody Tsdhr02 tsdhr02){
        return tsdhr02Service.deleteTsdhr02ByMap(tsdhr02);
    }

}
