package com.sd.sdhr.controller.sd.of;


import com.sd.sdhr.mapper.sd.of.Tsdof01DefinedMapper;
import com.sd.sdhr.mapper.sd.of.Tsdof01Mapper;
import com.sd.sdhr.pojo.sd.of.Tsdof01;
import com.sd.sdhr.service.sd.of.Tsdof01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Sdof01")
public class Tsdof01Controller {

    @Autowired
    Tsdof01Service tsdof01Service;



    @RequestMapping(value = "/getInfo")
    public Object queryTsdhr01List(){

        return tsdof01Service.getEnMonthStat(new Tsdof01());
    }
}
