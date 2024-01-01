package com.sd.sdhr.service.sd.hr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.hr.Tsdhr04Mapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr04Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr04Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.of.Tsdof01;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.hr.Tsdhr04Service;
import com.sd.sdhr.service.sd.of.Tsdof01Service;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class Tsdhr04ServiceImpl implements Tsdhr04Service {

    @Autowired
    private Tsdhr04Mapper tsdhr04Mapper;
    @Autowired
    Tsdof01Service tsdof01Service;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request



    @Override
    public EiINfo getAllTsdhr04(Tsdhr04Request tsdhr04) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsdhr04> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            if (tsdhr04.getItvDate() != null) {
                String b[]= tsdhr04.getItvDate().split(",");
                if(!"undefined".equals(b[0])&&!"undefined".equals(b[1])){
                    queryWrapper.between(!StringUtils.isEmpty(tsdhr04.getItvDate()),"ITV_DATE",b[0],b[1]);
                }
            }
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getItvNo()),"ITV_NO", tsdhr04.getItvNo());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getMemberName()),"MEMBER_NAME", tsdhr04.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getItvDept()),"ITV_DEPT", tsdhr04.getItvDept());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getItvJob()),"ITV_JOB", tsdhr04.getItvJob());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr04.getItvStatus()),"ITV_STATUS", tsdhr04.getItvStatus());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr04.getNowStatus()),"NOW_STATUS", tsdhr04.getNowStatus());

            PageHelper.startPage(tsdhr04.getPageNum(),tsdhr04.getPageSize());
            List<Tsdhr04> list= tsdhr04Mapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(list)){
                PageInfo pageInfo=new PageInfo(list);
                eiINfo.setTotalNum(pageInfo.getTotal());
                eiINfo.setData(list);
            }
            eiINfo.setMessage("查询成功!");
            eiINfo.setSuccess("1");
        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("查询失败!"+e);
        }
        return eiINfo;
    }

    @Override
    public List<Tsdhr04> queryTsdhr04s(Tsdhr04Request tsdhr04) {
        QueryWrapper<Tsdhr04> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("Delete_Flag","1");//删除标记不为1
        if (tsdhr04.getItvDate() != null) {
            String b[]= tsdhr04.getItvDate().split(",");
            if(!"undefined".equals(b[0])&&!"undefined".equals(b[1])){
                queryWrapper.between(!StringUtils.isEmpty(tsdhr04.getItvDate()),"ITV_DATE",b[0],b[1]);
            }
        }
        //模糊查询条件
        queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getItvNo()),"ITV_NO", tsdhr04.getItvNo());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getMemberName()),"MEMBER_NAME", tsdhr04.getMemberName());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getItvDept()),"ITV_DEPT", tsdhr04.getItvDept());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr04.getItvJob()),"ITV_JOB", tsdhr04.getItvJob());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr04.getItvStatus()),"ITV_STATUS", tsdhr04.getItvStatus());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr04.getNowStatus()),"NOW_STATUS", tsdhr04.getNowStatus());
        List<Tsdhr04> list= tsdhr04Mapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Tsdhr04 selectTsdhr04ById(Tsdhr04 tsdhr04) {
        return null;
    }

    @Override
    public EiINfo saveTsdhr04sByImp(List<Tsdhr04Upload> hr04Uploads)throws Exception {
        EiINfo eiINfo=new EiINfo();

        for (Tsdhr04Upload hr04Upload:hr04Uploads){
            Tsdhr04 tsdhr04 = new Tsdhr04();
            BeanUtils.copyProperties(hr04Upload,tsdhr04);
            this.saveTsdhr04(tsdhr04);
        }
        eiINfo.setSuccess("1");
        eiINfo.setMessage("导入信息成功！");
        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo saveTsdhr04(Tsdhr04 tsdhr04) throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (!StringUtils.isEmpty(tsdhr04.getItvNo())){
            throw new Exception("面试记录号不为空无法新增！面试记录号："+ tsdhr04.getItvNo());
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
        int backNum= tsdhr04Mapper.queryCountByItvNoLike(itvNo.toString());
        String serialNum= String.format("%04d", backNum+1);
        itvNo.append(serialNum);
        tsdhr04.setItvNo(itvNo.toString());
        // 注入信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr04.setRecCreator(userId);
        tsdhr04.setRecCreateName(userName);
        tsdhr04.setRecCreateTime(curDateTime);
        tsdhr04.setRecModifier(userId);
        tsdhr04.setRecModifyName(userName);
        tsdhr04.setRecModifyTime(curDateTime);
        tsdhr04.setDeleteFlag("0");
        int backInsert = tsdhr04Mapper.insert(tsdhr04);
        eiINfo.setMessage(String.valueOf(backInsert));
        if (backInsert==1){
            eiINfo.setSuccess("1");
            eiINfo.setMessage("新增成功！");
        }else {
            throw new Exception("insert返回出错！");
        }
        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo deleteTsdhr04ByMap(Tsdhr04 tsdhr04)throws Exception {

        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsdhr04.getItvNo())){
            throw new Exception("面试记录号为空无法删除！");
        }
        UpdateWrapper<Tsdhr04> wrapper=new UpdateWrapper<>();
        wrapper.eq("ITV_NO", tsdhr04.getItvNo());
        Tsdhr04 tsdhr04Up =new Tsdhr04();
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr04Up.setDeleteFlag("1");
        tsdhr04Up.setDeleteName(userName);
        tsdhr04Up.setDeleter(userId);
        tsdhr04Up.setDeleteTime(curDateTime);
        tsdhr04Mapper.update(tsdhr04Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("删除成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo updateTsdhr04(Tsdhr04 tsdhr04)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsdhr04.getItvNo())){
            throw new Exception("面试记录号为空无法修改！");
        }
        UpdateWrapper<Tsdhr04> wrapper=new UpdateWrapper<>();
        wrapper.eq("ITV_NO", tsdhr04.getItvNo());
        Tsdhr04 tsdhr04Up =new Tsdhr04();
        tsdhr04Up.setPlanNo(tsdhr04.getPlanNo());
        tsdhr04Up.setItvDept(tsdhr04.getItvDept());
        tsdhr04Up.setItvJob(tsdhr04.getItvJob());
        tsdhr04Up.setItvDate(tsdhr04.getItvDate());
        tsdhr04Up.setItver(tsdhr04.getItver());
        tsdhr04Up.setItvWays(tsdhr04.getItvWays());
        tsdhr04Up.setMemberName(tsdhr04.getMemberName());
        tsdhr04Up.setUniversityName(tsdhr04.getUniversityName());
        tsdhr04Up.setEducationBckr(tsdhr04.getEducationBckr());
        tsdhr04Up.setProfession(tsdhr04.getProfession());
        tsdhr04Up.setTel(tsdhr04.getTel());
        tsdhr04Up.setEmail(tsdhr04.getEmail());
        tsdhr04Up.setScore1(tsdhr04.getScore1());
        tsdhr04Up.setScore2(tsdhr04.getScore2());
        tsdhr04Up.setScore3(tsdhr04.getScore3());
        tsdhr04Up.setScore4(tsdhr04.getScore4());
        tsdhr04Up.setScore5(tsdhr04.getScore5());
        tsdhr04Up.setScore6(tsdhr04.getScore6());
        tsdhr04Up.setSumScore(tsdhr04.getSumScore());
        tsdhr04Up.setItvStatus(tsdhr04.getItvStatus());
        tsdhr04Up.setArrivalDate(tsdhr04.getArrivalDate());
        tsdhr04Up.setEvaluation(tsdhr04.getEvaluation());
        tsdhr04Up.setHopeSalary(tsdhr04.getHopeSalary());
        tsdhr04Up.setEvaluation(tsdhr04.getEvaluation());
        tsdhr04Up.setRemark(tsdhr04.getRemark());

        //
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr04Up.setRecModifyName(userName);
        tsdhr04Up.setRecModifier(userId);
        tsdhr04Up.setRecModifyTime(curDateTime);
        tsdhr04Mapper.update(tsdhr04Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo insertOffer(Tsdhr04 tsdhr04)throws Exception {
        log.info("新增offer信息："+tsdhr04.toString());
        EiINfo eiINfo=new EiINfo();

        //发起审批
        Tsdhr04 nerHr04 =tsdhr04Mapper.selectById(tsdhr04.getItvNo());
        if(!"10".equals(nerHr04.getNowStatus())){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("只有当前状态为【面试通过】的才可以申请off发送！");
            return eiINfo;
        }
        Tsdof01 tsdof01 = new Tsdof01();
        tsdof01.setMemberName(nerHr04.getMemberName());
        tsdof01.setDeptName(nerHr04.getItvDept());
        tsdof01.setJobs(nerHr04.getItvJob());
        tsdof01.setEmpDate(nerHr04.getArrivalDate());
        tsdof01.setEmail(nerHr04.getEmail());
        tsdof01.setTel(nerHr04.getTel());
        tsdof01.setSalary(nerHr04.getHopeSalary());
        this.updateTsdhr04NowStatus(tsdhr04.getItvNo(),"40");
        EiINfo iINfo = tsdof01Service.saveTsdof01(tsdof01);
        if ("-1".equals(iINfo.getSuccess())){
            throw new Exception(iINfo.getMessage());
        }
        eiINfo.setSuccess("1");
        eiINfo.setMessage("新增offer成功！");

        return eiINfo;
    }



    public void updateTsdhr04NowStatus(String itvNo,String NowStatus) {
        UpdateWrapper<Tsdhr04> wrapper=new UpdateWrapper<>();
        wrapper.eq("ITV_NO", itvNo);
        Tsdhr04 tsdhr04Up =new Tsdhr04();
        tsdhr04Up.setNowStatus(NowStatus);

        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName = claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr04Up.setRecModifyName(userName);
        tsdhr04Up.setRecModifier(userId);
        tsdhr04Up.setRecModifyTime(curDateTime);
        tsdhr04Mapper.update(tsdhr04Up,wrapper);
    }


    @Override
    @Transactional
    public String saveTsdhr042(Tsdhr04 tsdhr04) throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (!StringUtils.isEmpty(tsdhr04.getItvNo())){
            throw new Exception("面试记录号不为空无法新增！面试记录号："+ tsdhr04.getItvNo());
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
        int backNum= tsdhr04Mapper.queryCountByItvNoLike(itvNo.toString());
        String serialNum= String.format("%04d", backNum+1);
        itvNo.append(serialNum);
        tsdhr04.setItvNo(itvNo.toString());
        // 注入信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr04.setRecCreator(userId);
        tsdhr04.setRecCreateName(userName);
        tsdhr04.setRecCreateTime(curDateTime);
        tsdhr04.setRecModifier(userId);
        tsdhr04.setRecModifyName(userName);
        tsdhr04.setRecModifyTime(curDateTime);
        tsdhr04.setDeleteFlag("0");
        int backInsert = tsdhr04Mapper.insert(tsdhr04);
        eiINfo.setMessage(String.valueOf(backInsert));
        if (backInsert==1){
            eiINfo.setSuccess("1");
            eiINfo.setMessage("新增成功！");
        }else {
            throw new Exception("insert返回出错！");
        }
        return itvNo.toString();
    }
}
