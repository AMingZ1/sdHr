package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.common.Tsder03Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder03Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
        log.info("入参信息："+tsder03);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder03Service.saveTsder03(tsder03);
        }catch (Exception e){
            log.error("新增访谈信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增信息失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSder03")
    public Object updateTsder03(@RequestBody Tsder03 tsder03){
        log.info("入参信息："+tsder03);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder03Service.updateTsder03(tsder03);
        }catch (Exception e){
            outINfo.setSuccess("-1");
            log.error("修改访谈信息失败："+e);
            outINfo.setMessage("修改信息失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSder03")
    public Object deleteTsder03(@RequestBody Tsder03 tsder03){
        log.info("入参信息："+tsder03);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder03Service.deleteTsder03ByMap(tsder03);
        }catch (Exception e){
            log.error("删除访谈信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("删除信息失败！"+e.getMessage());
        }
        return outINfo;
    }

}
