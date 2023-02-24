package com.sd.sdhr.service.sd.of.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.constant.DsConstant;
import com.sd.sdhr.mapper.sd.of.Tsdof01DefinedMapper;
import com.sd.sdhr.mapper.sd.of.Tsdof01Mapper;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.of.Tsdof01;
import com.sd.sdhr.pojo.sd.of.common.Tsdof01Request;
import com.sd.sdhr.pojo.sd.st.Tsdst11;
import com.sd.sdhr.service.common.EmailSendUtil;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.of.Tsdof01Service;
import com.sd.sdhr.service.sd.st.Tsdst03Service;
import com.sd.sdhr.service.sd.st.Tsdst11Service;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.common.impl.identity.Authentication;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service

public class Tsdof01ServiceImpl implements Tsdof01Service {

    @Autowired
    Tsdof01Mapper tsdof01Mapper;
    @Autowired
    Tsdst03Service tsdst03Service;
    @Autowired
    Tsdof01DefinedMapper tsdof01DefinedMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    Tsdst11Service tsdst11Service;
    @Autowired
    HistoryService historyService;
    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsdof01(Tsdof01Request tsdof01Re) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsdof01> queryWrapper=new QueryWrapper<>();
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsdof01Re.getOfferNo()),"OFFER_NO", tsdof01Re.getOfferNo());
            queryWrapper.like(!StringUtils.isEmpty(tsdof01Re.getMemberName()),"MEMBER_NAME", tsdof01Re.getMemberName());
            queryWrapper.eq(!StringUtils.isEmpty(tsdof01Re.getJobs()),"JOBS", tsdof01Re.getJobs());
            queryWrapper.eq(!StringUtils.isEmpty(tsdof01Re.getDeptName()),"DEPT_NAME", tsdof01Re.getDeptName());
            queryWrapper.eq(!StringUtils.isEmpty(tsdof01Re.getIsAgree()),"IS_AGREE", tsdof01Re.getIsAgree());
            queryWrapper.eq(!StringUtils.isEmpty(tsdof01Re.getIsFreGra()),"IS_FRE_GRA", tsdof01Re.getIsFreGra());
            queryWrapper.eq(!StringUtils.isEmpty(tsdof01Re.getComStatus()),"COM_STATUS", tsdof01Re.getComStatus());
            //queryWrapper.between(!StringUtils.isEmpty(tsdof01Re.getEmpDate()),"EMP_DATE", tsdof01Re.getEmpDate().S,"")
            if (!StringUtils.isEmpty(tsdof01Re.getEmpDate())){
                String[] split = tsdof01Re.getEmpDate().split(",");
                queryWrapper.between("EMP_DATE",split[0],split[1]);
            }
            PageHelper.startPage(tsdof01Re.getPageNum(),tsdof01Re.getPageSize());
            List<Tsdof01> list= tsdof01Mapper.selectList(queryWrapper);
            if (list==null){
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
    public Tsdof01 selectTsdof01ById(Tsdof01 tsdof01) {
        return null;
    }

    @Override
    @Transactional
    public EiINfo saveTsdof01(Tsdof01 tsdof01) throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (!StringUtils.isEmpty(tsdof01.getOfferNo())){
            throw new Exception("offer编号不为空无法新增！面试记录号："+ tsdof01.getOfferNo());
        }
        //拿到当前年月日；
        Calendar calendar = Calendar.getInstance();
        // 获取当前年
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        // 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;
        StringBuilder offerNo=new StringBuilder();
        String an=year.substring(year.length()-2);
        offerNo=offerNo.append("OF").append(an).append(String.format("%02d", month));
        //查询当前生成流水号信息
        int backNum= tsdof01Mapper.queryCountByOfferNoLike(offerNo.toString());
        String serialNum= String.format("%04d", backNum+1);
        offerNo.append(serialNum);
        tsdof01.setOfferNo(offerNo.toString());
        tsdof01.setIsAgree("00");
        tsdof01.setIsFreGra("1");
        tsdof01.setComStatus("0");
        tsdof01.setIsDz("0.8");
        tsdof01.setOfferStatus("00");
        tsdof01.setApproveStatus("00");
        // 注入信息
        //Claims claims = JwtUtil.verifyJwt(request);
        String userId = "admin";//claims.get("userId").toString();
        String userName = "admin";// claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdof01.setRecCreator(userId);
        tsdof01.setRecCreateName(userName);
        tsdof01.setRecCreateTime(curDateTime);
        tsdof01.setRecModifier(userId);
        tsdof01.setRecModifyName(userName);
        tsdof01.setRecModifyTime(curDateTime);
        tsdof01.setDeleteFlag("0");
        int backInsert = tsdof01Mapper.insert(tsdof01);
        eiINfo.setMessage(String.valueOf(backInsert));
        if (backInsert==1){
            eiINfo.setSuccess("1");
            eiINfo.setMessage("新增成功！");
        }else {
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("新增失败！");
        }

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo deleteTsdof01ByMap(Tsdof01 tsdof01)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsdof01.getOfferNo())){
            throw new Exception("Offer编号为空无法删除！");
        }
        return null;
    }

    @Override
    @Transactional
    public EiINfo updateTsdof01(Tsdof01 tsdof01)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsdof01.getOfferNo())){
            throw new Exception("Offer编号为空无法修改！");
        }
        UpdateWrapper<Tsdof01> wrapper=new UpdateWrapper<>();
        wrapper.eq("OFFFER_NO", tsdof01.getOfferNo());
        Tsdof01 tsdof01Up =new Tsdof01();
        tsdof01Up.setDeptName(tsdof01.getDeptName());
        tsdof01Up.setJobs(tsdof01.getJobs());
        tsdof01Up.setEmpDate(tsdof01.getEmpDate());
        tsdof01Up.setIsFreGra(tsdof01.getIsFreGra());
        tsdof01Up.setComStatus(tsdof01.getComStatus());
        tsdof01Up.setEmail(tsdof01.getEmail());
        tsdof01Up.setTel(tsdof01.getTel());
        tsdof01Up.setJobAddress(tsdof01.getJobAddress());
        tsdof01Up.setSalary(tsdof01.getSalary());
        tsdof01Up.setIsDz(tsdof01.getIsDz());
        //
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdof01Up.setRecModifyName(userName);
        tsdof01Up.setRecModifier(userId);
        tsdof01Up.setRecModifyTime(curDateTime);
        tsdof01Mapper.update(tsdof01Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }

    @Override
    public EiINfo getEnMonthStat(Tsdof01 tsdof01) {
        EiINfo eiINfo=new EiINfo();
        try {
            //获取当前年份
            Calendar calendar = Calendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            List<Map> maps=tsdof01DefinedMapper.getTsdof01ForOffica(year);
            List<String> strList1= new ArrayList<>();
            if (maps.size()>0) {
                Map map=maps.get(0);
                strList1.add(map.get("1month").toString());
                strList1.add(map.get("2month").toString());
                strList1.add(map.get("3month").toString());
                strList1.add(map.get("4month").toString());
                strList1.add(map.get("5month").toString());
                strList1.add(map.get("6month").toString());
                strList1.add(map.get("7month").toString());
                strList1.add(map.get("8month").toString());
                strList1.add(map.get("9month").toString());
                strList1.add(map.get("10month").toString());
                strList1.add(map.get("11month").toString());
                strList1.add(map.get("12month").toString());
                //strList1 = new ArrayList<>(map.get(0).values());
            }else {
                strList1= Arrays.asList(new String[]{"0","0","0","0","0","0","0","0","0","0","0","0"});
            }
            eiINfo.setMessage("查询成功!");
            eiINfo.setData(strList1);
            eiINfo.setSuccess("1");
        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("查询失败！"+e);
        }
        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo initiateApproval(Tsdof01 tsdof01) throws Exception{
        log.info("发起审批,入参信息："+tsdof01.toString());
        EiINfo eiINfo=new EiINfo();
        //发起审批
        Tsdof01 nerOf01 =tsdof01Mapper.selectById(tsdof01.getOfferNo());
        if(!"00/03".contains(nerOf01.getApproveStatus())){
            throw new Exception("只有当前状态为【待提交/已驳回】的才可以申请off发送！");
        }
        //Claims claims = JwtUtil.verifyJwt(request);
        String userId = "admin";//claims.get("userId").toString();
        String userName = "admin";// claims.get("userName").toString();
        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        Authentication.setAuthenticatedUserId(userId);//设置流程发起人
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("SDOF0001", map);
        String processInstanceId=processInstance.getId();
        nerOf01.setWfId(processInstanceId);//保存流程实例号
        String an=processInstance.getProcessInstanceId();
        List<Task> tasks2=taskService.createTaskQuery().taskAssignee(userId).processInstanceId(processInstanceId).list();
        String taskId=tasks2.get(0).getId();// 拿到任务Id
        //通过审核
        //通过用户组获取用户组对应人员信息
        List<Tsdst11> st11List=tsdst11Service.getAllTsdst11("GRP1004");
        if (st11List.size()==0){
            throw new RuntimeException("通过角色代码GRP1004没有获取到人员信息！");
        }
        List<String> idList=new ArrayList();
        st11List.forEach(st11->{
            idList.add(st11.getMemberId());
        });
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("isFlag", "Y");
        map3.put("userIdList", idList);// 下级审批人
        taskService.complete(taskId, map3);
        //处理业务端数据
        this.updateTsdof01Status(nerOf01.getOfferNo(),"01",processInstanceId);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("发起审批成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo applyApprov(Tsdof01Request tsdof01Request)throws Exception {
        EiINfo eiINfo=new EiINfo();
        String procInsId=tsdof01Request.getWfId();//流程实例Id
        String offerNo=tsdof01Request.getOfferNo();//offer编号
        Tsdof01 nerOf01 =tsdof01Mapper.selectById(offerNo);
        if(!"01".equals(nerOf01.getApproveStatus())){
            throw new Exception("审批状态不对！只有当前状态为【审批中】的才可以进行审批操作！");
        }
        //Claims claims = JwtUtil.verifyJwt(request);
        String userId ="admin";// claims.get("userId").toString();
        String userName ="admin";//  claims.get("userName").toString();
        List<Task> tasks2=taskService.createTaskQuery().taskAssignee(userId).processInstanceId(procInsId).list();
        String taskId=tasks2.get(0).getId();// 拿到任务Id
        HashMap<String, Object> parm = new HashMap<>();
        parm.put("isFlag", "Y");
        parm.put("userId", userId);
        taskService.complete(taskId, parm);
        //调整hr04表状态（业务处理）
        this.updateTsdof01Status(offerNo,"02","");
        //发送off邮件

        //报道时间
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
        Date parse = formatDate.parse(nerOf01.getEmpDate());
        String arrivalDate=String.format("%tY年%tm月%td日", parse,parse,parse);
        String job=tsdst03Service.selectTsdst03ById(DsConstant.SDHR_JobName,nerOf01.getJobs()).getCodeCname();
        String deptName=tsdst03Service.selectTsdst03ById(DsConstant.SDHR_JobName,nerOf01.getDeptName()).getCodeCname();//部门
        BigDecimal salary = nerOf01.getSalary();//薪资
        String isDz=tsdst03Service.selectTsdst03ById(DsConstant.SDHR_IsDz,nerOf01.getIsDz()).getCodeCname();
        BigDecimal probationPay = salary.multiply(new BigDecimal(nerOf01.getIsDz()));//试用期薪资

        Map<String,String> emailMap=new HashMap();
        emailMap.put("email",nerOf01.getEmail());//邮箱
        emailMap.put("userName",nerOf01.getMemberName());//收件人
        emailMap.put("arrivalDate",arrivalDate);//报到日期
        emailMap.put("job",job);//岗位
        emailMap.put("deptName",deptName);//部门
        emailMap.put("jobAddress",nerOf01.getJobAddress());//工作地址
        emailMap.put("salary",salary.toString());//薪资
        emailMap.put("probationPay",probationPay.toString());//试用期薪资
        emailMap.put("isDz",isDz);//试用期薪资标准
        EmailSendUtil.sendSimpleHtmlMail(emailMap);

        eiINfo.setSuccess("1");
        eiINfo.setMessage("审批成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo applyReject(Tsdof01Request tsdof01Request)throws Exception {
        EiINfo eiINfo=new EiINfo();
        String procInsId=tsdof01Request.getWfId();//流程实例Id
        String offerNo=tsdof01Request.getOfferNo();//offer编号
        Tsdof01 nerOf01 =tsdof01Mapper.selectById(offerNo);
        if(!"01".equals(nerOf01.getApproveStatus())){
            throw new Exception("审批状态不对！只有当前状态为【审批中】的才可以进行审批操作！");
        }
        //Claims claims = JwtUtil.verifyJwt(request);
        String userId ="admin";// claims.get("userId").toString();
        String userName ="admin";//  claims.get("userName").toString();
        HistoricProcessInstance hi = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(procInsId)
                .singleResult();
        String startUserId = hi.getStartUserId();//获取流程发起人
        List<Task> tasks2=taskService.createTaskQuery().taskAssignee(userId).processInstanceId(procInsId).list();
        String taskId=tasks2.get(0).getId();// 拿到任务Id

        HashMap<String, Object> parm = new HashMap<>();
        parm.put("isFlag", "N");//驳回
        parm.put("userId", startUserId);
        taskService.complete(taskId, parm);
        //调整hr04表状态（业务处理）
        this.updateTsdof01Status(offerNo,"03","");
        eiINfo.setSuccess("1");
        eiINfo.setMessage("审批成功！");

        return eiINfo;
    }

    public void updateTsdof01Status(String offerNo,String NowStatus,String processInstance) {
        UpdateWrapper<Tsdof01> wrapper=new UpdateWrapper<>();
        wrapper.eq("OFFER_NO", offerNo);
        Tsdof01 tsdof01Up =new Tsdof01();
        tsdof01Up.setApproveStatus(NowStatus);
        if (!StringUtils.isEmpty(processInstance)){
            tsdof01Up.setWfId(processInstance);
        }
        if ("02".equals(NowStatus)){//审批通过
            tsdof01Up.setIsAgree("10");//邮件已发送
        }
        //Claims claims = JwtUtil.verifyJwt(request);
        String userId ="admin";// claims.get("userId").toString();
        String userName ="admin";// claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdof01Up.setRecModifyName(userName);
        tsdof01Up.setRecModifier(userId);
        tsdof01Up.setRecModifyTime(curDateTime);
        tsdof01Mapper.update(tsdof01Up,wrapper);
    }
}
