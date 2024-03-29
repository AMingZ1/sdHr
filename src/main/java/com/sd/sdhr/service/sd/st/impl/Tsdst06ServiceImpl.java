package com.sd.sdhr.service.sd.st.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.st.Tsdst06Mapper;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.st.Tsdst06;
import com.sd.sdhr.pojo.sd.st.common.Tsdst06Request;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.st.Tsdst06Service;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Tsdst06ServiceImpl implements Tsdst06Service {

    @Autowired
    Tsdst06Mapper tsdst06Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsdst06(Tsdst06Request tsdst06) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsdst06> queryWrapper=new QueryWrapper<>();
            //queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            queryWrapper.eq(!StringUtils.isEmpty(tsdst06.getTaskStatus()),"TASK_STATUS",tsdst06.getTaskStatus());
            if ("true".equals(tsdst06.getIsPostpone())){
                queryWrapper.eq("true".equals(tsdst06.getIsPostpone()),"IS_POSTPONE","Y");
            }
            if (tsdst06.getPlanEndDate() != null) {
                String b[]= tsdst06.getPlanEndDate().split(",");
                if(!"undefined".equals(b[0])&&!"undefined".equals(b[1])){
                    queryWrapper.between(!StringUtils.isEmpty(tsdst06.getPlanEndDate()),"PLAN_END_DATE",b[0],b[1]);
                }
            }

            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsdst06.getTaskId()),"TASK_ID",tsdst06.getTaskId());
            queryWrapper.like(!StringUtils.isEmpty(tsdst06.getTaskName()),"TASK_NAME",tsdst06.getTaskName());
            queryWrapper.like(!StringUtils.isEmpty(tsdst06.getTaskMember()),"TASK_MEMBER",tsdst06.getTaskMember());
            queryWrapper.like(!StringUtils.isEmpty(tsdst06.getRecCreator()),"REC_CREATOR",tsdst06.getRecCreator());

            PageHelper.startPage(tsdst06.getPageNum(),tsdst06.getPageSize());
            List<Tsdst06> list=tsdst06Mapper.selectList(queryWrapper);
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
    public Tsdst06 selectTsdst06ById(Tsdst06 tsdst06) {
        return null;
    }

    @Override
    @Transactional
    public EiINfo saveTsdst06(Tsdst06 tsdst06) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (! StringUtils.isEmpty(tsdst06.getTaskId())){
                throw new Exception("【任务编号】不为空无法新增！当前任务编号号："+tsdst06.getTaskId());
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
            memberId=memberId.append(an).append("TD").append(month);
            //查询当前生成流水号信息
            int backNum=tsdst06Mapper.queryCountByMemberIdLike(memberId.toString());
            String serialNum= String.format("%04d", backNum+1);
            memberId.append(serialNum);
            tsdst06.setTaskId(memberId.toString());
            // 注入信息
            Claims claims = JwtUtil.verifyJwt(request);
            String userId = claims.get("userId").toString();
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdst06.setTaskStatus("01");
            tsdst06.setIsPostpone("N");
            tsdst06.setRecCreator(userId);
            tsdst06.setRecCreateName(userName);
            tsdst06.setRecCreateTime(curDateTime);
            tsdst06.setRecModifier(userId);
            tsdst06.setRecModifyName(userName);
            tsdst06.setRecModifyTime(curDateTime);
            tsdst06.setDeleteFlag("0");
            int backInsert =tsdst06Mapper.insert(tsdst06);
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
    @Transactional
    public EiINfo taskTsdst06Shutdown(Tsdst06 tsdst06) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdst06.getTaskId())){
                throw new Exception("【任务编号】为空无法删除！");
            }
            UpdateWrapper<Tsdst06> wrapper=new UpdateWrapper<>();
            wrapper.eq("TASK_ID",tsdst06.getTaskId());
            Tsdst06 tsdst06Up=new Tsdst06();
            Claims claims = JwtUtil.verifyJwt(request);
            String userId = claims.get("userId").toString();
            String userName =  claims.get("userName").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdst06Up.setTaskStatus("00");//关闭
            tsdst06Up.setActEndDate(StringUtils.isEmpty(tsdst06.getIsPostpone())?curDateTime:tsdst06.getIsPostpone());
            tsdst06Up.setRecModifyName(userName);
            tsdst06Up.setRecModifier(userId);
            tsdst06Up.setRecModifyTime(curDateTime);
            tsdst06Mapper.update(tsdst06Up,wrapper);
            eiINfo.setSuccess("1");
            eiINfo.setMessage("删除成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("删除失败！"+e);
        }
        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo updateTsdst06(Tsdst06 tsdst06) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdst06.getTaskId())){
                throw new Exception("【任务编号】为空无法修改！");
            }
            UpdateWrapper<Tsdst06> wrapper=new UpdateWrapper<>();
            wrapper.eq("TASK_ID",tsdst06.getTaskId());
            Tsdst06 tsdst06Up=new Tsdst06();
            tsdst06Up.setTaskName(tsdst06.getTaskName());
            tsdst06Up.setTaskMember(tsdst06.getTaskMember());
            tsdst06Up.setPlanEndDate(tsdst06.getPlanEndDate());
            tsdst06Up.setRemark(tsdst06.getRemark());
            tsdst06Up.setTaskStatus(tsdst06.getTaskStatus());

            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdst06Up.setRecModifyName(userName);
            tsdst06Up.setRecModifier(userId);
            tsdst06Up.setRecModifyTime(curDateTime);
            tsdst06Mapper.update(tsdst06Up,wrapper);
            eiINfo.setSuccess("1");
            eiINfo.setMessage("修改成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("修改失败！"+e);
        }
        return eiINfo;
    }

    @Override
    @Transactional
    public void taskIsPostpone() {
        // 查询还没关闭的任务信息
        UpdateWrapper<Tsdst06> wrapper=new UpdateWrapper<>();
        wrapper.eq("TASK_STATUS","01");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String nowDate = formatter.format(new Date());
        wrapper.gt("PLAN_END_DATE",nowDate);// 预计结束时间大于当前时间

        Tsdst06 tsdst06Up=new Tsdst06();
        SimpleDateFormat formatterTime = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatterTime.format(new Date());
        tsdst06Up.setRecModifyName("admin");
        tsdst06Up.setRecModifier("admin");
        tsdst06Up.setRecModifyTime(curDateTime);
        tsdst06Up.setIsPostpone("Y");//延期
        tsdst06Up.setRemark(nowDate+"系统自动更新延期标志");
        tsdst06Mapper.update(tsdst06Up,wrapper);
    }
}
