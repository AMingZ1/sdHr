package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.common.Tsder01Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder01Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/Sder01")
@CrossOrigin
public class Tsder01Controller {

    @Autowired
    private Tsder01Service tsder01Service;

    @RequestMapping(value = "/querySder01")
    public Object queryTsder01List(Tsder01Request tsder01){
        return tsder01Service.getAllTsder01(tsder01);
    }

    @RequestMapping(value = "/saveSder01")
    public Object saveTsder01(@RequestBody Tsder01 tsder01){
        log.info("新增岗位信息："+tsder01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder01Service.saveTsder01(tsder01);
        }catch (Exception e){
            log.error("新增岗位信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSder01")
    public Object updateTsder01(@RequestBody Tsder01 tsder01){
        log.info("新增岗位信息："+tsder01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder01Service.updateTsder01(tsder01);
        }catch (Exception e){
            log.error("修改信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("修改操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSder01")
    public Object deleteTsder01(@RequestBody Tsder01 tsder01){
        log.info("新增岗位信息："+tsder01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder01Service.deleteTsder01ByMap(tsder01);
        }catch (Exception e){
            log.error("删除信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("操作失败！"+e.getMessage());
        }
        return outINfo;
    }

}
