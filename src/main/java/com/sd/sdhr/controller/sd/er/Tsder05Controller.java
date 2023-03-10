package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder05;
import com.sd.sdhr.pojo.sd.er.common.Tsder05Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder05Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/Sder05")
@CrossOrigin
public class Tsder05Controller {

    @Autowired
    private Tsder05Service tsder05Service;

    @RequestMapping(value = "/querySder05")
    public Object queryTsder05List(Tsder05Request tsder05){
        return tsder05Service.getAllTsder05(tsder05);
    }

    @RequestMapping(value = "/saveSder05")
    public Object saveTsder05(@RequestBody Tsder05 tsder05){
        log.info("入参信息："+tsder05);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder05Service.saveTsder05(tsder05);
        }catch (Exception e){
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增离职员工信息失败！"+e.getMessage());
            log.error("新增离职员工信息失败："+e);
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSder05")
    public Object updateTsder05(@RequestBody Tsder05 tsder05){
        log.info("入参信息："+tsder05);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder05Service.updateTsder05(tsder05);
        }catch (Exception e){
            log.error("修改离职员工信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("修改离职员工信息失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSder05")
    public Object deleteTsder05(@RequestBody Tsder05 tsder05){
        log.info("入参信息："+tsder05);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder05Service.deleteTsder05ByMap(tsder05);
        }catch (Exception e){
            log.error("删除离职员工信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("删除离职员工信息失败！"+e.getMessage());
        }
        return outINfo;
    }

}
