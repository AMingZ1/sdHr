package com.sd.sdhr.service.sd.st.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.st.Tsdst03Mapper;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst03;
import com.sd.sdhr.pojo.sd.st.Tsdst06;
import com.sd.sdhr.pojo.sd.st.common.Tsdst03Request;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.st.Tsdst03Service;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Tsdst03ServiceImpl implements Tsdst03Service {

    @Autowired
    Tsdst03Mapper tsdst03Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request
    private Object forEach;

    @Override
    public EiINfo getAllTsdst03(Tsdst03Request tsdst03Request) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsdst03> queryWrapper=new QueryWrapper<>();
            //queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            queryWrapper.eq(!StringUtils.isEmpty(tsdst03Request.getCodeNo()),"CODE_NO",tsdst03Request.getCodeNo());
            queryWrapper.eq(!StringUtils.isEmpty(tsdst03Request.getCodeEname()),"CODE_ENAME",tsdst03Request.getCodeEname());
            //queryWrapper.select("CODE_ENAME","CODE_CNAME");
            //模糊查询条件

            PageHelper.startPage(tsdst03Request.getPageNum(),tsdst03Request.getPageSize());
            List<Tsdst03> list=tsdst03Mapper.selectList(queryWrapper);
            if (CollectionUtils.isEmpty(list)){
                throw new Exception("返回结果为null");
            }
            PageInfo pageInfo=new PageInfo(list);
            eiINfo.setMessage("查询成功!");
            eiINfo.setTotalNum(pageInfo.getTotal());
            eiINfo.setData(list);
            eiINfo.setSuccess("1");
        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("查询失败!"+e);
        }
        return eiINfo;
    }

    @Override
    public Tsdst03 selectTsdst03ById(String codeNo,String codeEname) throws Exception {

        QueryWrapper<Tsdst03> queryWrapper=new QueryWrapper<>();
        //queryWrapper.ne("Delete_Flag","1");//删除标记不为1
        queryWrapper.eq("CODE_NO",codeNo);
        queryWrapper.eq("CODE_ENAME",codeEname);
        List<Tsdst03> list=tsdst03Mapper.selectList(queryWrapper);
        if (list.size()!=1){
            throw new Exception("返回结果为 空");
        }
        return list.get(0);
    }

    @Override
    public EiINfo saveTsdst03(Tsdst03 tsdst03) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdst03.getCodeNo())){
                throw new Exception("【代码编号】为空无法新增！");
            }
            if (StringUtils.isEmpty(tsdst03.getCodeEname())){
                throw new Exception("【代码英文名】为空无法新增！");
            }
            //拿到当前年月日；
            Calendar calendar = Calendar.getInstance();
            // 获取当前年
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            // 获取当前月
            int month = calendar.get(Calendar.MONTH) + 1;
            StringBuilder memberId=new StringBuilder();
            StringBuilder mpRelationNo=new StringBuilder();
            String an=year.substring(year.length()-2);

            // 注入信息
            Claims claims = JwtUtil.verifyJwt(request);
            String userId = claims.get("userId").toString();
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdst03.setRecCreator(userId);
            tsdst03.setRecCreateName(userName);
            tsdst03.setRecCreateTime(curDateTime);
            tsdst03.setRecModifier(userId);
            tsdst03.setRecModifyName(userName);
            tsdst03.setRecModifyTime(curDateTime);
            tsdst03.setDeleteFlag("0");
            int backInsert =tsdst03Mapper.insert(tsdst03);
            eiINfo.setMessage(String.valueOf(backInsert));
            if (backInsert==1){
                eiINfo.setMessage("新增成功！");
            }else {
                eiINfo.setMessage("新增失败！");
            }

        }catch (Exception e){
            eiINfo.setMessage("新增失败！"+e);
        }
        return eiINfo;
    }

    @Override
    public Map<String, String> selectTsdst03ToMap(String codeNo, String codeEname) throws Exception {
        QueryWrapper<Tsdst03> queryWrapper=new QueryWrapper<>();
        //queryWrapper.ne("Delete_Flag","1");//删除标记不为1
        queryWrapper.eq("CODE_NO",codeNo);
        queryWrapper.eq(!StringUtils.isEmpty(codeEname),"CODE_ENAME",codeEname);
        Map<String,String> hashMap = new HashMap();
        List<Tsdst03> st03s = tsdst03Mapper.selectList(queryWrapper);
        if (st03s.size()<1){
            throw new Exception("返回结果为 空");
        }
        for (Tsdst03 st03:st03s){
            hashMap.put(st03.getCodeEname(),st03.getCodeCname());
        }

        return hashMap;
    }
}
