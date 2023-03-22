package com.sd.sdhr.controller.sd.er;

import com.alibaba.excel.EasyExcel;
import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.common.Tsder01Request;
import com.sd.sdhr.pojo.sd.er.common.Tsder02Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder02Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/Sder02")
@CrossOrigin
public class Tsder02Controller {

    @Autowired
    private Tsder02Service tsder02Service;

    @RequestMapping(value = "/querySder02")
    public Object queryTsder02List(Tsder02Request tsder02){
        return tsder02Service.getAllTsder02(tsder02);
    }

    @RequestMapping(value = "/saveSder02")
    public Object saveTsder02(@RequestBody Tsder02 tsder02){
        log.info("入参信息："+tsder02);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder02Service.saveTsder02(tsder02);
        }catch (Exception e){
            log.error("新增人员项目失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增人员项目失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/updateSder02")
    public Object updateTsder02(@RequestBody Tsder02 tsder02){
        log.info("入参信息："+tsder02);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder02Service.updateTsder02(tsder02);
        }catch (Exception e){
            log.error("修改人员项目失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("修改人员项目失败！"+e.getMessage());
        }
        return outINfo;
    }

    @RequestMapping(value = "/deleteSder02")
    public Object deleteTsder02(@RequestBody Tsder02 tsder02){
        log.info("入参信息："+tsder02);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsder02Service.deleteTsder02ByMap(tsder02);
        }catch (Exception e){
            log.error("删除人员项目失败："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("删除人员项目失败！"+e.getMessage());
        }
        return outINfo;
    }

    //导出
    @RequestMapping(value = "/export")
    public void exportTsdhrXls(Tsder02Request tsder02, HttpServletResponse response){

        try {
            List<Tsder02> allTsder02 = tsder02Service.queryTsder02s(tsder02);
            this.setExcelRespProp(response, "员工项目信息");

            EasyExcel.write(response.getOutputStream(), Tsder02.class).sheet("sheet1").doWrite(allTsder02);

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
