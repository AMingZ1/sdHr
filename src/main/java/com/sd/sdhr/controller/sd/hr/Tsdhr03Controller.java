package com.sd.sdhr.controller.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr03Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr03Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/Sdhr03")
@CrossOrigin
public class Tsdhr03Controller {

    @Autowired
    private Tsdhr03Service tsdhr03Service;

    @RequestMapping(value = "/querySdhr03")
    public Object queryTsdhr03List(Tsdhr03Request tsdhr03){

        return tsdhr03Service.getAllTsdhr03(tsdhr03);
    }

    @RequestMapping(value = "/saveSdhr03")
    public Object saveTsdhr03(@RequestBody Tsdhr03 tsdhr03){
        log.info("新增人才库信息："+tsdhr03);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr03Service.saveTsdhr03(tsdhr03);
        }catch (Exception e){
            log.error("新增人才库信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增人才库信息失败:"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSdhr03")
    public Object updateTsdhr03(@RequestBody Tsdhr03 tsdhr03){
        log.info("修改人才库信息："+tsdhr03);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr03Service.updateTsdhr03(tsdhr03);
        }catch (Exception e){
            log.error("修改人才库信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("修改人才库信息失败:"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSdhr03")
    public Object deleteTsdhr03(@RequestBody Tsdhr03 tsdhr03){
        log.info("删除人才库信息："+tsdhr03);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr03Service.deleteTsdhr03ByMap(tsdhr03);
        }catch (Exception e){
            log.error("删除人才库信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("删除人才库信息失败:"+e.getMessage());
        }
        return outINfo;
    }

}
