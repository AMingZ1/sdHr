package com.sd.sdhr.controller.sd.hr;

import com.alibaba.excel.EasyExcel;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Upload;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr02Service;
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
            outINfo.setMessage("新增操作失败！"+e.getMessage());
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
            outINfo.setMessage("修改操作失败！"+e.getMessage());
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
            outINfo.setMessage("删除操作失败！"+e.getMessage());
        }
        return outINfo;
    }

    //导出
    @RequestMapping(value = "/export")
    public void exportTsdhrXls(Tsdhr02Request tsdhr02, HttpServletResponse response){

        try {
            List<Tsdhr02> allTsdhr02 = tsdhr02Service.queryTsdhr02s(tsdhr02);
            //将User映射为导出的实体类
            this.setExcelRespProp(response, "电联记录");

            EasyExcel.write(response.getOutputStream(),Tsdhr02.class).sheet("sheet1").doWrite(allTsdhr02);

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
        List<Tsdhr02Upload> list = EasyExcel.read(file.getInputStream())
                .head(Tsdhr01Upload.class)
                .sheet()
                .doReadSync();
        if (list==null || list.size()<1){
            throw new Exception("导入会错！没有获取到导入清单信息！");
        }
        // 往后端塞值
        EiINfo eiINfo = tsdhr02Service.saveTsdhr02sByImp(list);
    }


}
