package com.sd.sdhr.controller.sd.hr;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr01Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/Sdhr01")
//@CrossOrigin(origins = "*") 跨域
@CrossOrigin
public class Tsdhr01Controller {

    @Autowired
    private Tsdhr01Service tsdhr01Service;

    @RequestMapping(value = "/getInfo")
    public Object queryTsdhr01List(Tsdhr01Request tsdhr01){
        //tsdhr01.setIndexPage(tsdhr01.getIndexPage()+1);
        return tsdhr01Service.getAllTsdhr01Test(tsdhr01);
    }

    @RequestMapping(value = "/addSdhr01")
    public Object saveTsdhr01(@RequestBody Tsdhr01 tsdhr01){
        log.info("新增岗位信息："+tsdhr01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr01Service.saveTsdhr01(tsdhr01);
        }catch (Exception e){
            log.error("新增岗位信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSdhr01")
    public Object updateTsdhr01(@RequestBody Tsdhr01 tsdhr01){
        log.info("修改岗位信息："+tsdhr01);
        EiINfo eiINfo = new EiINfo();
        try {
            eiINfo=tsdhr01Service.updateTsdhr01(tsdhr01);
        }catch (Exception e){
            log.error("修改岗位信息错误："+e);
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("操作失败！"+e.getMessage());
        }
        return eiINfo;
    }

    @RequestMapping(value = "/deleteSdhr01")
    public Object deleteTsdhr01(@RequestBody Tsdhr01 tsdhr01){
        log.info("删除岗位信息："+tsdhr01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr01Service.deleteTsdhr01ByMap(tsdhr01);
        }catch (Exception e){
            log.error("删除岗位信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    //导出
    @RequestMapping(value = "/export")
    public void exportTsdhrXls(Tsdhr01 tsdhr01, HttpServletResponse response){

        try {
            List<Tsdhr01> tsdhr01s=tsdhr01Service.getAllTsdhr01();
            this.setExcelRespProp(response, "会员列表");
            EasyExcel.write(response.getOutputStream()).head(Tsdhr01.class).excelType(ExcelTypeEnum.XLSX).sheet("列表").doWrite(tsdhr01s);
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
    @RequestMapping(value = "/import1", method = RequestMethod.POST)
    @ResponseBody
    public void importMemberList(@RequestPart("file") MultipartFile file) throws IOException {
        List<Tsdhr01> list = EasyExcel.read(file.getInputStream())
                .head(Tsdhr01.class)
                .sheet()
                .doReadSync();
        for (Tsdhr01 member : list) {
            System.out.println(member);
        }
    }


}
