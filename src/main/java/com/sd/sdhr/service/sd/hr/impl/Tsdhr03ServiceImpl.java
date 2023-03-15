package com.sd.sdhr.service.sd.hr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.hr.Tsdhr03Mapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr03;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr03Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr03Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.hr.Tsdhr03Service;
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
public class Tsdhr03ServiceImpl implements Tsdhr03Service {

    @Autowired
    private Tsdhr03Mapper tsdhr03Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsdhr03(Tsdhr03Request tsdhr03) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsdhr03> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getChannel()),"CHANNEL", tsdhr03.getChannel());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getMemberName()),"MEMBER_NAME", tsdhr03.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getDeptName()),"DEPT_NAME", tsdhr03.getDeptName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getItvJob()),"ITV_JOB", tsdhr03.getItvJob());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr03.getArchiveReason()),"ARCHIVE_REASON", tsdhr03.getArchiveReason());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr03.getArchiveStatusbfr()),"ARCHIVE_STATUSBFR", tsdhr03.getArchiveStatusbfr());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr03.getEducationBckr()),"EDUCATION_BCKR", tsdhr03.getEducationBckr());

            PageHelper.startPage(tsdhr03.getPageNum(),tsdhr03.getPageSize());
            List<Tsdhr03> list= tsdhr03Mapper.selectList(queryWrapper);
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
    public List<Tsdhr03> queryTsdhr03s(Tsdhr03Request tsdhr03) {
        QueryWrapper<Tsdhr03> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("Delete_Flag","1");//删除标记不为1
        //模糊查询条件
        queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getChannel()),"CHANNEL", tsdhr03.getChannel());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getMemberName()),"MEMBER_NAME", tsdhr03.getMemberName());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getDeptName()),"DEPT_NAME", tsdhr03.getDeptName());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr03.getItvJob()),"ITV_JOB", tsdhr03.getItvJob());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr03.getArchiveReason()),"ARCHIVE_REASON", tsdhr03.getArchiveReason());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr03.getArchiveStatusbfr()),"ARCHIVE_STATUSBFR", tsdhr03.getArchiveStatusbfr());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr03.getEducationBckr()),"EDUCATION_BCKR", tsdhr03.getEducationBckr());
        List<Tsdhr03> list= tsdhr03Mapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Tsdhr03 selectTsdhr03ById(Tsdhr03 tsdhr03) {
        return null;
    }

    @Override
    public EiINfo saveTsdhr03sByImp(List<Tsdhr03Upload> hr03Uploads)throws Exception {
        EiINfo eiINfo=new EiINfo();
        Tsdhr03 tsdhr03 = new Tsdhr03();
        for (Tsdhr03Upload hr03Upload:hr03Uploads){
            tsdhr03.setMemberName(hr03Upload.getMemberName());
            tsdhr03.setDeptName(hr03Upload.getDeptName());
            tsdhr03.setItvJob(hr03Upload.getItvJob());
            tsdhr03.setTel(hr03Upload.getTel());
            tsdhr03.setEmail(hr03Upload.getEmail());
            tsdhr03.setChannel(hr03Upload.getChannel());
            tsdhr03.setArchiveReason(hr03Upload.getArchiveReason());
            tsdhr03.setArchiveStatusbfr(hr03Upload.getArchiveStatusbfr());
            tsdhr03.setWorkYear(hr03Upload.getWorkYear());
            tsdhr03.setEducationBckr(hr03Upload.getEducationBckr());
            tsdhr03.setRemark(hr03Upload.getRemark());
            this.saveTsdhr03(tsdhr03);
        }
        eiINfo.setSuccess("1");
        eiINfo.setMessage("导入信息成功！");
        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo saveTsdhr03(Tsdhr03 tsdhr03)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (!StringUtils.isEmpty(tsdhr03.getMemberNo())){
            throw new Exception("员工编号不为空无法新增！面试记录号："+ tsdhr03.getMemberNo());
        }
        //拿到当前年月日；
        Calendar calendar = Calendar.getInstance();
        // 获取当前年
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        // 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;
        StringBuilder memberNo=new StringBuilder("DSSH");
        //查询当前生成流水号信息
        int backNum= tsdhr03Mapper.queryCountByMemberNoLike(memberNo.toString());
        String serialNum= String.format("%04d", backNum+1);
        memberNo.append(serialNum);
        tsdhr03.setMemberNo(memberNo.toString());
        // 注入信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr03.setRecCreator(userId);
        tsdhr03.setRecCreateName(userName);
        tsdhr03.setRecCreateTime(curDateTime);
        tsdhr03.setRecModifier(userId);
        tsdhr03.setRecModifyName(userName);
        tsdhr03.setRecModifyTime(curDateTime);
        tsdhr03.setDeleteFlag("0");
        int backInsert = tsdhr03Mapper.insert(tsdhr03);
        eiINfo.setMessage(String.valueOf(backInsert));
        if (backInsert==1){
            eiINfo.setSuccess("1");
            eiINfo.setMessage("新增成功！");
        }else {
            throw new Exception("Inser hr03失败！");
        }
        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo deleteTsdhr03ByMap(Tsdhr03 tsdhr03)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsdhr03.getMemberNo())){
            throw new Exception("人员编号为空无法删除！");
        }
        UpdateWrapper<Tsdhr03> wrapper=new UpdateWrapper<>();
        wrapper.eq("MEMBER_NO", tsdhr03.getMemberNo());
        Tsdhr03 tsdhr03Up =new Tsdhr03();
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr03Up.setDeleteFlag("1");
        tsdhr03Up.setDeleteName(userName);
        tsdhr03Up.setDeleter(userId);
        tsdhr03Up.setDeleteTime(curDateTime);
        tsdhr03Mapper.update(tsdhr03Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("删除成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo updateTsdhr03(Tsdhr03 tsdhr03)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsdhr03.getMemberNo())){
            throw new Exception("人员编号为空无法修改！");
        }
        UpdateWrapper<Tsdhr03> wrapper=new UpdateWrapper<>();
        wrapper.eq("MEMBER_NO", tsdhr03.getMemberNo());
        Tsdhr03 tsdhr03Up =new Tsdhr03();
        tsdhr03Up.setMemberName(tsdhr03.getMemberName());
        tsdhr03Up.setTel(tsdhr03.getTel());
        tsdhr03Up.setEmail(tsdhr03.getEmail());
        tsdhr03Up.setUniversityName(tsdhr03.getUniversityName());
        tsdhr03Up.setEducationBckr(tsdhr03.getEducationBckr());
        tsdhr03Up.setProfession(tsdhr03.getProfession());
        tsdhr03Up.setSumScore(tsdhr03.getSumScore());
        tsdhr03Up.setEvaluation(tsdhr03.getEvaluation());
        tsdhr03Up.setItvJob(tsdhr03.getItvJob());
        tsdhr03Up.setDeptName(tsdhr03.getDeptName());
        tsdhr03Up.setChannel(tsdhr03.getChannel());
        tsdhr03Up.setWorkYear(tsdhr03.getWorkYear());
        tsdhr03Up.setRemark(tsdhr03.getRemark());

        //
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdhr03Up.setRecModifyName(userName);
        tsdhr03Up.setRecModifier(userId);
        tsdhr03Up.setRecModifyTime(curDateTime);
        tsdhr03Mapper.update(tsdhr03Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }
}
