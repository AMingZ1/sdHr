package com.sd.sdhr.service.sd.er.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.er.Tsder03Mapper;
import com.sd.sdhr.mapper.sd.er.Tsder04Mapper;
import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder03Service;
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
public class Tsder03ServiceImpl implements Tsder03Service {

    @Autowired
    private Tsder03Mapper tsder03Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Autowired
    private Tsder04Mapper tsder04Mapper;

    @Override
    public EiINfo getAllTsder03(Tsder03 tsder03) {
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
    public EiINfo saveTsder03(Tsder03 tsder03) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsder03.getMemberId())){
                throw new Exception("人员编号为空无法新增！面试记录号："+tsder03.getMemberId());
            }

            //查询当前人员编号是否已经生成访谈主信息
            QueryWrapper<Tsder03> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("MEMBER_ID",tsder03.getMemberId());
            int backNum=tsder03Mapper.selectCount(queryWrapper);
            if (backNum!=0){
                throw new Exception("人员编号已经维护有访谈主信息，人员编号："+tsder03.getMemberId());
            }
            // 注入信息
            String userName = (String) request.getSession().getAttribute("userName");
            String userId = (String) request.getSession().getAttribute("userId");
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
    public EiINfo updateTsder03(Tsder03 tsder03) {
        EiINfo eiINfo=new EiINfo();
        try {
            if (StringUtils.isEmpty(tsder03.getMemberId())){
                throw new Exception("人员编号为空无法修改！");
            }
            UpdateWrapper<Tsder03> wrapper=new UpdateWrapper<>();
            wrapper.eq("MEMBER_ID",tsder03.getMemberId());
            Tsder03 tsder03Up=new Tsder03();
            tsder03Up.setMemberName(tsder03.getMemberName());
            tsder03Up.setMemberType(tsder03.getMemberType());
            tsder03Up.setDeptName(tsder03.getDeptName());
            tsder03Up.setJobs(tsder03.getJobs());
            tsder03Up.setEmpDate(tsder03.getEmpDate());
            tsder03Up.setFormalDare(tsder03.getFormalDare());
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

        }catch (Exception e){
            eiINfo.setSuccess("-1");
            eiINfo.setMessage("修改失败！"+e);
        }
        return eiINfo;
    }
}
