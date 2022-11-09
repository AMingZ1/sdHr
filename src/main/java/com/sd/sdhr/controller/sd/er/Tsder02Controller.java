package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.service.sd.er.Tsder02Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sder02")
@CrossOrigin
public class Tsder02Controller {

    @Autowired
    private Tsder02Service tsder02Service;

    @RequestMapping(value = "/query")
    public Object queryTsder02List(Tsder02 tsder02){
        return tsder02Service.getAllTsder02(tsder02);
    }

    @RequestMapping(value = "/save")
    public Object saveTsder02(Tsder02 tsder02){
        return tsder02Service.saveTsder02(tsder02);
    }

    @RequestMapping(value = "/update")
    public Object updateTsder02(Tsder02 tsder02){
        return tsder02Service.updateTsder02(tsder02);
    }

    @RequestMapping(value = "/delete")
    public Object deleteTsder02(Tsder02 tsder02){
        return tsder02Service.deleteTsder02ByMap(tsder02);
    }

}
