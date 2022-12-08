package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.common.Tsder02Request;
import com.sd.sdhr.service.sd.er.Tsder02Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sder02")
@CrossOrigin
public class Tsder02Controller {

    @Autowired
    private Tsder02Service tsder02Service;

    @RequestMapping(value = "/querySder02")
    public Object queryTsder02List(Tsder02Request tsder02){
        return tsder02Service.getAllTsder02(tsder02);
    }

    @RequestMapping(value = "/saveSder02")
    public Object saveTsder02(@RequestBody Tsder02 tsder02){
        return tsder02Service.saveTsder02(tsder02);
    }

    @RequestMapping(value = "/updateSder02")
    public Object updateTsder02(@RequestBody Tsder02 tsder02){
        return tsder02Service.updateTsder02(tsder02);
    }

    @RequestMapping(value = "/deleteSder02")
    public Object deleteTsder02(@RequestBody Tsder02 tsder02){
        return tsder02Service.deleteTsder02ByMap(tsder02);
    }

}
