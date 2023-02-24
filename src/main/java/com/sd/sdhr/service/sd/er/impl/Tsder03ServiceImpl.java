package com.sd.sdhr.service.sd.er.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.er.Tsder03Mapper;
import com.sd.sdhr.mapper.sd.er.Tsder04Mapper;
import com.sd.sdhr.mapper.sd.er.Tsder06Mapper;
import com.sd.sdhr.mapper.sd.st.Tsdst09DefinedMapper;
import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.er.Tsder06;
import com.sd.sdhr.pojo.sd.er.common.Tsder03Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.er.Tsder03Service;
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
public class Tsder03ServiceImpl implements Tsder03Service {

    @Autowired
    private Tsder03Mapper tsder03Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Autowired
    private Tsder04Mapper tsder04Mapper;

    @Autowired
    private Tsder06Mapper tsder06Mapper;

    @Autowired
    Tsdst09DefinedMapper tsdst09DefinedMapper;

    @Override
    public EiINfo getAllTsder03(Tsder03Request tsder03) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsder03> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsder03.getMemberName()),"MEMBER_NAME",tsder03.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsder03.getDeptName()),"DEPT_NAME",tsder03.getDeptName());
            queryWrapper.like(!StringUtils.isEmpty(tsder03.getJobs()),"JOBS",tsder03.getJobs());

            PageHelper.startPage(tsder03.getPageNum(),tsder03.getPageSize());
            List<Tsder03> list=tsder03Mapper.selectList(queryWrapper);
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
    public Tsder03 selectTsder03ById(Tsder03 tsder03) {
        return null;
    }

    @Override
    @Transactional
    public EiINfo saveTsder03(Tsder03 tsder03)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder03.getMemberId())){
            throw new Exception("【人员编号】为空无法新增！面试记录号："+tsder03.getMemberId());
        }
        if (StringUtils.isEmpty(tsder03.getEmpDate())){
            throw new Exception("【入职日期】为空无法新增！面试记录号："+tsder03.getMemberId());
        }
        //查询当前人员编号是否已经生成访谈主信息
        QueryWrapper<Tsder03> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("MEMBER_ID",tsder03.getMemberId());
        int backNum=tsder03Mapper.selectCount(queryWrapper);
        if (backNum!=0){
            throw new Exception("人员编号已经维护有访谈主信息，人员编号："+tsder03.getMemberId());
        }
        //根据入职日期确定后续周、月、转正日期
        String empDa=tsder03.getEmpDate();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
        Date empDate =formatDate.parse(empDa);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(empDate);
        calendar.add(Calendar.DATE,7);// 周访谈
        tsder03.setTalkWeek(formatDate.format(calendar.getTime()));
        calendar.add(Calendar.DATE,-7);
        calendar.add(Calendar.MONTH,1);// 第一月月访谈
        tsder03.setTalkMonth(formatDate.format(calendar.getTime()));
        calendar.add(Calendar.MONTH,2);// 转正访谈
        tsder03.setFormalDare(formatDate.format(calendar.getTime()));

        // 注入信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder03.setRecCreator(userId);
        tsder03.setRecCreateName(userName);
        tsder03.setRecCreateTime(curDateTime);
        tsder03.setRecModifier(userId);
        tsder03.setRecModifyName(userName);
        tsder03.setRecModifyTime(curDateTime);
        tsder03.setDeleteFlag("0");
        int backInsert =tsder03Mapper.insert(tsder03);
        eiINfo.setMessage(String.valueOf(backInsert));
        if (backInsert==1){
            eiINfo.setSuccess("1");
            eiINfo.setMessage("新增成功！");
        }else {
            throw new Exception("新增失败！");
        }

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo deleteTsder03ByMap(Tsder03 tsder03) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsder03.getMemberId())){
                throw new Exception("人员编号为空无法删除！");
            }
            QueryWrapper<Tsder04> wrapper=new QueryWrapper<>();
            wrapper.eq("MEMBER_ID",tsder03.getMemberId());
            int listConun= tsder04Mapper.selectCount(wrapper);
            if (listConun>0){
                throw new Exception("人员编号在访谈明细表中存在数据，无法删除！人员编号："+tsder03.getMemberId());
            }
            Tsder03 tsder03Up=new Tsder03();
            /*
            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsder03Up.setDeleteFlag("1");
            tsder03Up.setDeleteName(userName);
            tsder03Up.setDeleter(userId);
            tsder03Up.setDeleteTime(curDateTime);
            tsder03Mapper.update(tsder03Up,wrapper);
            */
            tsder03Mapper.deleteById(tsder03.getMemberId());
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
    public EiINfo updateTsder03(Tsder03 tsder03)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder03.getMemberId())){
            throw new Exception("【人员编号】为空无法修改！");
        }
        if (StringUtils.isEmpty(tsder03.getEmpDate())){
            throw new Exception("【入职日期】为空无法修改！");
        }
        UpdateWrapper<Tsder03> wrapper=new UpdateWrapper<>();
        wrapper.eq("MEMBER_ID",tsder03.getMemberId());
        Tsder03 tsder03Up=new Tsder03();
        tsder03Up.setMemberName(tsder03.getMemberName());
        tsder03Up.setMemberType(tsder03.getMemberType());
        tsder03Up.setDeptName(tsder03.getDeptName());
        tsder03Up.setJobs(tsder03.getJobs());
        tsder03Up.setEmpDate(tsder03.getEmpDate());
        //tsder03Up.setFormalDare(tsder03.getFormalDare());
        //根据入职日期确定后续周、月、转正日期
        String empDa=tsder03.getEmpDate();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
        Date empDate =formatDate.parse(empDa);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(empDate);
        calendar.add(Calendar.DATE,7);// 周访谈
        tsder03Up.setTalkWeek(formatDate.format(calendar.getTime()));
        calendar.add(Calendar.DATE,-7);
        calendar.add(Calendar.MONTH,1);// 第一月月访谈
        tsder03Up.setTalkMonth(formatDate.format(calendar.getTime()));
        calendar.add(Calendar.MONTH,2);// 转正访谈
        tsder03Up.setFormalDare(formatDate.format(calendar.getTime()));

        tsder03Up.setPmNameF(tsder03.getPmNameF());
        tsder03Up.setProjectNameF(tsder03.getProjectNameF());
        tsder03Up.setTalkNo(tsder03.getTalkNo());
        tsder03Up.setRemark(tsder03.getRemark());

        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder03Up.setRecModifyName(userName);
        tsder03Up.setRecModifier(userId);
        tsder03Up.setRecModifyTime(curDateTime);
        tsder03Mapper.update(tsder03Up,wrapper);

        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo isOverdueJudge() {
        EiINfo eiINfo=new EiINfo();
        try {
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyyMMdd");
            String currentDate = formatterDate.format(new Date());
            Tsder03 tsder03Up=new Tsder03();
            UpdateWrapper<Tsder03> wrapperWeek=new UpdateWrapper<>();
            wrapperWeek.lt("TALK_WEEK",currentDate);
            wrapperWeek.eq("MEMBER_TYPE","0");
            wrapperWeek.eq("TALK_PLAN_NOW","00");
            wrapperWeek.ne("TALK_STATUS","00");
            //wrapper.gt
            tsder03Up.setTalkStatus("00");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String curDateTime = formatter.format(new Date());
            tsder03Up.setRecModifyName("admin");
            tsder03Up.setRecModifier("admmin");
            tsder03Up.setRecModifyTime(curDateTime);
            tsder03Mapper.update(tsder03Up,wrapperWeek);
            // 月
            UpdateWrapper<Tsder03> wrapperMonth=new UpdateWrapper<>();
            wrapperMonth.lt("TALK_MONTH",currentDate);
            wrapperMonth.eq("MEMBER_TYPE","0");
            wrapperMonth.eq("TALK_PLAN_NOW","01");
            wrapperMonth.ne("TALK_STATUS","00");
            tsder03Mapper.update(tsder03Up,wrapperMonth);
            // 转正
            UpdateWrapper<Tsder03> wrapperFormal=new UpdateWrapper<>();
            wrapperFormal.lt("FORMAL_DARE",currentDate);
            wrapperFormal.eq("MEMBER_TYPE","0");
            wrapperFormal.eq("TALK_PLAN_NOW","02");
            wrapperFormal.ne("TALK_STATUS","00");
            tsder03Mapper.update(tsder03Up,wrapperFormal);

            // 年度
            // 获取当前年
            //拿到当前年月日；
            Calendar calendar = Calendar.getInstance();
            int yyyy=calendar.get(Calendar.YEAR);
            String year = String.valueOf(yyyy);
            Tsder06 tsder06=tsder06Mapper.selectById(year);
            if (tsder06!=null){
                Date date1=formatterDate.parse(tsder06.getNode1());
                Date date2=formatterDate.parse(currentDate);
                //date1.after(date2) Date1在Date2之后 当前时间超过上半年设定值就主抓上半年，没超过就下半年
                String talkType ;
                if (date2.after(date1)){
                    talkType="T04";
                }else {
                    talkType="T05";
                    year=String.valueOf(yyyy-1);
                }
                //String talkType =date2.after(date1)?"T04":"T05";
                curDateTime.substring(0,4);
                //StringBuffer sqlS=new StringBuffer(" ");
                Tsder04 tsder04=new Tsder04();
                tsder04.setYear(year);
                tsder04.setTalkType(talkType);
                tsdst09DefinedMapper.updateEr03TalkStart(tsder04);

            }

            eiINfo.setSuccess("1");
            eiINfo.setMessage("修改成功！");



        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("修改失败！"+e);
            throw new RuntimeException("修改失败:"+e);
        }
        return eiINfo;
    }
}
