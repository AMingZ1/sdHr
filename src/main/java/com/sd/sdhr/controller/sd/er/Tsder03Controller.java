package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.common.Tsder03Request;
import com.sd.sdhr.service.sd.er.Tsder03Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sder03")
@CrossOrigin
public class Tsder03Controller {

    @Autowired
    private Tsder03Service tsder03Service;

    @RequestMapping(value = "/querySder03")
    public Object queryTsder03List(Tsder03Request tsder03){
        return tsder03Service.getAllTsder03(tsder03);
    }

    @RequestMapping(value = "/saveSder03")
    public Object saveTsder03(@RequestBody Tsder03 tsder03){
        return tsder03Service.saveTsder03(tsder03);
    }

    @RequestMapping(value = "/updateSder03")
    public Object updateTsder03(@RequestBody Tsder03 tsder03){
        return tsder03Service.updateTsder03(tsder03);
    }

    @RequestMapping(value = "/deleteSder03")
    public Object deleteTsder03(@RequestBody Tsder03 tsder03){
        return tsder03Service.deleteTsder03ByMap(tsder03);
    }

}
