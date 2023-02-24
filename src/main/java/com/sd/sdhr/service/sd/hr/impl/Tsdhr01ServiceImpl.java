package com.sd.sdhr.service.sd.hr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.hr.Tsdhr01Mapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.hr.Tsdhr01Service;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class Tsdhr01ServiceImpl implements Tsdhr01Service {


    @Autowired
    private Tsdhr01Mapper tsdhr01Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public List<Tsdhr01> getAllTsdhr01() {
        return tsdhr01Mapper.selectList(null);
    }

    @Override
    public Tsdhr01 getTsdhr01ByReqNo(String reqNo) {
        return tsdhr01Mapper.selectById(reqNo);
    }

    @Override
    @Transactional
    public EiINfo saveTsdhr01(Tsdhr01 tsdhr01)throws Exception {
        EiINfo eiINfo=new EiINfo();
        //tsdhr01Mapper.selectList(new Wrapper<>().like("",""))
        //拿到当前年月日；
        Calendar calendar = Calendar.getInstance();
        // 获取当前年
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        // 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;
        StringBuilder reqNo=new StringBuilder();
        String an=year.substring(year.length()-2);
        reqNo=reqNo.append(an).append("A").append(month);
        //查询当前生成流水号信息
        int num=tsdhr01Mapper.queryCountByReqNoLike(reqNo.toString());
        String serialNum= String.format("%04d", num+1);
        reqNo.append(serialNum);
        tsdhr01.setReqNo(reqNo.toString());
        // 注入信息
        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr01.setRecCreator(userId);
        tsdhr01.setRecCreatorName(userName);
        tsdhr01.setRecCreateTime(curDateTime);
        tsdhr01.setRecModifier(userId);
        tsdhr01.setRecModifyName(userName);
        tsdhr01.setRecModifyTime(curDateTime);
        tsdhr01.setDeleteFlag("0");
        int ss =tsdhr01Mapper.insert(tsdhr01);
        eiINfo.setMessage(String.valueOf(ss));
        if (ss==1){
            eiINfo.setSuccess("1");
            eiINfo.setMessage("新增成功！");
        }else {
            throw new Exception("新增失败！！");
        }

        return eiINfo;
    }

    @Override
    public int updateTsdhr01ByReqNo(Tsdhr01 tsdhr01) {
        return tsdhr01Mapper.updateById(tsdhr01);
    }

    @Override
    public Tsdhr01 queryTsdhr01ByReqNo(String reqNo) {
        return tsdhr01Mapper.queryTsdhr01ByReqNo(reqNo);
    }

    @Override
    public EiINfo getAllTsdhr01Test(Tsdhr01Request tsdhr01) {
        EiINfo eiINfo=new EiINfo();
        try {
            //模糊查询条件
            QueryWrapper<Tsdhr01> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("Delete_Flag",tsdhr01.isQueryHis()?"1":"0");//true 只查历史
            queryWrapper.like(!StringUtils.isEmpty(tsdhr01.getYear()),"YEAR",tsdhr01.getYear());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr01.getDeptName()),"DEPT_NAME",tsdhr01.getDeptName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr01.getItvJob()),"ITV_JOB",tsdhr01.getItvJob());
            //queryWrapper.like(!tsdhr01.getReqNo().isEmpty(),"REQ_NO",tsdhr01.getReqNo());

            //eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(tsdhr01.getPageNum(),tsdhr01.getPageSize());
            List<Tsdhr01> list=tsdhr01Mapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(list)){
                PageInfo pageInfo=new PageInfo(list);
                eiINfo.setMessage("查询成功!");
                eiINfo.setTotalNum(pageInfo.getTotal());
            }
            eiINfo.setData(list);
            eiINfo.setSuccess("1");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("查询失败!"+e);
        }


        return eiINfo;

    }

    @Override
    @Transactional
    public EiINfo deleteTsdhr01ByMap(Tsdhr01 tsdhr01) throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsdhr01.getReqNo())){
            throw new Exception("需求编号号为空无法删除！");
        }
        UpdateWrapper<Tsdhr01> wrapper=new UpdateWrapper<>();
        wrapper.eq("REQ_NO",tsdhr01.getReqNo());
        Tsdhr01 tsdhr01Up=new Tsdhr01();
        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr01Up.setDeleteFlag("1");
        tsdhr01Up.setDeleteName(userName);
        tsdhr01Up.setDeleter(userId);
        tsdhr01Up.setDeleteTime(curDateTime);
        tsdhr01Mapper.update(tsdhr01Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("删除成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo updateTsdhr01(Tsdhr01 tsdhr01) throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsdhr01.getReqNo())){
            throw new Exception("需求编号号为空无法修改！");
        }
        UpdateWrapper<Tsdhr01> wrapper=new UpdateWrapper<>();
        wrapper.eq("REQ_NO",tsdhr01.getReqNo());
        Tsdhr01 tsdhr01Up=new Tsdhr01();
        tsdhr01Up.setYear(tsdhr01.getYear());
        tsdhr01Up.setDeptName(tsdhr01.getDeptName());
        tsdhr01Up.setItvJob(tsdhr01.getItvJob());
        tsdhr01Up.setRequireNum(tsdhr01.getRequireNum());
        tsdhr01Up.setRealNum(tsdhr01.getRealNum());
        tsdhr01Up.setJobRequire(tsdhr01.getJobRequire());
        tsdhr01Up.setRequireContact(tsdhr01.getRequireContact());
        tsdhr01Up.setDutyPerson(tsdhr01.getDutyPerson());
        tsdhr01Up.setPlanEndDate(tsdhr01.getPlanEndDate());
        tsdhr01Up.setIsEme(tsdhr01.getIsEme());
        tsdhr01Up.setRemark(tsdhr01.getRemark());

        //  注入基本信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        //String userName = (String) request.getSession().getAttribute("userName");
        //String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr01Up.setRecModifyName(userName);
        tsdhr01Up.setRecModifier(userId);
        tsdhr01Up.setRecModifyTime(curDateTime);
        tsdhr01Mapper.update(tsdhr01Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }
}
