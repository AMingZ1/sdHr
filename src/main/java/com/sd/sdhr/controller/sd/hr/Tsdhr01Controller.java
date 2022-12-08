package com.sd.sdhr.controller.sd.hr;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Request;
import com.sd.sdhr.service.sd.hr.Tsdhr01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class Tsdhr01Controller {

    @Autowired
    private Tsdhr01Service tsdhr01Service;

    @RequestMapping(value = "/Sdhr01")
    public Object queryTsdhr01List(Tsdhr01Request tsdhr01){
        //tsdhr01.setIndexPage(tsdhr01.getIndexPage()+1);
        return tsdhr01Service.getAllTsdhr01Test(tsdhr01);
    }

    @RequestMapping(value = "/sdhr01/save")
    public Object saveTsdhr01(Tsdhr01 tsdhr01){

        return tsdhr01Service.saveTsdhr01(tsdhr01);
    }

    @RequestMapping(value = "/Sdhr01/Update")
    public Object updateTsdhr01(Tsdhr01 tsdhr01){

        return tsdhr01Service.updateTsdhr01(tsdhr01);
    }

    @RequestMapping(value = "/Sdhr01/Delete")
    public Object deleteTsdhr01(Tsdhr01 tsdhr01){

        return tsdhr01Service.deleteTsdhr01ByMap(tsdhr01);
    }

    //导出
    @RequestMapping(value = "/sdhr01/export")
    public void exportTsdhrXls(Tsdhr01 tsdhr01, HttpServletResponse response){

        try {
            List<Tsdhr01> tsdhr01s=tsdhr01Service.getAllTsdhr01();
            this.setExcelRespProp(response, "会员列表");
            EasyExcel.write(response.getOutputStream()).head(Tsdhr01.class).excelType(ExcelTypeEnum.XLSX).sheet("列表").doWrite(tsdhr01s);
        }catch (Exception e){

        }

    }

    //设置excel下载响应头属性

    private void setExcelRespProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("'\'+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }

    @RequestMapping(value = "/sdhr01/save/{id}")
    public Object saveTsdhr01New(HttpServletRequest request){
        Tsdhr01Request tsdhr01Res =new Tsdhr01Request();
        String reqNo=request.getParameter("reqNo").trim();
        tsdhr01Res.setReqNo(reqNo);
        return tsdhr01Service.saveTsdhr01(tsdhr01Res);
    }



}
