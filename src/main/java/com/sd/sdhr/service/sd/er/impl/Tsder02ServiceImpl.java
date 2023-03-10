package com.sd.sdhr.service.sd.er.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.er.Tsder02Mapper;
import com.sd.sdhr.pojo.sd.er.Tsder02;
import com.sd.sdhr.pojo.sd.er.common.Tsder02Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.er.Tsder02Service;
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
public class Tsder02ServiceImpl implements Tsder02Service {

    @Autowired
    private Tsder02Mapper tsder02Mapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsder02(Tsder02Request tsder02) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsder02> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.eq(!StringUtils.isEmpty(tsder02.getMemberId()),"MEMBER_ID",tsder02.getMemberId());
            queryWrapper.like(!StringUtils.isEmpty(tsder02.getMemberName()),"MEMBER_NAME",tsder02.getMemberName());
            queryWrapper.like(!StringUtils.isEmpty(tsder02.getDeptName()),"DEPT_NAME",tsder02.getDeptName());
            queryWrapper.like(!StringUtils.isEmpty(tsder02.getProjectName()),"PROJECT_NAME",tsder02.getProjectName());

            PageHelper.startPage(tsder02.getPageNum(),tsder02.getPageSize());
            List<Tsder02> list=tsder02Mapper.selectList(queryWrapper);
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
    public Tsder02 selectTsder02ById(Tsder02 tsder02) {
        return null;
    }

    @Override
    @Transactional
    public EiINfo saveTsder02(Tsder02 tsder02)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (!StringUtils.isEmpty(tsder02.getMpRelationNo())){
            throw new Exception("关系编号不为空无法新增！面试记录号："+tsder02.getMpRelationNo());
        }
        //拿到当前年月日；
        Calendar calendar = Calendar.getInstance();
        // 获取当前年
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        // 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;
        StringBuilder mpRelationNo=new StringBuilder();
        String an=year.substring(year.length()-2);
        mpRelationNo=mpRelationNo.append(an).append("E").append(month);
        //查询当前生成流水号信息
        int backNum=tsder02Mapper.queryCountByMpRelationNoLike(mpRelationNo.toString());
        String serialNum= String.format("%04d", backNum+1);
        mpRelationNo.append(serialNum);
        tsder02.setMpRelationNo(mpRelationNo.toString());
        // 注入信息
        Claims claims = JwtUtil.verifyJwt(request);
        String userId = claims.get("userId").toString();
        String userName =  claims.get("userName").toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder02.setRecCreator(userId);
        tsder02.setRecCreateName(userName);
        tsder02.setRecCreateTime(curDateTime);
        tsder02.setRecModifier(userId);
        tsder02.setRecModifyName(userName);
        tsder02.setRecModifyTime(curDateTime);
        tsder02.setDeleteFlag("0");
        int backInsert =tsder02Mapper.insert(tsder02);
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
    public EiINfo deleteTsder02ByMap(Tsder02 tsder02)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder02.getMpRelationNo())){
            throw new Exception("关系编号为空无法删除！");
        }
        UpdateWrapper<Tsder02> wrapper=new UpdateWrapper<>();
        wrapper.eq("MP_RELATION_NO",tsder02.getMpRelationNo());
        Tsder02 tsder02Up=new Tsder02();
        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder02Up.setDeleteFlag("1");
        tsder02Up.setDeleteName(userName);
        tsder02Up.setDeleter(userId);
        tsder02Up.setDeleteTime(curDateTime);
        tsder02Mapper.update(tsder02Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("删除成功！");

        return eiINfo;
    }

    @Override
    @Transactional
    public EiINfo updateTsder02(Tsder02 tsder02)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder02.getMpRelationNo())){
            throw new Exception("关系编号为空无法修改！");
        }
        UpdateWrapper<Tsder02> wrapper=new UpdateWrapper<>();
        wrapper.eq("MP_RELATION_NO",tsder02.getMpRelationNo());
        Tsder02 tsder02Up=new Tsder02();
        tsder02Up.setDeptName(tsder02.getDeptName());
        tsder02Up.setMemberId(tsder02.getMemberId());
        tsder02Up.setMemberName(tsder02.getMemberName());
        tsder02Up.setProAddress(tsder02.getProAddress());
        tsder02Up.setProjectName(tsder02.getProjectName());
        tsder02Up.setProjectPhase(tsder02.getProjectPhase());
        tsder02Up.setResMod(tsder02.getResMod());
        tsder02Up.setUseTec(tsder02.getUseTec());
        tsder02Up.setPmName(tsder02.getPmName());
        tsder02Up.setPmEmail(tsder02.getPmEmail());
        tsder02Up.setPmTel(tsder02.getPmTel());
        tsder02Up.setRemark(tsder02.getRemark());
        tsder02Up.setDeptName(tsder02.getDeptName());

        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder02Up.setRecModifyName(userName);
        tsder02Up.setRecModifier(userId);
        tsder02Up.setRecModifyTime(curDateTime);
        tsder02Mapper.update(tsder02Up,wrapper);
        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }
}
