package com.sd.sdhr.service.sd.hr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.hr.Tsdhr01DefinedMapper;
import com.sd.sdhr.mapper.sd.hr.Tsdhr01Mapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Upload;
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
import java.util.Map;

@Service
public class Tsdhr01ServiceImpl implements Tsdhr01Service {


    @Autowired
    private Tsdhr01Mapper tsdhr01Mapper;

    @Autowired
    private Tsdhr01DefinedMapper tsdhr01DefinedMapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public List<Tsdhr01> getAllTsdhr01(Tsdhr01Request tsdhr01) {
        //模糊查询条件
        QueryWrapper<Tsdhr01> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("Delete_Flag",tsdhr01.isQueryHis()?"1":"0");//true 只查历史
        queryWrapper.like(!StringUtils.isEmpty(tsdhr01.getYear()),"YEAR",tsdhr01.getYear());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr01.getDeptName()),"DEPT_NAME",tsdhr01.getDeptName());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr01.getItvJob()),"ITV_JOB",tsdhr01.getItvJob());
        queryWrapper.ge(!StringUtils.isEmpty(tsdhr01.getStartRecCreateTime()),"LEFT(REC_CREATE_TIME,8)",tsdhr01.getStartRecCreateTime());
        queryWrapper.le(!StringUtils.isEmpty(tsdhr01.getEndRecCreateTime()),"LEFT(REC_CREATE_TIME,8)",tsdhr01.getEndRecCreateTime());

        List<Tsdhr01> list=tsdhr01Mapper.selectList(queryWrapper);
        //格式化数据
        for(int i=0;i<list.size();i++){
            if(isNotEmpty(list.get(i).getPlanEndDate())){
                list.get(i).setPlanEndDate(
                        list.get(i).getPlanEndDate().substring(0,4)+"/"+
                                list.get(i).getPlanEndDate().substring(4,6)+"/"+
                                list.get(i).getPlanEndDate().substring(6,8));
            }
        }

        return list;
    }

    public static boolean isNotEmpty(String str) {
        if(str == null ){
            return false;
        }

        if(str.trim().length()  == 0 ){
            return false;
        }

        return true;
    }

    @Override
    public List<Tsdhr01> queryExportAllTsdhr01(Tsdhr01Request tsdhr01Request) {
        return tsdhr01DefinedMapper.queryExportByHr01(tsdhr01Request);
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


            queryWrapper.ge(!StringUtils.isEmpty(tsdhr01.getStartRecCreateTime()),"LEFT(REC_CREATE_TIME,8)",tsdhr01.getStartRecCreateTime());
            queryWrapper.le(!StringUtils.isEmpty(tsdhr01.getEndRecCreateTime()),"LEFT(REC_CREATE_TIME,8)",tsdhr01.getEndRecCreateTime());


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
    public EiINfo deleteTsdhr01ByReqNos(String reqNos) throws Exception {
        EiINfo eiINfo=new EiINfo();

        UpdateWrapper<Tsdhr01> wrapper  =new UpdateWrapper<>();;

        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());

        String []   array= reqNos.split(",");
        wrapper.in("REQ_NO",array);

        Tsdhr01 tsdhr01Up =new Tsdhr01();;
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

    @Override
    @Transactional
    public EiINfo saveTsdhr01sByImp(List<Tsdhr01Upload> tsdhr01Up) throws Exception {
        EiINfo eiINfo=new EiINfo();
        Tsdhr01 tsdhr01 = new Tsdhr01();
        for(int i=0;i<tsdhr01Up.size();i++){
            Tsdhr01Upload tsdhr01Upload = tsdhr01Up.get(i);
            if (StringUtils.isEmpty(tsdhr01Upload.getYear())){
                throw new Exception(i+1+"行,年份为空! 无法导入");
            }
            String deptNameOld = tsdhr01Upload.getDeptName();
            if (StringUtils.isEmpty(deptNameOld)){
                throw new Exception(i+1+"行,部门名称为空! 无法导入");
            }
            String itvJobOld = tsdhr01Upload.getItvJob();
            if (StringUtils.isEmpty(tsdhr01Upload.getItvJob())){
                throw new Exception(i+1+"行,岗位名称为空! 无法导入");
            }
            if (StringUtils.isEmpty(tsdhr01Upload.getRequireNum().toString())){
                throw new Exception(i+1+"行,需求数量为空! 无法导入");
            }
            if (StringUtils.isEmpty(tsdhr01Upload.getJobRequire())){
                throw new Exception(i+1+"行,岗位需求为空! 无法导入");
            }
            if (StringUtils.isEmpty(tsdhr01Upload.getRequireContact())){
                throw new Exception(i+1+"行,需求联系人为空! 无法导入");
            }
            if (StringUtils.isEmpty(tsdhr01Upload.getDutyPerson())){
                throw new Exception(i+1+"行,责任人为空! 无法导入");
            }
            if (StringUtils.isEmpty(tsdhr01Upload.getPlanEndDate())){
                throw new Exception(i+1+"行,计划完成时间为空! 无法导入");
            }
            String itvWaysOld = tsdhr01Upload.getItvWays();
            if (StringUtils.isEmpty(itvWaysOld)){
                throw new Exception(i+1+"行,面试方式为空! 无法导入");
            }
            tsdhr01.setYear(tsdhr01Upload.getYear());
            //tsdhr01.setDeptName(deptNameOld.substring(0,deptNameOld.lastIndexOf("-")));
            tsdhr01.setDeptName(deptNameOld);
            //tsdhr01.setItvJob(itvJobOld.substring(0,itvJobOld.lastIndexOf("-")));
            tsdhr01.setItvJob(itvJobOld);
            tsdhr01.setRequireNum(tsdhr01Upload.getRequireNum());
            tsdhr01.setJobRequire(tsdhr01Upload.getJobRequire());
            tsdhr01.setRequireContact(tsdhr01Upload.getRequireContact());
            tsdhr01.setDutyPerson(tsdhr01Upload.getDutyPerson());
            tsdhr01.setPlanEndDate(tsdhr01Upload.getPlanEndDate());
            tsdhr01.setItvWays(itvWaysOld);
            tsdhr01.setIsEme(tsdhr01Upload.getIsEme());
            this.saveTsdhr01(tsdhr01);
        }
        eiINfo.setSuccess("1");
        eiINfo.setMessage("导入信息成功！");
        return eiINfo;
    }
}
