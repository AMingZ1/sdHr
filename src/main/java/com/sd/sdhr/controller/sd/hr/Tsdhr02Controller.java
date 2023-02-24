package com.sd.sdhr.controller.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr02Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/Sdhr02")
@CrossOrigin
public class Tsdhr02Controller {

    @Autowired
    private Tsdhr02Service tsdhr02Service;

    @RequestMapping(value = "/querySdhr02")
    public Object queryTsdhr02List(Tsdhr02Request tsdhr02){

        return tsdhr02Service.getAllTsdhr02(tsdhr02);
    }

    @RequestMapping(value = "/saveSdhr02")
    public Object saveTsdhr02(@RequestBody Tsdhr02 tsdhr02){
        log.info("新增电联信息："+tsdhr02);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr02Service.saveTsdhr02(tsdhr02);
        }catch (Exception e){
            log.error("新增电联信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage(e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSdhr02")
    public Object updateTsdhr02(@RequestBody Tsdhr02 tsdhr02){
        log.info("修改电联信息："+tsdhr02);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr02Service.updateTsdhr02(tsdhr02);
        }catch (Exception e){
            log.error("修改电联信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage(e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSdhr02")
    public Object deleteTsdhr02( Tsdhr02 tsdhr02){
        log.info("删除电联信息："+tsdhr02);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr02Service.deleteTsdhr02ByMap(tsdhr02);
        }catch (Exception e){
            log.error("删除电联信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage(e.getMessage());
        }
        return outINfo;
    }

}
