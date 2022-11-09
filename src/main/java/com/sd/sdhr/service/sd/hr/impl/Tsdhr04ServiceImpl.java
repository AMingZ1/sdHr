package com.sd.sdhr.service.sd.hr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.hr.Tsdhr04Mapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr04Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class Tsdhr04ServiceImpl implements Tsdhr04Service {

    @Autowired
    private Tsdhr04Mapper tsdhr04Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsdhr04(Tsdhr04 tsdhr04) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsdhr04> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getChannel()),"CHANNEL",tsdhr04.getChannel());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getMemberName()),"MEMBER_NAME",tsdhr04.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getDeptName()),"DEPT_NAME",tsdhr04.getDeptName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getItvJob()),"ITV_JOB",tsdhr04.getItvJob());

            List<Tsdhr04> list=tsdhr04Mapper.selectList(queryWrapper);
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
    public Tsdhr04 selectTsdhr04ById(Tsdhr04 tsdhr04) {
        return null;
    }

    @Override
    public EiINfo saveTsdhr04(Tsdhr04 tsdhr04) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (!StringUtils.isEmpty(tsdhr04.getMemberNo())){
                throw new Exception("员工编号不为空无法新增！面试记录号："+tsdhr04.getMemberNo());
            }
            //拿到当前年月日；
            Calendar calendar = Calendar.getInstance();
            // 获取当前年
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            // 获取当前月
            int month = calendar.get(Calendar.MONTH) + 1;
            StringBuilder memberNo=new StringBuilder("DSSH");
            //查询当前生成流水号信息
            int backNum=tsdhr04Mapper.queryCountByMemberNoLike(memberNo.toString());
            String serialNum= String.format("%04d", backNum+1);
            memberNo.append(serialNum);
            tsdhr04.setMemberNo(memberNo.toString());
            // 注入信息
            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr04.setRecCreator(userId);
            tsdhr04.setRecCreateName(userName);
            tsdhr04.setRecCreateTime(curDateTime);
            tsdhr04.setRecModifier(userId);
            tsdhr04.setRecModifyName(userName);
            tsdhr04.setRecModifyTime(curDateTime);
            tsdhr04.setDeleteFlag("0");
            int backInsert =tsdhr04Mapper.insert(tsdhr04);
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
    public EiINfo deleteTsdhr04ByMap(Tsdhr04 tsdhr04) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdhr04.getMemberNo())){
                throw new Exception("人员编号为空无法删除！");
            }
            UpdateWrapper<Tsdhr04> wrapper=new UpdateWrapper<>();
            wrapper.eq("MEMBER_NO",tsdhr04.getMemberNo());
            Tsdhr04 tsdhr04Up=new Tsdhr04();
            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr04Up.setDeleteFlag("1");
            tsdhr04Up.setDeleteName(userName);
            tsdhr04Up.setDeleter(userId);
            tsdhr04Up.setDeleteTime(curDateTime);
            tsdhr04Mapper.update(tsdhr04Up,wrapper);
            eiINfo.setSuccess("1");
            eiINfo.setMessage("删除成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("删除失败！"+e);
        }
        return eiINfo;
    }

    @Override
    public EiINfo updateTsdhr04(Tsdhr04 tsdhr04) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdhr04.getMemberNo())){
                throw new Exception("人员编号为空无法修改！");
            }
            UpdateWrapper<Tsdhr04> wrapper=new UpdateWrapper<>();
            wrapper.eq("MEMBER_NO",tsdhr04.getMemberNo());
            Tsdhr04 tsdhr04Up=new Tsdhr04();
            tsdhr04Up.setMemberName(tsdhr04.getMemberName());
            tsdhr04Up.setTel(tsdhr04.getTel());
            tsdhr04Up.setEmail(tsdhr04.getEmail());
            tsdhr04Up.setUniversityName(tsdhr04.getUniversityName());
            tsdhr04Up.setEducationBckr(tsdhr04.getEducationBckr());
            tsdhr04Up.setProfession(tsdhr04.getProfession());
            tsdhr04Up.setSumScore(tsdhr04.getSumScore());
            tsdhr04Up.setEvaluation(tsdhr04.getEvaluation());
            tsdhr04Up.setItvJob(tsdhr04.getItvJob());
            tsdhr04Up.setDeptName(tsdhr04.getDeptName());
            tsdhr04Up.setChannel(tsdhr04.getChannel());
            tsdhr04Up.setWorkYear(tsdhr04.getWorkYear());
            tsdhr04Up.setRemark(tsdhr04.getRemark());

            //
            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr04Up.setRecModifyName(userName);
            tsdhr04Up.setRecModifier(userId);
            tsdhr04Up.setRecModifyTime(curDateTime);
            tsdhr04Mapper.update(tsdhr04Up,wrapper);
            eiINfo.setSuccess("1");
            eiINfo.setMessage("修改成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("修改失败！"+e);
        }
        return eiINfo;
    }
}
