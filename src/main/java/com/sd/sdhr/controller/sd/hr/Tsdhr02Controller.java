package com.sd.sdhr.controller.sd.hr;

import com.alibaba.excel.EasyExcel;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst03;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.hr.Tsdhr01Service;
import com.sd.sdhr.service.sd.hr.Tsdhr02Service;
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
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/Sdhr02")
@CrossOrigin
public class Tsdhr02Controller {

    @Autowired
    private Tsdhr02Service tsdhr02Service;

    @Autowired
    private Tsdhr01Service tsdhr01Service;
    @Autowired
    private Tsdst03Service tsdst03Service;

    @Value("${myfile.path}")
    private String path;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Autowired
    Tsdst12Service tsdst12Service;

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
    public Object importMemberList(@RequestPart("file") MultipartFile file) throws Exception {
        EiINfo outINfo = new EiINfo();
        try {

            List<Tsdhr02Upload> list = EasyExcel.read(file.getInputStream())
                    .head(Tsdhr02Upload.class)
                    .sheet()
                    .doReadSync();
            if (list==null || list.size()<1){
                throw new Exception("导入会错！没有获取到导入清单信息！");
            }
            // 往后端塞值
            outINfo = tsdhr02Service.saveTsdhr02sByImp(list);
        }catch (Exception e){
            outINfo.setSuccess("-1");
            outINfo.setMessage("导入失败！"+e.getMessage());
        }
        return outINfo;
    }

    @PostMapping("/uploadFile")
    public Object fileUpload(MultipartFile file, HttpServletRequest req, @RequestParam String planNo, @RequestParam String businessKeyword) {
        EiINfo outINfo = new EiINfo();
        Map<String, Object> resultMap = new HashMap<>();
        // 得到上传时的文件名
        String originName = file.getOriginalFilename();
        String fileName=originName.substring(0,originName.lastIndexOf("."));//文件名称
        String fileSuffName=originName.substring(originName.lastIndexOf("."));//文件后缀

        Tsdhr02 tsdhr02 = new Tsdhr02();
        try {
            String []   array= originName.split("_");
            String memberName=array[0];//人员姓名
            String reqNo=array[1];//岗位需求编号
            String contactStatus=array[2];//状态

            String tel=array[3].substring(0,array[3].lastIndexOf("."));//电话

            //生成tsdhr03表信息
            tsdhr02.setMemberName(memberName);//姓名
            tsdhr02.setTel(tel);//电话

            //状态
            Tsdst03 contact = tsdst03Service.selectTsdst032("sdHr_contactStatus","",contactStatus);
            if(contact == null){
                outINfo.setSuccess("-1");
                outINfo.setMessage("新增电联记录信息失败:状态信息填写错误！");
                return outINfo;
            }
            tsdhr02.setContactStatus(contact.getCodeEname());
            //岗位编号
            Tsdhr01 tsdhr01 = tsdhr01Service.queryTsdhr01ByReqNo(reqNo);
            if(tsdhr01 == null){
                outINfo.setSuccess("-1");
                outINfo.setMessage("新增电联记录信息失败:岗位编号填写错误,未查询到对应岗位信息！");
                return outINfo;
            }
            tsdhr02.setReqNo(tsdhr01.getReqNo());

            //联系人 联系时间
            Claims claims = JwtUtil.verifyJwt(request);
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String curDateTime = formatter.format(new Date());
            tsdhr02.setContactMember(userName);
            tsdhr02.setContactDate(curDateTime);



            planNo=tsdhr02Service.saveTsdhr022(tsdhr02);


        }catch (Exception e){
            log.error("新增人才库信息错误："+e);
            outINfo.setSuccess("-1");
            outINfo.setMessage("新增电联记录信息失败:文件格式错误：姓名_岗位需求编号_状态_电话");
            return outINfo;
        }

        resultMap.put("resultMap",planNo);
        resultMap.put("resultMap",businessKeyword);

        String filePath=path+ File.separator+businessKeyword;
        File folder = new File(filePath);
        if (!folder.exists()) {
            //如果文件目录不存在就重新创建
            folder.mkdirs();
        }
        try {
            // 保存文件对象，加上uuid是为了防止文件重名
            String strNewFileName=tsdst12Service.saveTsdst12(planNo,businessKeyword,filePath,fileName,fileSuffName);

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
