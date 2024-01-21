package com.sd.sdhr.controller.sd.hr;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.common.*;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst03;
import com.sd.sdhr.service.sd.hr.Tsdhr03Service;
import com.sd.sdhr.service.sd.st.Tsdst03Service;
import com.sd.sdhr.service.sd.st.Tsdst12Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/Sdhr03")
@CrossOrigin
public class Tsdhr03Controller {

    @Autowired
    private Tsdhr03Service tsdhr03Service;

    @Autowired
    private Tsdst03Service tsdst03Service;

    @Value("${myfile.path}")
    private String path;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

    @Autowired
    Tsdst12Service tsdst12Service;

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

    @RequestMapping(value = "/deletesSdhr03")
    public Object deletesTsdhr03(@RequestBody Tsdhr03 tsdhr03){
        log.info("删除人才库信息："+tsdhr03);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr03Service.deleteTsdhr03ByMemberNos(tsdhr03.getMemberNo());
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
                .head(Tsdhr03Upload.class)
                .sheet()
                .doReadSync();
        if (list==null || list.size()<1){
            throw new Exception("导入会错！没有获取到导入清单信息！");
        }
        // 往后端塞值
        EiINfo eiINfo = tsdhr03Service.saveTsdhr03sByImp(list);
    }


    @PostMapping("/uploadFile")
    public Object fileUpload(MultipartFile file, HttpServletRequest req, @RequestParam String businessNo, @RequestParam String businessKeyword) {
        EiINfo outINfo = new EiINfo();
        Map<String, Object> resultMap = new HashMap<>();
        // 得到上传时的文件名
        String originName = file.getOriginalFilename();
        String fileName=originName.substring(0,originName.lastIndexOf("."));//文件名称
        String fileSuffName=originName.substring(originName.lastIndexOf("."));//文件后缀

        Tsdhr03 tsdhr03 = new Tsdhr03();
        try {
            String []   array= originName.split("_");
            String memberName=array[0];//人员姓名
            String deptName=array[1];//部门
            String itvJob=array[2];//岗位
            String workYear=array[3].substring(0,array[3].lastIndexOf("."));//工作年限

            //生成tsdhr03表信息
            tsdhr03.setMemberName(memberName);//姓名
            //面试部门
            Tsdst03 dept = tsdst03Service.selectTsdst032("sdHr_deptName","",deptName);
            if(dept == null){
                Tsdst03 temp= new Tsdst03();
                temp.setCodeCname(deptName);
                temp.setCodeNo("sdHr_deptName");
                dept = tsdst03Service.saveTsdst032(temp);
            }
            tsdhr03.setDeptName(dept.getCodeEname());
            //面试岗位
            Tsdst03 job = tsdst03Service.selectTsdst032("sdHr_jobName","",itvJob);
            if(job == null){
                Tsdst03 temp= new Tsdst03();
                temp.setCodeCname(itvJob);
                temp.setCodeNo("sdHr_jobName");
                job = tsdst03Service.saveTsdst032(temp);
            }
            tsdhr03.setItvJob(job.getCodeEname());
            tsdhr03.setArchiveReason("10");
            tsdhr03.setArchiveStatusbfr("08");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr03.setArchiveDate(curDateTime);
            tsdhr03.setWorkYear(workYear);
        }catch (Exception e){
            log.error("新增人才库信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增人才库信息失败:文件格式错误：姓名_面试部门_面试岗位_工作年限");
            return outINfo;
        }

        try {
            //新增人员
            businessNo = tsdhr03Service.saveTsdhr032(tsdhr03);
        }catch (Exception e){
            log.error("新增人才库信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增人才库信息失败:"+e.getMessage());
            return outINfo;
        }

        //获取文件后缀
        /*if (!originName.endsWith(".pdf")) {
            resultMap.put("status", "error");
            resultMap.put("msg", "文件类型不对，必须为pdf");
            return resultMap;
        }*/

        resultMap.put("resultMap",businessNo);
        resultMap.put("resultMap",businessKeyword);


        String filePath=path+File.separator+businessKeyword;
        String strFormat = simpleDateFormat.format(new Date());
        String realPath = req.getServletContext().getRealPath("/") + strFormat;
        File folder = new File(filePath);
        if (!folder.exists()) {
            //如果文件目录不存在就重新创建
            folder.mkdirs();
        }
        try {
            // 保存文件对象，加上uuid是为了防止文件重名
            String strNewFileName=tsdst12Service.saveTsdst12(businessNo,businessKeyword,filePath,fileName,fileSuffName);

            file.transferTo(new File(folder, strNewFileName+fileSuffName));
            String strUrl = folder.getPath(); //req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + strFormat + strNewFileName;
            resultMap.put("success", "true");
            resultMap.put("url", strUrl);
            resultMap.put("fileName", originName);
            resultMap.put("newFileName", strNewFileName);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status", "false");
            resultMap.put("msg", e.getMessage());
            log.error("{}",e);
        }
        log.info("文件上传成功：{}",resultMap.toString());
        outINfo.setMessage("文件上传成功!");
        outINfo.setSuccess("1");
        return outINfo;
    }



}
