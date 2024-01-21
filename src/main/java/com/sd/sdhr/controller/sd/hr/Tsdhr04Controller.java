package com.sd.sdhr.controller.sd.hr;

import com.alibaba.excel.EasyExcel;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.common.*;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst03;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.hr.Tsdhr02Service;
import com.sd.sdhr.service.sd.hr.Tsdhr04Service;
import com.sd.sdhr.service.sd.st.Tsdst03Service;
import com.sd.sdhr.service.sd.st.Tsdst12Service;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/Sdhr04")
@CrossOrigin
public class Tsdhr04Controller {

    @Autowired
    private Tsdhr04Service tsdhr04Service;

    @Autowired
    private Tsdhr02Service tsdhr02Service;
    @Autowired
    private Tsdst03Service tsdst03Service;

    @Value("${myfile.path}")
    private String path;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Autowired
    Tsdst12Service tsdst12Service;


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
     * 批量删除面试测评信息.
     * @param tsdhr04：面试测评对象
     * @return
     */
    @RequestMapping(value = "/deletesSdhr04")
    public Object deletesTsdhr04( Tsdhr04 tsdhr04){
        log.info("删除面试测评信息："+tsdhr04);
        EiINfo outINfo = new EiINfo();
        try {
            outINfo=tsdhr04Service.deleteTsdhr04ByItvNos(tsdhr04.getItvNo());
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



    @PostMapping("/uploadFile")
    public Object fileUpload(MultipartFile file, HttpServletRequest req, @RequestParam String itvNo, @RequestParam String businessKeyword) {
        EiINfo outINfo = new EiINfo();
        Map<String, Object> resultMap = new HashMap<>();
        // 得到上传时的文件名
        String originName = file.getOriginalFilename();
        String fileName=originName.substring(0,originName.lastIndexOf("."));//文件名称
        String fileSuffName=originName.substring(originName.lastIndexOf("."));//文件后缀

        Tsdhr04 tsdhr04 = new Tsdhr04();
        try {
            String []   array= originName.split("_");
            String memberName=array[0];//人员姓名
            String planNo=array[1];//电联记录号
            String itvStatusId=array[2].substring(0,array[2].lastIndexOf("."));//面试结果

            //生成tsdhr03表信息
            tsdhr04.setMemberName(memberName);//姓名


            //面试结果
            Tsdst03 contact = tsdst03Service.selectTsdst032("sdHr_itvStatus","",itvStatusId);
            if(contact == null){
                outINfo.setSuccess("-1");
                outINfo.setMessage("新增,面试测评信息失败:面试结果信息填写错误！");
                return outINfo;
            }
            tsdhr04.setItvStatus(contact.getCodeEname());

            //电联记录信息
            Tsdhr02 tsdhr02 = tsdhr02Service.queryTsdhr02ByPlanNo(planNo);
            if(tsdhr02 != null){
                tsdhr04.setItvDept(tsdhr02.getDeptName());
                tsdhr04.setItvJob(tsdhr02.getItvJob());
                tsdhr04.setItvDate(tsdhr02.getItvDate());
                tsdhr04.setTel(tsdhr02.getTel());
                tsdhr04.setHopeSalary(new BigDecimal(tsdhr02.getHopeSalary()));
            }
            tsdhr04.setPlanNo(planNo);

            //面试官
            Claims claims = JwtUtil.verifyJwt(request);
            String userName =  claims.get("userName").toString();
            tsdhr04.setItver(userName);

            itvNo=tsdhr04Service.saveTsdhr042(tsdhr04);


        }catch (Exception e){
            log.error("新增面试测评信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增人才库信息失败:文件格式错误：姓名_电联记录编号_面试结果");
            return outINfo;
        }

        resultMap.put("resultMap",itvNo);
        resultMap.put("resultMap",businessKeyword);

        String filePath=path+ File.separator+businessKeyword;
        File folder = new File(filePath);
        if (!folder.exists()) {
            //如果文件目录不存在就重新创建
            folder.mkdirs();
        }
        try {
            // 保存文件对象，加上uuid是为了防止文件重名
            String strNewFileName=tsdst12Service.saveTsdst12(itvNo,businessKeyword,filePath,fileName,fileSuffName);

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
