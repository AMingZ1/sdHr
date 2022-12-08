package com.sd.sdhr.controller.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.service.sd.hr.Tsdhr02Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sdhr02")
@CrossOrigin
public class Tsdhr02Controller {

    @Autowired
    private Tsdhr02Service tsdhr02Service;

    @RequestMapping(value = "/query")
    public Object queryTsdhr02List(Tsdhr02Request tsdhr02){

        return tsdhr02Service.getAllTsdhr02(tsdhr02);
    }

    @RequestMapping(value = "/save")
    public Object saveTsdhr02(Tsdhr02 tsdhr02){
        return tsdhr02Service.saveTsdhr02(tsdhr02);
    }

    @RequestMapping(value = "/update")
    public Object updateTsdhr02(Tsdhr02 tsdhr02){
        return tsdhr02Service.updateTsdhr02(tsdhr02);
    }

    @RequestMapping(value = "/delete")
    public Object deleteTsdhr02(Tsdhr02 tsdhr02){
        return tsdhr02Service.deleteTsdhr02ByMap(tsdhr02);
    }

}
