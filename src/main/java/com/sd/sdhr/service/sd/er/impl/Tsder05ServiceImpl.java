package com.sd.sdhr.service.sd.er.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.er.Tsder05Mapper;
import com.sd.sdhr.pojo.sd.er.Tsder05;
import com.sd.sdhr.pojo.sd.er.common.Tsder05Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.er.Tsder05Service;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Tsder05ServiceImpl implements Tsder05Service {

    @Autowired
    private Tsder05Mapper tsder05Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request


    @Override
    public EiINfo getAllTsder05(Tsder05Request tsder05) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsder05> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsder05.getMemberName()),"MEMBER_NAME",tsder05.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsder05.getDeptName()),"DEPT_NAME",tsder05.getDeptName());
            queryWrapper.like(!StringUtils.isEmpty(tsder05.getJobs()),"JOBS",tsder05.getJobs());

            PageHelper.startPage(tsder05.getPageNum(),tsder05.getPageSize());
            List<Tsder05> list=tsder05Mapper.selectList(queryWrapper);
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
    public Tsder05 selectTsder05ById(Tsder05 tsder05) {
        return null;
    }

    @Override
    @Transactional
    public EiINfo saveTsder05(Tsder05 tsder05)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder05.getMemberId())){
            throw new Exception("人员编号为空无法新增！人员编号："+tsder05.getMemberId());
        }

        //查询当前人员编号是否已经生成访谈主信息
        QueryWrapper<Tsder05> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("MEMBER_ID",tsder05.getMemberId());
        int backNum=tsder05Mapper.selectCount(queryWrapper);
        if (backNum!=0){
            throw new Exception("人员编号已经维护有离职信息 无法再新增，人员编号："+tsder05.getMemberId());
        }
        // 注入信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder05.setRecCreator(userId);
        tsder05.setRecCreateName(userName);
        tsder05.setRecCreateTime(curDateTime);
        tsder05.setRecModifier(userId);
        tsder05.setRecModifyName(userName);
        tsder05.setRecModifyTime(curDateTime);
        tsder05.setDeleteFlag("0");
        int backInsert =tsder05Mapper.insert(tsder05);
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
    public EiINfo deleteTsder05ByMap(Tsder05 tsder05)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder05.getMemberId())){
            throw new Exception("人员编号为空无法删除！");
        }
        tsder05Mapper.deleteById(tsder05.getMemberId());

        eiINfo.setSuccess("1");
        eiINfo.setMessage("删除成功！");
        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo updateTsder05(Tsder05 tsder05)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder05.getMemberId())){
            throw new Exception("人员编号为空无法修改！");
        }
        UpdateWrapper<Tsder05> wrapper=new UpdateWrapper<>();
        wrapper.eq("MEMBER_ID",tsder05.getMemberId());
        Tsder05 tsder05Up=new Tsder05();
        tsder05Up.setCompany(tsder05.getCompany());
        tsder05Up.setDeptName(tsder05.getDeptName());
        tsder05Up.setJobs(tsder05.getJobs());
        tsder05Up.setEmpDate(tsder05.getEmpDate());
        tsder05Up.setDepDate(tsder05.getDepDate());
        tsder05Up.setDepReason(tsder05.getDepReason());
        tsder05Up.setApplyDate(tsder05.getApplyDate());
        tsder05Up.setApprover(tsder05.getApprover());
        tsder05Up.setApproveDate(tsder05.getApproveDate());
        tsder05Up.setApproveRemark(tsder05.getApproveRemark());
        tsder05Up.setRemark(tsder05.getRemark());

        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder05Up.setRecModifyName(userName);
        tsder05Up.setRecModifier(userId);
        tsder05Up.setRecModifyTime(curDateTime);
        tsder05Mapper.update(tsder05Up,wrapper);

        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }
}
