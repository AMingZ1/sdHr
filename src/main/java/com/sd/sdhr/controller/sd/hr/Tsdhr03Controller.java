package com.sd.sdhr.controller.sd.hr;

import com.alibaba.excel.EasyExcel;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.common.*;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr03Service;
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

    //导出
    @RequestMapping(value = "/export")
    public void exportTsdhrXls(Tsdhr03Request tsdhr03, HttpServletResponse response){

        try {
            List<Tsdhr03> allTsdhr03 = tsdhr03Service.queryTsdhr03s(tsdhr03);
            //将User映射为导出的实体类
            this.setExcelRespProp(response, "人才库清单");

            EasyExcel.write(response.getOutputStream(),Tsdhr03.class).sheet("sheet1").doWrite(allTsdhr03);

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
        List<Tsdhr03Upload> list = EasyExcel.read(file.getInputStream())
                .head(Tsdhr01Upload.class)
                .sheet()
                .doReadSync();
        // 往后端塞值
        EiINfo eiINfo = tsdhr03Service.saveTsdhr03sByImp(list);
    }

}
