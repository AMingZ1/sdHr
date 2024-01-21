package com.sd.sdhr.service.sd.hr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.hr.Tsdhr01Mapper;
import com.sd.sdhr.mapper.sd.hr.Tsdhr02Mapper;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.Tsdhr02;
import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Request;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr02Upload;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.hr.Tsdhr01Service;
import com.sd.sdhr.service.sd.hr.Tsdhr02Service;
import com.sd.sdhr.service.sd.hr.Tsdhr04Service;
import io.jsonwebtoken.Claims;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
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
    private Tsdhr01Mapper tsdhr01Mapper;
    @Autowired
    private Tsdhr04Service tsdhr04Service;

    @Autowired
    private Tsdhr01Service tsdhr01Service;

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
            List<String> itvDateList= new ArrayList<String>();
            if (tsdhr02.getContactStatus()!=null ) {
                String a[]= tsdhr02.getContactStatus().split(",");
                for (int i = 0; i < a.length; i++) {
                    contactStatusList.add(a[i]);
                }
            }
            if (tsdhr02.getItvDate() != null) {
                String b[]= tsdhr02.getItvDate().split(",");
                if(!"undefined".equals(b[0])&&!"undefined".equals(b[1])){
                    queryWrapper.between(!StringUtils.isEmpty(tsdhr02.getItvDate()),"ITV_DATE",b[0],b[1]);
                }
            }
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getPlanNo()),"PLAN_NO",tsdhr02.getPlanNo());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getMemberName()),"MEMBER_NAME",tsdhr02.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getReqNo()),"REQ_NO",tsdhr02.getReqNo());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getDeptName()),"DEPT_NAME",tsdhr02.getDeptName());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getItvJob()),"ITV_JOB",tsdhr02.getItvJob());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getWorkStatus()),"WORK_STATUS",tsdhr02.getWorkStatus());
            queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getItvStatus()),"ITV_STATUS",tsdhr02.getItvStatus());
            if (contactStatusList.size()>0){
                queryWrapper.in(!StringUtils.isEmpty(tsdhr02.getContactStatus()),"CONTACT_STATUS",contactStatusList);
            }
            queryWrapper.ge(!StringUtils.isEmpty(tsdhr02.getStartRecCreateTime()),"LEFT(REC_CREATE_TIME,8)",tsdhr02.getStartRecCreateTime());
            queryWrapper.le(!StringUtils.isEmpty(tsdhr02.getEndRecCreateTime()),"LEFT(REC_CREATE_TIME,8)",tsdhr02.getEndRecCreateTime());


            PageHelper.startPage(tsdhr02.getPageNum(),tsdhr02.getPageSize());
            List<Tsdhr02> list=tsdhr02Mapper.selectList(queryWrapper);


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
    public List<Tsdhr02> queryTsdhr02s(Tsdhr02Request tsdhr02) {
        QueryWrapper<Tsdhr02> queryWrapper=new QueryWrapper<>();
        List<String> contactStatusList= new ArrayList<String>();
        List<String> itvDateList= new ArrayList<String>();
        if (tsdhr02.getContactStatus()!=null ) {
            String a[]= tsdhr02.getContactStatus().split(",");
            for (int i = 0; i < a.length; i++) {
                contactStatusList.add(a[i]);
            }
        }
        if (tsdhr02.getItvDate() != null) {
            String b[]= tsdhr02.getItvDate().split(",");
            if(!"undefined".equals(b[0])&&!"undefined".equals(b[1])){
                queryWrapper.between(!StringUtils.isEmpty(tsdhr02.getItvDate()),"ITV_DATE",b[0],b[1]);
            }
        }
        queryWrapper.ne("Delete_Flag","1");//删除标记不为1
        //模糊查询条件
        queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getPlanNo()),"PLAN_NO",tsdhr02.getPlanNo());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getMemberName()),"MEMBER_NAME",tsdhr02.getMemberName());
        queryWrapper.like(!StringUtils.isEmpty(tsdhr02.getReqNo()),"REQ_NO",tsdhr02.getReqNo());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getDeptName()),"DEPT_NAME",tsdhr02.getDeptName());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getItvJob()),"ITV_JOB",tsdhr02.getItvJob());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getWorkStatus()),"WORK_STATUS",tsdhr02.getWorkStatus());
        queryWrapper.eq(!StringUtils.isEmpty(tsdhr02.getItvStatus()),"ITV_STATUS",tsdhr02.getItvStatus());
        if (contactStatusList.size()>0){
            queryWrapper.in(!StringUtils.isEmpty(tsdhr02.getContactStatus()),"CONTACT_STATUS",contactStatusList);
        }
        queryWrapper.ge(!StringUtils.isEmpty(tsdhr02.getStartRecCreateTime()),"LEFT(REC_CREATE_TIME,8)",tsdhr02.getStartRecCreateTime());
        queryWrapper.le(!StringUtils.isEmpty(tsdhr02.getEndRecCreateTime()),"LEFT(REC_CREATE_TIME,8)",tsdhr02.getEndRecCreateTime());

        List<Tsdhr02> list=tsdhr02Mapper.selectList(queryWrapper);

        //格式化数据
        for(int i=0;i<list.size();i++){
            //联系时间
            if(isNotEmpty(list.get(i).getContactDate())){
                list.get(i).setContactDate(
                        list.get(i).getContactDate().substring(0,4)+"/"+
                                list.get(i).getContactDate().substring(4,6)+"/"+
                                list.get(i).getContactDate().substring(6,8));
            }
            //面试时间
            if(isNotEmpty(list.get(i).getItvDate())){
                list.get(i).setItvDate(
                        list.get(i).getItvDate().substring(0,4)+"/"+
                                list.get(i).getItvDate().substring(4,6)+"/"+
                                list.get(i).getItvDate().substring(6,8));
            }
            //预计到岗时间
            if(isNotEmpty(list.get(i).getArrivalDate())){
                list.get(i).setArrivalDate(
                        list.get(i).getArrivalDate().substring(0,4)+"/"+
                                list.get(i).getArrivalDate().substring(4,6)+"/"+
                                list.get(i).getArrivalDate().substring(6,8));
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
    public Tsdhr02 selectTsdhr02ById(Tsdhr02 tsdhr02) {
        return tsdhr02Mapper.selectById(tsdhr02.getPlanNo());
    }

    @Override
    @Transactional
    public EiINfo saveTsdhr02(Tsdhr02 tsdhr02)throws Exception {
        EiINfo eiINfo=new EiINfo();
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
        //部门，岗位
        Tsdhr01 tsdhr01 = tsdhr01Service.queryTsdhr01ByReqNo(tsdhr02.getReqNo());
        if(tsdhr01 != null){
            tsdhr02.setDeptName(tsdhr01.getDeptName());
            tsdhr02.setItvJob(tsdhr01.getItvJob());
        }
        int backInsert =tsdhr02Mapper.insert(tsdhr02);
        eiINfo.setMessage(String.valueOf(backInsert));
        if (backInsert==1){
            eiINfo.setSuccess("1");
            eiINfo.setMessage("新增成功！");
        }else {
            throw new Exception("insert新增失败！");
        }

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo deleteTsdhr02ByMap(Tsdhr02 tsdhr02)throws Exception {
        EiINfo eiINfo=new EiINfo();
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

        return eiINfo;
    }


    @Override
    @Transactional
    public EiINfo deleteTsdhr02ByPlanNos(String planNos)throws Exception {
        EiINfo eiINfo=new EiINfo();

        UpdateWrapper<Tsdhr02> wrapper=new UpdateWrapper<>();
        String []   array= planNos.split(",");
        wrapper.in("PLAN_NO",array);


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
        return eiINfo;
    }




    @Override
    @Transactional
    public EiINfo updateTsdhr02(Tsdhr02 tsdhr02)throws Exception {
        EiINfo eiINfo=new EiINfo();
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
        if ("Y".equals(tsdhr02.getItvStatus())){
            //instTsdhr04ByHr02(tsdhr02);
        }
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

        return eiINfo;
    }

    @Override
    public EiINfo insertHr04ByHr02(Tsdhr02 tsdhr02) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public EiINfo saveTsdhr02sByImp(List<Tsdhr02Upload> hr02Uploads) throws Exception {
        EiINfo eiINfo=new EiINfo();
        Tsdhr02 tsdhr02 = new Tsdhr02();
        for (Tsdhr02Upload hr02Upload:hr02Uploads){
            //获取岗位需求信息
            Tsdhr01 tsdhr01 = tsdhr01Mapper.selectById(hr02Upload.getReqNo());
            if (tsdhr01==null){
                throw new Exception("岗位需求编号出错，没有找到对应岗位需求信息！岗位需求编号："+hr02Upload.getReqNo());
            }
            tsdhr02.setReqNo(hr02Upload.getReqNo());
            tsdhr02.setDeptName(tsdhr01.getDeptName());
            tsdhr02.setItvJob(tsdhr01.getItvJob());
            tsdhr02.setMemberName(hr02Upload.getMemberName());
            tsdhr02.setTel(hr02Upload.getTel());
            tsdhr02.setEmail(hr02Upload.getEmail());
            tsdhr02.setContactStatus(hr02Upload.getContactStatus());
            tsdhr02.setContactMember(hr02Upload.getContactMember());
            tsdhr02.setContactDate(hr02Upload.getContactDate());
            tsdhr02.setRemark(hr02Upload.getRemark());
            this.saveTsdhr02(tsdhr02);
        }
        eiINfo.setSuccess("1");
        eiINfo.setMessage("导入信息成功！");
        return eiINfo;
    }

    public void instTsdhr04ByHr02(Tsdhr02 tsdhr02)throws Exception{
        Tsdhr04 tsdhr04 = new Tsdhr04();
        tsdhr04.setItvDept(tsdhr02.getDeptName());
        tsdhr04.setItvJob(tsdhr02.getItvJob());
        tsdhr04.setItver(tsdhr02.getItver());
        tsdhr04.setMemberName(tsdhr02.getMemberName());
        tsdhr04.setTel(tsdhr02.getTel());
        tsdhr04.setEmail(tsdhr02.getEmail());
        tsdhr04.setHopeSalary(new BigDecimal(tsdhr02.getHopeSalary()));
        tsdhr04.setArrivalDate(tsdhr02.getArrivalDate());
        tsdhr04.setItvStatus("S");
        tsdhr04.setNowStatus("00");
        tsdhr04Service.saveTsdhr04(tsdhr04);

    }



    @Override
    @Transactional
    public String saveTsdhr022(Tsdhr02 tsdhr02)throws Exception {
        EiINfo eiINfo=new EiINfo();
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
        //部门，岗位
        Tsdhr01 tsdhr01 = tsdhr01Service.queryTsdhr01ByReqNo(tsdhr02.getReqNo());
        if(tsdhr01 != null){
            tsdhr02.setDeptName(tsdhr01.getDeptName());
            tsdhr02.setItvJob(tsdhr01.getItvJob());
        }

        int backInsert =tsdhr02Mapper.insert(tsdhr02);
        eiINfo.setMessage(String.valueOf(backInsert));
        if (backInsert==1){
            eiINfo.setSuccess("1");
            eiINfo.setMessage("新增成功！");
        }else {
            throw new Exception("insert新增失败！");
        }

        return planNo.toString();
    }


    @Override
    public Tsdhr02 queryTsdhr02ByPlanNo(String planNo) {
        return tsdhr02Mapper.queryTsdhr02ByPlanNo(planNo);
    }

}
