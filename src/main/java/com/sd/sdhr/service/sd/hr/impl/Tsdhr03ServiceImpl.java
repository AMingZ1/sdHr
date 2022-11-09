package com.sd.sdhr.service.sd.hr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.hr.Tsdhr03Mapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.hr.Tsdhr03Service;
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
public class Tsdhr03ServiceImpl implements Tsdhr03Service {

    @Autowired
    private Tsdhr03Mapper tsdhr03Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsdhr03(Tsdhr03 tsdhr03) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsdhr03> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getItvNo()),"ITV_NO",tsdhr03.getItvNo());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getMemberName()),"MEMBER_NAME",tsdhr03.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getItvDept()),"ITV_DEPT",tsdhr03.getItvDept());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getItvJob()),"ITV_JOB",tsdhr03.getItvJob());

            List<Tsdhr03> list=tsdhr03Mapper.selectList(queryWrapper);
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
    public Tsdhr03 selectTsdhr03ById(Tsdhr03 tsdhr03) {
        return null;
    }

    @Override
    public EiINfo saveTsdhr03(Tsdhr03 tsdhr03) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (!StringUtils.isEmpty(tsdhr03.getItvNo())){
                throw new Exception("面试记录号不为空无法新增！面试记录号："+tsdhr03.getItvNo());
            }
            //拿到当前年月日；
            Calendar calendar = Calendar.getInstance();
            // 获取当前年
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            // 获取当前月
            int month = calendar.get(Calendar.MONTH) + 1;
            StringBuilder itvNo=new StringBuilder();
            String an=year.substring(year.length()-2);
            itvNo=itvNo.append(an).append("C").append(month);
            //查询当前生成流水号信息
            int backNum=tsdhr03Mapper.queryCountByItvNoLike(itvNo.toString());
            String serialNum= String.format("%04d", backNum+1);
            itvNo.append(serialNum);
            tsdhr03.setItvNo(itvNo.toString());
            // 注入信息
            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr03.setRecCreator(userId);
            tsdhr03.setRecCreateName(userName);
            tsdhr03.setRecCreateTime(curDateTime);
            tsdhr03.setRecModifier(userId);
            tsdhr03.setRecModifyName(userName);
            tsdhr03.setRecModifyTime(curDateTime);
            tsdhr03.setDeleteFlag("0");
            int backInsert =tsdhr03Mapper.insert(tsdhr03);
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
    public EiINfo deleteTsdhr03ByMap(Tsdhr03 tsdhr03) {

        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdhr03.getItvNo())){
                throw new Exception("面试记录号为空无法删除！");
            }
            UpdateWrapper<Tsdhr03> wrapper=new UpdateWrapper<>();
            wrapper.eq("ITV_NO",tsdhr03.getItvNo());
            Tsdhr03 tsdhr03Up=new Tsdhr03();
            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr03Up.setDeleteFlag("1");
            tsdhr03Up.setDeleteName(userName);
            tsdhr03Up.setDeleter(userId);
            tsdhr03Up.setDeleteTime(curDateTime);
            tsdhr03Mapper.update(tsdhr03Up,wrapper);
            eiINfo.setSuccess("1");
            eiINfo.setMessage("删除成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("删除失败！"+e);
        }
        return eiINfo;
    }

    @Override
    public EiINfo updateTsdhr03(Tsdhr03 tsdhr03) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsdhr03.getItvNo())){
                throw new Exception("面试记录号为空无法修改！");
            }
            UpdateWrapper<Tsdhr03> wrapper=new UpdateWrapper<>();
            wrapper.eq("ITV_NO",tsdhr03.getItvNo());
            Tsdhr03 tsdhr03Up=new Tsdhr03();
            tsdhr03Up.setPlanNo(tsdhr03.getPlanNo());
            tsdhr03Up.setItvDept(tsdhr03.getItvDept());
            tsdhr03Up.setItvJob(tsdhr03.getItvJob());
            tsdhr03Up.setItvDate(tsdhr03.getItvDate());
            tsdhr03Up.setItver(tsdhr03.getItver());
            tsdhr03Up.setItvWays(tsdhr03.getItvWays());
            tsdhr03Up.setMemberName(tsdhr03.getMemberName());
            tsdhr03Up.setUniversityName(tsdhr03.getUniversityName());
            tsdhr03Up.setEducationBckr(tsdhr03.getEducationBckr());
            tsdhr03Up.setProfession(tsdhr03.getProfession());
            tsdhr03Up.setTel(tsdhr03.getTel());
            tsdhr03Up.setEmail(tsdhr03.getEmail());
            tsdhr03Up.setScore1(tsdhr03.getScore1());
            tsdhr03Up.setScore2(tsdhr03.getScore2());
            tsdhr03Up.setScore3(tsdhr03.getScore3());
            tsdhr03Up.setScore4(tsdhr03.getScore4());
            tsdhr03Up.setScore5(tsdhr03.getScore5());
            tsdhr03Up.setScore6(tsdhr03.getScore6());
            tsdhr03Up.setSumScore(tsdhr03.getSumScore());
            tsdhr03Up.setItvStatus(tsdhr03.getItvStatus());
            tsdhr03Up.setArrivalDate(tsdhr03.getArrivalDate());
            tsdhr03Up.setEvaluation(tsdhr03.getEvaluation());
            tsdhr03Up.setMailStatus(tsdhr03.getMailStatus());
            tsdhr03Up.setIsAgree(tsdhr03.getIsAgree());
            tsdhr03Up.setRemark(tsdhr03.getRemark());

            //
            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsdhr03Up.setRecModifyName(userName);
            tsdhr03Up.setRecModifier(userId);
            tsdhr03Up.setRecModifyTime(curDateTime);
            tsdhr03Mapper.update(tsdhr03Up,wrapper);
            eiINfo.setSuccess("1");
            eiINfo.setMessage("修改成功！");

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("修改失败！"+e);
        }
        return eiINfo;
    }
}
