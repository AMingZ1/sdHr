package com.sd.sdhr.controller.sd.er;

import com.alibaba.excel.EasyExcel;
import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.common.Tsder01Request;
import com.sd.sdhr.pojo.sd.er.common.Tsder01Upload;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Export;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder01Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/Sder01")
@CrossOrigin
public class Tsder01Controller {

    @Autowired
    private Tsder01Service tsder01Service;

    @RequestMapping(value = "/querySder01")
    public Object queryTsder01List(Tsder01Request tsder01){
        return tsder01Service.getAllTsder01(tsder01);
    }

    @RequestMapping(value = "/saveSder01")
    public Object saveTsder01(@RequestBody Tsder01 tsder01){
        log.info("新增岗位信息："+tsder01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder01Service.saveTsder01(tsder01);
        }catch (Exception e){
            log.error("新增岗位信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSder01")
    public Object updateTsder01(@RequestBody Tsder01 tsder01){
        log.info("新增岗位信息："+tsder01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder01Service.updateTsder01(tsder01);
        }catch (Exception e){
            log.error("修改信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("修改操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSder01")
    public Object deleteTsder01(@RequestBody Tsder01 tsder01){
        log.info("新增岗位信息："+tsder01);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder01Service.deleteTsder01ByMap(tsder01);
        }catch (Exception e){
            log.error("删除信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("操作失败！"+e.getMessage());
        }
        return outINfo;
    }


    //导出
    @RequestMapping(value = "/export")
    public void exportTsdhrXls(Tsder01Request tsder01, HttpServletResponse response){

        try {
            //List<Tsdhr01> allTsdhr01 = tsdhr01Service.queryExportAllTsdhr01(tsdhr01);
            List<Tsder01> allTsder01 = tsder01Service.queryTsder01s(tsder01);
            //将User映射为导出的实体类
           /*List<Tsder01Upload> tsder01ss=new ArrayList();
            for (Tsder01 the01:allTsder01){
                Tsder01Upload userExcel=new Tsder01Upload();
                BeanUtils.copyProperties(the01,userExcel);
                tsder01ss.add(userExcel);
            }*/
            this.setExcelRespProp(response, "员工信息");

            EasyExcel.write(response.getOutputStream(), Tsder01.class).sheet("sheet1").doWrite(allTsder01);

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
        List<Tsder01Upload> list = EasyExcel.read(file.getInputStream())
                .head(Tsder01Upload.class)
                .sheet()
                .doReadSync();
        //List<Tsdhr01Upload> list2 =EasyExcel.read(file.getInputStream(), Tsdhr01Upload.class, null).sheet(0).doReadSync();
        // 往后端塞值
        if (list==null || list.size()<1){
            throw new Exception("导入会错！没有获取到导入清单信息！");
        }
        EiINfo eiINfo = tsder01Service.saveTsder01sByImp(list);
    }


}
