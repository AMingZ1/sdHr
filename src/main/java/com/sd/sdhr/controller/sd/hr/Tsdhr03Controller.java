package com.sd.sdhr.controller.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr03Request;
import com.sd.sdhr.service.sd.hr.Tsdhr03Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sdhr03")
@CrossOrigin
public class Tsdhr03Controller {

    @Autowired
    private Tsdhr03Service tsdhr03Service;

    @RequestMapping(value = "/query")
    public Object queryTsdhr03List(Tsdhr03Request tsdhr03){

        return tsdhr03Service.getAllTsdhr03(tsdhr03);
    }

    @RequestMapping(value = "/save")
    public Object saveTsdhr03(Tsdhr03 tsdhr03){
        return tsdhr03Service.saveTsdhr03(tsdhr03);
    }

    @RequestMapping(value = "/update")
    public Object updateTsdhr03(Tsdhr03 tsdhr03){
        return tsdhr03Service.updateTsdhr03(tsdhr03);
    }

    @RequestMapping(value = "/delete")
    public Object deleteTsdhr03(Tsdhr03 tsdhr03){
        return tsdhr03Service.deleteTsdhr03ByMap(tsdhr03);
    }

}
