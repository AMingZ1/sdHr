package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.er.common.Tsder04Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder04Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/Sder04")
@CrossOrigin
public class Tsder04Controller {

    @Autowired
    private Tsder04Service tsder04Service;

    @RequestMapping(value = "/querySder04")
    public Object queryTsder04List(Tsder04Request tsder04){
        return tsder04Service.getAllTsder04(tsder04);
    }

    @RequestMapping(value = "/saveSder04")
    public Object saveTsder04(@RequestBody Tsder04 tsder04){
        log.info("入参信息："+tsder04);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder04Service.saveTsder04(tsder04);
        }catch (Exception e){
            log.error("新增访谈明细信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增信息失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSder04")
    public Object updateTsder04(@RequestBody Tsder04 tsder04){
        log.info("入参信息："+tsder04);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder04Service.updateTsder04(tsder04);
        }catch (Exception e){
            log.error("修改访谈明细信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("修改信息失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSder04")
    public Object deleteTsder04(@RequestBody Tsder04 tsder04) {
        log.info("入参信息："+tsder04);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder04Service.deleteTsder04ByMap(tsder04);
        }catch (Exception e){
            log.error("删除访谈信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("删除信息失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/getInfo")
    public Object getEnMonthStatByTalk(){
        return tsder04Service.getEnMonthStatByTalk(new Tsder04());
    }
}
