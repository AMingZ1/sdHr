package com.sd.sdhr.controller.sd.er;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.er.common.*;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder03Service;
import com.sd.sdhr.service.sd.er.Tsder04Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/Sder03")
@CrossOrigin
public class Tsder03Controller {

    @Autowired
    private Tsder03Service tsder03Service;

    @Autowired
    private Tsder04Service tsder04Service;

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

    //导出
    @RequestMapping(value = "/export")
    public void exportTsdhrXls(Tsder03Request tsder03, HttpServletResponse response) throws Exception{

        try {
            List<Tsder03> allTsder03 = tsder03Service.queryTsder03s(tsder03);
            Tsder04Request tsder04Request = new Tsder04Request();
            tsder04Request.setMemberId(tsder03.getMemberId());
            tsder04Request.setTalkType("T01");// 周访谈
            List<Tsder04> mapsWeak = tsder04Service.queryTsder04s(tsder03,tsder04Request);
            List<Tsder04ExpWeek> er04ExpWeeks=new ArrayList();

            for (Tsder04 er04:mapsWeak) {
                Tsder04ExpWeek er04ExpWeek = new Tsder04ExpWeek();
                BeanUtils.copyProperties(er04,er04ExpWeek);
                er04ExpWeeks.add(er04ExpWeek);
            }

            tsder04Request.setTalkType("T02");// 月访谈
            List<Tsder04> mapsMonth = tsder04Service.queryTsder04s(tsder03,tsder04Request);
            List<Tsder04ExpMonth> er04ExMonths =new ArrayList();
            Tsder04ExpMonth er04ExpMonth = new Tsder04ExpMonth();
            for (Tsder04 er04:mapsMonth) {
                BeanUtils.copyProperties(er04,er04ExpMonth);
                er04ExMonths.add(er04ExpMonth);
            }

            tsder04Request.setTalkType("T03");// 转正访谈
            List<Tsder04> mapsFormals = tsder04Service.queryTsder04s(tsder03,tsder04Request);
            List<Tsder04ExpFormal> er04ExFormalS =new ArrayList();
            Tsder04ExpFormal er04ExpFormal = new Tsder04ExpFormal();
            for (Tsder04 er04:mapsFormals) {
                BeanUtils.copyProperties(er04,er04ExpFormal);
                er04ExFormalS.add(er04ExpFormal);
            }

            tsder04Request.setTalkType("T04");// 年度访谈
            List<Tsder04> maps = tsder04Service.queryTsder04s(tsder03,tsder04Request);

            this.setExcelRespProp(response, "员工访谈信息");
            //EasyExcel.write(response.getOutputStream(), Tsder03.class).sheet("sheet1").doWrite(allTsder03);
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
            WriteSheet sheet1 = EasyExcel.writerSheet(0, "员工访谈主信息").head(Tsder03.class).build();
            WriteSheet sheet2 = EasyExcel.writerSheet(1, "周访谈").head(Tsder04ExpWeek.class).build();
            WriteSheet sheet3 = EasyExcel.writerSheet(2, "第一月访谈").head(Tsder04ExpMonth.class).build();
            WriteSheet sheet4 = EasyExcel.writerSheet(3, "转正访谈").head(Tsder04ExpFormal.class).build();
            WriteSheet sheet5 = EasyExcel.writerSheet(4, "年度访谈").head(Tsder04.class).build();

            excelWriter.write(allTsder03,sheet1);
            excelWriter.write(er04ExpWeeks,sheet2);
            excelWriter.write(er04ExMonths,sheet3);
            excelWriter.write(er04ExFormalS,sheet4);
            excelWriter.write(maps,sheet5);
            excelWriter.finish();
            response.flushBuffer();

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

}
