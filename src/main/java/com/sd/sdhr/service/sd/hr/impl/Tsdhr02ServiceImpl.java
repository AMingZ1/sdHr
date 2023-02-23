package com.sd.sdhr.service.sd.hr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.hr.Tsdhr02Mapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.hr.Tsdhr02Service;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class Tsdhr02ServiceImpl implements Tsdhr02Service {

    @Autowired
    private Tsdhr02Mapper tsdhr02Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsdhr02(Tsdhr02Request tsdhr02) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsdhr02> queryWrapper=new QueryWrapper<>();
            List<String> contactStatusList= new ArrayList<String>();
            if (tsdhr02.getContactStatus()!=null ) {
                String a[]= tsdhr02.getContactStatus().split(",");
                for (int i = 0; i < a.length; i++) {
                    contactStatusList.add(a[i]);
                }
            }
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getPlanNo()),"PLAN_NO",tsdhr02.getPlanNo());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getMemberName()),"MEMBER_NAME",tsdhr02.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getReqNo()),"REQ_NO",tsdhr02.getReqNo());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getDeptName()),"DEPT_NAME",tsdhr02.getDeptName());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getItvJob()),"ITV_JOB",tsdhr02.getItvJob());
            if (contactStatusList.size()>0){
                queryWrapper.in(!StringUtils.isEmpty(tsdhr02.getContactStatus()),"CONTACT_STATUS",contactStatusList);
            }
            PageHelper.startPage(tsdhr02.getPageNum(),tsdhr02.getPageSize());
            List<Tsdhr02> list=tsdhr02Mapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(list)){
                PageInfo pageInfo=new PageInfo(list);
                eiINfo.setMessage("查询成功!");
                eiINfo.setTotalNum(pageInfo.getTotal());
                eiINfo.setData(list);
                eiINfo.setSuccess("1");
            }
        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("查询失败!"+e);
        }
        return eiINfo;
    }

    @Override
    public Tsdhr02 selectTsdhr02ById(Tsdhr02 tsdhr02) {
        return tsdhr02Mapper.selectById(tsdhr02.getPlanNo());
    }

    @Override
    public EiINfo saveTsdhr02(Tsdhr02 tsdhr02) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (!StringUtils.isEmpty(tsdhr02.getPlanNo())){
                throw new Exception("电联记录号不为空无法新增！面试记录号："+tsdhr02.getPlanNo());
            }
            //拿到当前年月日；
            Calendar calendar = Calendar.getInstance();
            // 获取当前年
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            // 获取当前月
            int month = calendar.get(Calendar.MONTH) + 1;
            StringBuilder planNo=new StringBuilder();
            String an=year.substring(year.length()-2);
            planNo=planNo.append(an).append("B").append(month);
            //查询当前生成流水号信息
            int backNum=tsdhr02Mapper.queryCountByPlanNoLike(planNo.toString());
            String serialNum= String.format("%04d", backNum+1);
            planNo.append(serialNum);
            tsdhr02.setPlanNo(planNo.toString());
            // 注入信息
            Claims claims = JwtUtil.verifyJwt(request);
            String userId = claims.get("userId").toString();
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr02.setRecCreator(userId);
            tsdhr02.setRecCreateName(userName);
            tsdhr02.setRecCreateTime(curDateTime);
            tsdhr02.setRecModifier(userId);
            tsdhr02.setRecModifyName(userName);
            tsdhr02.setRecModifyTime(curDateTime);
            tsdhr02.setDeleteFlag("0");
            int backInsert =tsdhr02Mapper.insert(tsdhr02);
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
    public EiINfo deleteTsdhr02ByMap(Tsdhr02 tsdhr02) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdhr02.getPlanNo())){
                throw new Exception("电联记录号为空无法删除！");
            }
            UpdateWrapper<Tsdhr02> wrapper=new UpdateWrapper<>();
            wrapper.eq("PLAN_NO",tsdhr02.getPlanNo());
            Tsdhr02 tsdhr02Up=new Tsdhr02();
            Claims claims = JwtUtil.verifyJwt(request);
            String userId = claims.get("userId").toString();
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr02Up.setDeleteFlag("1");
            tsdhr02Up.setDeleteName(userName);
            tsdhr02Up.setDeleter(userId);
            tsdhr02Up.setDeleteTime(curDateTime);
            tsdhr02Mapper.update(tsdhr02Up,wrapper);
            eiINfo.setSuccess("1");
            eiINfo.setMessage("删除成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("删除失败！"+e);
        }


        return eiINfo;
    }

    @Override
    public EiINfo updateTsdhr02(Tsdhr02 tsdhr02) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdhr02.getPlanNo())){
                throw new Exception("电联记录号为空无法修改！");
            }
            UpdateWrapper<Tsdhr02> wrapper=new UpdateWrapper<>();
            wrapper.eq("PLAN_NO",tsdhr02.getPlanNo());
            Tsdhr02 tsdhr02Up=new Tsdhr02();
            tsdhr02Up.setReqNo(tsdhr02.getReqNo());
            tsdhr02Up.setMemberName(tsdhr02.getMemberName());
            tsdhr02Up.setTel(tsdhr02.getTel());
            tsdhr02Up.setContactStatus(tsdhr02.getContactStatus());
            tsdhr02Up.setContactMember(tsdhr02.getContactMember());
            tsdhr02Up.setContactDate(tsdhr02.getContactDate());
            tsdhr02Up.setItvDate(tsdhr02.getItvDate());
            tsdhr02Up.setDeptName(tsdhr02.getDeptName());
            tsdhr02Up.setItvJob(tsdhr02.getItvJob());
            tsdhr02Up.setExpLevel(tsdhr02.getExpLevel());
            tsdhr02Up.setWorkStatus(tsdhr02.getWorkStatus());
            tsdhr02Up.setArrivalDate(tsdhr02.getArrivalDate());
            tsdhr02Up.setHopeSalary(tsdhr02.getHopeSalary());
            tsdhr02Up.setItvStatus(tsdhr02.getItvStatus());
            tsdhr02Up.setItvRemark(tsdhr02.getItvRemark());
            tsdhr02Up.setItver(tsdhr02.getItver());
            tsdhr02Up.setRemark(tsdhr02.getRemark());
            //基础信息
            Claims claims = JwtUtil.verifyJwt(request);
            String userId = claims.get("userId").toString();
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr02Up.setRecModifyName(userName);
            tsdhr02Up.setRecModifier(userId);
            tsdhr02Up.setRecModifyTime(curDateTime);
            tsdhr02Mapper.update(tsdhr02Up,wrapper);
            eiINfo.setSuccess("1");
            eiINfo.setMessage("删除成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("删除失败！"+e);
        }
        return eiINfo;
    }
}
