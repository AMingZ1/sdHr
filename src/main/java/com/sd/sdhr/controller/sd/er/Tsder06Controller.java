package com.sd.sdhr.controller.sd.er;

import com.sd.sdhr.pojo.sd.er.Tsder06;
import com.sd.sdhr.pojo.sd.er.common.Tsder06Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder06Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: Tsder06Controller 年度访谈节点表
 * @Author dems
 * @Package com.sd.sdhr.controller.sd.er
 * @Date 2023/2/24 15:46
 * @description: ${description}
 */
@Slf4j
@RestController
@RequestMapping(value = "/Sder06")
@CrossOrigin
public class Tsder06Controller {

    @Autowired
    Tsder06Service tsder06Service;

    @RequestMapping(value = "/querySder06")
    public Object queryTsder06List(Tsder06Request tsder06){
        return tsder06Service.getAllTsder06(tsder06);
    }

    @RequestMapping(value = "/saveSder06")
    public Object saveTsder05(@RequestBody Tsder06 tsder06){
        log.info("入参信息："+tsder06);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder06Service.saveTsder06(tsder06);
        }catch (Exception e){
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增年度访谈节点信息失败！"+e.getMessage());
            log.error("新增年度访谈节点信息失败："+e);
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSder06")
    public Object updateTsder05(@RequestBody Tsder06 tsder06){
        log.info("入参信息："+tsder06);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder06Service.updateTsder06(tsder06);
        }catch (Exception e){
            log.error("修改年度访谈节点信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("修改年度访谈节点信息失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSder06")
    public Object deleteTsder05(@RequestBody Tsder06 tsder06){
        log.info("入参信息："+tsder06);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder06Service.deleteTsder06ByMap(tsder06);
        }catch (Exception e){
            log.error("删除年度访谈节点信息失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("删除年度访谈节点信息失败！"+e.getMessage());
        }
        return outINfo;
    }

}
