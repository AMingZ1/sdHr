package com.sd.sdhr.controller.sd.hr;

import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr04Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr04Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/Sdhr04")
@CrossOrigin
public class Tsdhr04Controller {

    @Autowired
    private Tsdhr04Service tsdhr04Service;

    @RequestMapping(value = "/query")
    public Object queryTsdhr04List(Tsdhr04Request tsdhr04){
        return tsdhr04Service.getAllTsdhr04(tsdhr04);
    }

    /**
     * 新增面试测评信息.
     * @param tsdhr04：面试测评对象
     * @return
     */
    @RequestMapping(value = "/saveSdhr04")
    public Object saveTsdhr04(@RequestBody Tsdhr04 tsdhr04){
        log.info("新增面试测评信息："+tsdhr04);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr04Service.saveTsdhr04(tsdhr04);
        }catch (Exception e){
            outINfo.setSuccess("-1");
            log.error("新增面试测评信息失败："+e);
            outINfo.setMessage("新增面试测评信息失败:"+e.getMessage());
        }
        return outINfo;
    }

    /**
     * 修改面试测评信息.
     * @param tsdhr04：面试测评对象
     * @return
     */
    @RequestMapping(value = "/updateSdhr04")
    public Object updateTsdhr04(@RequestBody Tsdhr04 tsdhr04){
        log.info("修改面试测评信息："+tsdhr04);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr04Service.updateTsdhr04(tsdhr04);
        }catch (Exception e){
            log.error("修改面试测评信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("修改面试测评信息失败："+e.getMessage());
        }
        return outINfo;
    }

    /**
     * 删除面试测评信息.
     * @param tsdhr04：面试测评对象
     * @return
     */
    @RequestMapping(value = "/deleteSdhr04")
    public Object deleteTsdhr04( Tsdhr04 tsdhr04){
        log.info("删除面试测评信息："+tsdhr04);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr04Service.deleteTsdhr04ByMap(tsdhr04);
        }catch (Exception e){
            log.error("删除面试测评信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("删除面试测评信息失败:"+e.getMessage());
        }
        return outINfo;
    }


    /**
     * 生成Offer信息.
     * @param tsdhr04：面试测评对象
     * @return
     */
    @RequestMapping(value = "/insertOffer")
    public Object insertOf01ByHr04(@RequestBody Tsdhr04 tsdhr04){
        log.info("通过hr04生成Offer信息："+tsdhr04);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr04Service.insertOffer(tsdhr04);
        }catch (Exception e){
            log.error("通过hr04生成Offer信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("生成Offer信息失败"+e.getMessage());
        }
        return outINfo;
    }
}
