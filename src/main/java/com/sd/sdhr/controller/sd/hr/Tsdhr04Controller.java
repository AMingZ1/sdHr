package com.sd.sdhr.controller.sd.hr;

import com.alibaba.excel.EasyExcel;
import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.common.*;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr04Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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


    //导出
    @RequestMapping(value = "/export")
    public void exportTsdhrXls(Tsdhr04Request tsdhr04, HttpServletResponse response){

        try {
            List<Tsdhr04> allTsdhr04 = tsdhr04Service.queryTsdhr04s(tsdhr04);
            //将User映射为导出的实体类
            this.setExcelRespProp(response, "面试测评");

            EasyExcel.write(response.getOutputStream(),Tsdhr04.class).sheet("sheet1").doWrite(allTsdhr04);

        }catch (Exception e){
            log.error("导出岗位信息错误："+e);
        }

    }

    //设置excel下载响应头属性

    private void setExcelRespProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("'\'+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }


    /**
     * 从Excel导入会员列表
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public void importMemberList(@RequestPart("file") MultipartFile file) throws Exception {
        List<Tsdhr04Upload> list = EasyExcel.read(file.getInputStream())
                .head(Tsdhr04Upload.class)
                .sheet()
                .doReadSync();
        if (list==null || list.size()<1){
            throw new Exception("导入会错！没有获取到导入清单信息！");
        }
        // 往后端塞值
        EiINfo eiINfo = tsdhr04Service.saveTsdhr04sByImp(list);
    }
}
