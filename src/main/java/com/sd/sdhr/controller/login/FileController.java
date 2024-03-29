package com.sd.sdhr.controller.login;

import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst12;
import com.sd.sdhr.service.sd.st.Tsdst12Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: FileController
 * @Author dems
 * @Package com.sd.sdhr.controller.login
 * @Date 2023/2/20 13:59
 * @description: ${description}
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/file")
public class FileController {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

    @Value("${myfile.path}")
    private String path;//="D:\\A_linshi";//"\test\\ImpFile";

    @Autowired
    Tsdst12Service tsdst12Service;

    @RequestMapping(value = "/getAllFiles")
    private Object getAllMsgNotificationByUser(Tsdst12 tsdst12){
        log.info("查询附件清单信息："+tsdst12);

        return tsdst12Service.getAllTsdst12(tsdst12);
    }

    @PostMapping("/upload")
    public Map<String, Object> fileUpload(MultipartFile file, HttpServletRequest req,@RequestParam String businessNo,@RequestParam String businessKeyword) {

        Map<String, Object> resultMap = new HashMap<>();
        // 得到上传时的文件名
        String originName = file.getOriginalFilename();
        String fileName=originName.substring(0,originName.lastIndexOf("."));//文件名称
        String fileSuffName=originName.substring(originName.lastIndexOf("."));//文件后缀
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
        //String uploadDir = req.getSession().getServletContext().getRealPath("/") + "/upload/" + strFormat;

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
        return resultMap;
    }


    @RequestMapping("/downloadFile")
    public void downloadFiles(@RequestParam("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) {
        //@RequestParam("file") String downUrl
        log.info("文件ID为：{}",fileId);
        /*String businessKeyword=params.get("businessKeyword");
        String businessNo=params.get("businessNo");*/
        Tsdst12 tsdst12=new Tsdst12();
        tsdst12.setFileId(fileId);
        List<Tsdst12> list=tsdst12Service.getAllTsdst12(tsdst12);
        if (list.size()==0){
            // 查无附件信息
        }
        Tsdst12 tsdst12New =list.get(0);
        String downUrl=tsdst12New.getFilePath()+File.separator+tsdst12New.getFileId()+tsdst12New.getFileSuffix();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] bytes = new byte[1024];
        File file = new File(downUrl);
        String fileName = file.getName();
        //logger.info("本次下载的文件为" + downUrl);
        // 获取输出流
        try {
            // StandardCharsets.ISO_8859_1 *=UTF-8'
            // response.setHeader("Content-Disposition", "attachment;filename=" +  new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 以流的形式返回文件
            response.setContentType("application/octet-stream;charset=utf-8");
            inputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(inputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(bytes);
            while (i != -1) {
                outputStream.write(bytes, 0, i);
                i = bufferedInputStream.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    @RequestMapping("/removeFile")
    public void remove(@RequestParam("fileId") String fileId){
        //@RequestParam("file") String downUrl
        log.info("文件ID为：{}",fileId);

        Tsdst12 tsdst12=new Tsdst12();
        tsdst12.setFileId(fileId);
        List<Tsdst12> list=tsdst12Service.getAllTsdst12(tsdst12);
        if (list.size()==0){
            // 查无附件信息
        }
        Tsdst12 tsdst12New =list.get(0);
        String downUrl=tsdst12New.getFilePath()+File.separator+tsdst12New.getFileId()+tsdst12New.getFileSuffix();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] bytes = new byte[1024];
        File file = new File(downUrl);
        if(file.exists()){
            // 上传的文件被保存了
            file.delete();
            tsdst12Service.delectTsdst12ByFileId(fileId);
            // 打印日志
            log.info("{}删除成功",tsdst12New.getFileName());
        }
        // 待完成 —— 文件类型校验工作
    }

}