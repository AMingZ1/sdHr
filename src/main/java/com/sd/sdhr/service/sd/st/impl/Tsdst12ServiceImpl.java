package com.sd.sdhr.service.sd.st.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sd.sdhr.mapper.sd.st.Tsdst12Mapper;
import com.sd.sdhr.pojo.sd.st.Tsdst12;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.st.Tsdst12Service;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: Tsdst12ServiceImpl
 * @Author dems
 * @Package com.sd.sdhr.service.sd.st.impl
 * @Date 2023/2/20 14:29
 * @description: ${description}
 */
@Service
public class Tsdst12ServiceImpl implements Tsdst12Service {

    @Autowired
    Tsdst12Mapper tsdst12Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public List<Tsdst12> getAllTsdst12(Tsdst12 tsdst12) {
        List<Tsdst12> list =new ArrayList();
        try {
            QueryWrapper<Tsdst12> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq(!StringUtils.isEmpty(tsdst12.getBusinessNo()),"BUSINESS_NO",tsdst12.getBusinessNo());
            queryWrapper.eq(!StringUtils.isEmpty(tsdst12.getBusinessKeyword()),"BUSINESS_KEYWORD",tsdst12.getBusinessKeyword());
            queryWrapper.eq(!StringUtils.isEmpty(tsdst12.getFileId()),"FILE_ID",tsdst12.getFileId());

            list=tsdst12Mapper.selectList(queryWrapper);

        }catch (Exception e){

        }
        return list;
    }

    @Override
    public String saveTsdst12(String businessNo,String businessKeyword,String filePath,String fileName,String fileSuffix) {
        Tsdst12 tsdst12=new Tsdst12();
        tsdst12.setBusinessNo(businessNo);
        tsdst12.setBusinessKeyword(businessKeyword);
        tsdst12.setFileName(fileName);
        tsdst12.setFilePath(filePath);
        tsdst12.setFileSuffix(fileSuffix);
        // 保存文件对象，加上uuid是为了防止文件重名
        String fileId = UUID.randomUUID().toString().replaceAll("-", "");
        tsdst12.setFileId(fileId);
        // 注入信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdst12.setRecCreator(userId);
        tsdst12.setRecCreateName(userName);
        tsdst12.setRecCreateTime(curDateTime);
        tsdst12.setRecModifier(userId);
        tsdst12.setRecModifyName(userName);
        tsdst12.setRecModifyTime(curDateTime);
        tsdst12.setDeleteFlag("0");
        tsdst12Mapper.insert(tsdst12);

        return fileId;
    }

    @Override
    public void delectTsdst12ByFileId(String fileId) {
        Map map = new HashMap();
        map.put("fileId",fileId);
        int i = tsdst12Mapper.deleteByMap(map);
    }
}
