package com.sd.sdhr.service.sd.er.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.er.Tsder01Mapper;
import com.sd.sdhr.pojo.sd.er.Tsder01;
import com.sd.sdhr.pojo.sd.er.common.Tsder01Request;
import com.sd.sdhr.pojo.sd.er.common.Tsder01Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.er.Tsder01Service;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
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
public class Tsder01ServiceImpl implements Tsder01Service {

    @Autowired
    private Tsder01Mapper tsder01Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsder01(Tsder01Request tsder01) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsder01> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsder01.getMemberName()),"MEMBER_NAME",tsder01.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsder01.getDeptName()),"DEPT_NAME",tsder01.getDeptName());
            queryWrapper.like(!StringUtils.isEmpty(tsder01.getJobs()),"JOBS",tsder01.getJobs());
            queryWrapper.eq(!StringUtils.isEmpty(tsder01.getIsFormal()),"IS_FORMAL",tsder01.getIsFormal());
            queryWrapper.likeLeft(!StringUtils.isEmpty(tsder01.getBirthDate()),"BIRTH_DATE",tsder01.getBirthDate());


            PageHelper.startPage(tsder01.getPageNum(),tsder01.getPageSize());
            List<Tsder01> list=tsder01Mapper.selectList(queryWrapper);
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
    public List<Tsder01> queryTsder01s(Tsder01Request tsder01) {
        QueryWrapper<Tsder01> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("Delete_Flag","1");//删除标记不为1
        //模糊查询条件
        queryWrapper.like(!StringUtils.isEmpty(tsder01.getMemberName()),"MEMBER_NAME",tsder01.getMemberName());
        queryWrapper.like(!StringUtils.isEmpty(tsder01.getDeptName()),"DEPT_NAME",tsder01.getDeptName());
        queryWrapper.like(!StringUtils.isEmpty(tsder01.getJobs()),"JOBS",tsder01.getJobs());
        queryWrapper.eq(!StringUtils.isEmpty(tsder01.getIsFormal()),"IS_FORMAL",tsder01.getIsFormal());
        queryWrapper.likeLeft(!StringUtils.isEmpty(tsder01.getBirthDate()),"BIRTH_DATE",tsder01.getBirthDate());

        List<Tsder01> list=tsder01Mapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Tsder01 selectTsder01ById(Tsder01 tsder01) {
        return null;
    }

    @Override
    @Transactional
    public EiINfo saveTsder01(Tsder01 tsder01)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (! StringUtils.isEmpty(tsder01.getMemberId())){
            throw new Exception("员工编号不为空无法新增！面试记录号："+tsder01.getMemberId());
        }
        //拿到当前年月日；
        Calendar calendar = Calendar.getInstance();
        // 获取当前年
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        // 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;
        StringBuilder memberId=new StringBuilder("DSSH");
        //查询当前生成流水号信息
        int backNum=tsder01Mapper.queryCountByMemberIdLike(memberId.toString());
        String serialNum= String.format("%04d", backNum+1);
        memberId.append(serialNum);
        tsder01.setMemberId(memberId.toString());
        // 注入信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder01.setRecCreator(userId);
        tsder01.setRecCreateName(userName);
        tsder01.setRecCreateTime(curDateTime);
        tsder01.setRecModifier(userId);
        tsder01.setRecModifyName(userName);
        tsder01.setRecModifyTime(curDateTime);
        tsder01.setDeleteFlag("0");
        int backInsert =tsder01Mapper.insert(tsder01);
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
    public EiINfo deleteTsder01ByMap(Tsder01 tsder01)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder01.getMemberId())){
            throw new Exception("人员编号为空无法删除！");
        }
        UpdateWrapper<Tsder01> wrapper=new UpdateWrapper<>();
        wrapper.eq("MEMBER_ID",tsder01.getMemberId());
        Tsder01 tsder01Up=new Tsder01();

        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder01Up.setDeleteFlag("1");
        tsder01Up.setDeleteName(userName);
        tsder01Up.setDeleter(userId);
        tsder01Up.setDeleteTime(curDateTime);
        tsder01Mapper.update(tsder01Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("删除成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo updateTsder01(Tsder01 tsder01)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder01.getMemberId())){
            throw new Exception("人员编号为空无法修改！");
        }
        UpdateWrapper<Tsder01> wrapper=new UpdateWrapper<>();
        wrapper.eq("MEMBER_ID",tsder01.getMemberId());
        Tsder01 tsder01Up=new Tsder01();
        tsder01Up.setMemberName(tsder01.getMemberName());
        tsder01Up.setSex(tsder01.getSex());
        tsder01Up.setTel(tsder01.getTel());
        tsder01Up.setNational(tsder01.getNational());
        tsder01Up.setPolitStatus(tsder01.getPolitStatus());
        tsder01Up.setEmail(tsder01.getEmail());
        tsder01Up.setNatPlace(tsder01.getNatPlace());
        tsder01Up.setThrAddress(tsder01.getThrAddress());
        tsder01Up.setResAddress(tsder01.getResAddress());
        tsder01Up.setIdCard(tsder01.getIdCard());
        tsder01Up.setBirthDate(tsder01.getBirthDate());
        tsder01Up.setAge(tsder01.getAge());
        tsder01Up.setHxCardId(tsder01.getHxCardId());
        tsder01Up.setGsCardId(tsder01.getGsCardId());
        tsder01Up.setBloodType(tsder01.getBloodType());
        tsder01Up.setMarryStatus(tsder01.getMarryStatus());
        tsder01Up.setHigEdu(tsder01.getHigEdu());
        tsder01Up.setUniversityName(tsder01.getUniversityName());
        tsder01Up.setGraDate(tsder01.getGraDate());
        tsder01Up.setProfession(tsder01.getProfession());
        tsder01Up.setHigDegree(tsder01.getHigDegree());
        tsder01Up.setEmpDate(tsder01.getEmpDate());
        tsder01Up.setIndYear(tsder01.getIndYear());
        tsder01Up.setContractNo(tsder01.getContractNo());
        tsder01Up.setSignDate(tsder01.getSignDate());
        tsder01Up.setEndDate(tsder01.getEndDate());
        tsder01Up.setEndDateNew(tsder01.getEndDateNew());
        tsder01Up.setIsRemindC(tsder01.getIsRemindC());
        tsder01Up.setProbStartDate(tsder01.getProbStartDate());
        tsder01Up.setProbEndDate(tsder01.getProbEndDate());
        tsder01Up.setIsRemindP(tsder01.getIsRemindP());
        tsder01Up.setFormalDare(tsder01.getFormalDare());
        tsder01Up.setDeptName(tsder01.getDeptName());
        tsder01Up.setJobs(tsder01.getJobs());
        tsder01Up.setSecInf(tsder01.getSecInf());
        tsder01Up.setLocalInf(tsder01.getLocalInf());
        tsder01Up.setEmeContact(tsder01.getEmeContact());
        tsder01Up.setEmeRel(tsder01.getEmeRel());
        tsder01Up.setEmeTel(tsder01.getEmeTel());
        tsder01Up.setRemark(tsder01.getRemark());

        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        //String userName = (String) request.getSession().getAttribute("userName");
        //String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder01Up.setRecModifyName(userName);
        tsder01Up.setRecModifier(userId);
        tsder01Up.setRecModifyTime(curDateTime);
        tsder01Mapper.update(tsder01Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }

    @Override
    public EiINfo saveTsder01sByImp(List<Tsder01Upload> er01UploadS) throws Exception {
        EiINfo eiINfo=new EiINfo();
        for (Tsder01Upload er01Upload:er01UploadS){
            Tsder01 tsder01 = new Tsder01();
            BeanUtils.copyProperties(er01Upload,tsder01);
            this.saveTsder01(tsder01);
        }
        eiINfo.setSuccess("1");
        eiINfo.setMessage("导入信息成功！");
        return eiINfo;
    }
}
