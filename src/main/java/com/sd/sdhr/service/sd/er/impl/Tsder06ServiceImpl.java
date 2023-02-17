package com.sd.sdhr.service.sd.er.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.er.Tsder06Mapper;
import com.sd.sdhr.pojo.sd.er.Tsder05;
import com.sd.sdhr.pojo.sd.er.Tsder06;
import com.sd.sdhr.pojo.sd.er.common.Tsder06Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.er.Tsder06Service;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Tsder06ServiceImpl implements Tsder06Service {

    @Autowired
    private Tsder06Mapper tsder06Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsder06(Tsder06Request tsder06) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsder06> queryWrapper=new QueryWrapper<>();
            queryWrapper.like(!StringUtils.isEmpty(tsder06.getYear()),"YEAR",tsder06.getYear());

            PageHelper.startPage(tsder06.getPageNum(),tsder06.getPageSize());
            List<Tsder06> list=tsder06Mapper.selectList(queryWrapper);
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
    public Tsder06 selectTsder06ById(Tsder06 tsder06) {
        return null;
    }

    @Override
    public EiINfo saveTsder06(Tsder06 tsder06) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsder06.getYear())){
                throw new Exception("【年度】为空无法新增！");
            }

            //查询当前人员编号是否已经生成访谈主信息
            QueryWrapper<Tsder06> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("MEMBER_ID",tsder06.getYear());
            int backNum=tsder06Mapper.selectCount(queryWrapper);
            if (backNum!=0){
                throw new Exception("当前年度的时间节点已经维护，无法再新增，年度："+tsder06.getYear());
            }
            // 注入信息
            Claims claims = JwtUtil.verifyJwt(request);
            String userId = claims.get("userId").toString();
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsder06.setRecCreator(userId);
            tsder06.setRecCreateName(userName);
            tsder06.setRecCreateTime(curDateTime);
            tsder06.setRecModifier(userId);
            tsder06.setRecModifyName(userName);
            tsder06.setRecModifyTime(curDateTime);
            tsder06.setDeleteFlag("0");
            int backInsert =tsder06Mapper.insert(tsder06);
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
    public EiINfo deleteTsder06ByMap(Tsder06 tsder06) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsder06.getYear())){
                throw new Exception("主键【年度】为空无法删除！");
            }
            tsder06Mapper.deleteById(tsder06.getYear());

            eiINfo.setSuccess("1");
            eiINfo.setMessage("删除成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("删除失败！"+e);
        }
        return eiINfo;
    }

    @Override
    public EiINfo updateTsder06(Tsder06 tsder06) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsder06.getYear())){
                throw new Exception("主键【年度】为空无法修改！");
            }
            UpdateWrapper<Tsder06> wrapper=new UpdateWrapper<>();
            wrapper.eq("YEAR",tsder06.getYear());
            Tsder06 tsder06Up=new Tsder06();
            tsder06Up.setNode1(tsder06.getNode1());
            tsder06Up.setNode2(tsder06.getNode2());
            tsder06Up.setRemark(tsder06.getRemark());

            Claims claims = JwtUtil.verifyJwt(request);
            String userId = claims.get("userId").toString();
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsder06Up.setRecModifyName(userName);
            tsder06Up.setRecModifier(userId);
            tsder06Up.setRecModifyTime(curDateTime);
            tsder06Mapper.update(tsder06Up,wrapper);

            eiINfo.setSuccess("1");
            eiINfo.setMessage("修改成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("修改失败！"+e);
        }
        return eiINfo;
    }
}
