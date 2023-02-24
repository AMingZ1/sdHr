package com.sd.sdhr.service.sd.er.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdhr.mapper.sd.er.Tsder03Mapper;
import com.sd.sdhr.mapper.sd.er.Tsder04DefinedMapper;
import com.sd.sdhr.mapper.sd.er.Tsder04Mapper;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.er.common.Tsder04Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.service.sd.er.Tsder04Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class  Tsder04ServiceImpl implements Tsder04Service {

    @Autowired
    private Tsder04Mapper tsder04Mapper;

    @Autowired
    Tsder03Mapper tsder03Mapper;

    @Autowired
    Tsder04DefinedMapper tsder04DefinedMapper;

    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    @Override
    public EiINfo getAllTsder04(Tsder04Request tsder04) {
        EiINfo eiINfo=new EiINfo();
        try {
            eiINfo.setPageNum(eiINfo.getPageNum()+1);
            PageHelper.startPage(eiINfo);
            QueryWrapper<Tsder04> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("Delete_Flag","1");//删除标记不为1
            //模糊查询条件
            queryWrapper.like(!StringUtils.isEmpty(tsder04.getMemberId()),"MEMBER_ID",tsder04.getMemberId());
            queryWrapper.like(!StringUtils.isEmpty(tsder04.getTalkNo()),"TALK_NO",tsder04.getTalkNo());
            queryWrapper.like(!StringUtils.isEmpty(tsder04.getTalkType()),"TALK_TYPE",tsder04.getTalkType());

            List<Tsder04> list=tsder04Mapper.selectList(queryWrapper);
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
    public Tsder04 selectTsder04ById(Tsder04 tsder04) {
        return null;
    }

    @Override
    @Transactional
    public EiINfo saveTsder04(Tsder04 tsder04)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder04.getMemberId())){
            throw new Exception("人员编号为空无法新增！人员编号："+tsder04.getMemberId());
        }
        if (StringUtils.isEmpty(tsder04.getTalkType())){
            throw new Exception("访谈类型 为空无法新增！人员编号："+tsder04.getMemberId());
        }
        //同步调整er03表的状态
        Tsder03 tsder03= tsder03Mapper.selectById(tsder04.getMemberId());
        String talkPlanNow=tsder03.getTalkPlanNow();
        Tsder03 tsder03Up =new Tsder03();
        tsder03Up.setMemberId(tsder03.getMemberId());
        if ("T01".equals(tsder04.getTalkType())&&!"00".equals(talkPlanNow)){//周访谈
            tsder03Up.setTalkPlanNow("01");
            tsder03Up.setTalkStatus("10");
        }else if ("T02".equals(tsder04.getTalkType())&& !"01".equals(talkPlanNow)){
            tsder03Up.setTalkPlanNow("02");
            tsder03Up.setTalkStatus("10");
        }else if("T03".equals(tsder04.getTalkType())&& !"02".equals(talkPlanNow)){
            tsder03Up.setTalkPlanNow("03");
            tsder03Up.setMemberType("1");//人员类型转正
            tsder03Up.setTalkStatus("10");
        }else if("T04".equals(tsder04.getTalkType())&& !"03/04".contains(talkPlanNow)){
            tsder03Up.setTalkPlanNow("04");
            tsder03Up.setTalkStatus("20");

        }else if("T05".equals(tsder04.getTalkType())&& !"03/04".contains(talkPlanNow)){
            tsder03Up.setTalkStatus("20");
            tsder03Up.setTalkPlanNow("04");

        }else {
            throw new Exception("新增访谈明细失败，请按照先周》月》转正的访谈顺序依次维护！人员编号："+tsder04.getMemberId());
        }
        tsder03Mapper.updateById(tsder03Up);

        //拿到当前年月日；
        Calendar calendar = Calendar.getInstance();
        // 获取当前年
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        // 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;
        StringBuilder talkNo=new StringBuilder();
        String an=year.substring(year.length()-2);
        talkNo=talkNo.append(an).append("G").append(month);
        //查询当前生成流水号信息
        int backNum=tsder04Mapper.queryCountByTalkNoLike(tsder04.getMemberId(),tsder04.getTalkType(),talkNo.toString());
        String serialNum= String.format("%04d", backNum+1);
        talkNo.append(serialNum);
        tsder04.setTalkNo(talkNo.toString());
        tsder04.setTalkStatus("00".equals(tsder03.getTalkStatus())?"00":"20");//00 逾期
        // 注入信息
        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder04.setRecCreator(userId);
        tsder04.setRecCreateName(userName);
        tsder04.setRecCreateTime(curDateTime);
        tsder04.setRecModifier(userId);
        tsder04.setRecModifyName(userName);
        tsder04.setRecModifyTime(curDateTime);
        tsder04.setDeleteFlag("0");
        int backInsert =tsder04Mapper.insert(tsder04);
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
    public EiINfo deleteTsder04ByMap(Tsder04 tsder04)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder04.getMemberId())){
            throw new Exception("人员编号为空无法删除！");
        }
        if (StringUtils.isEmpty(tsder04.getTalkNo())){
            throw new Exception("访谈计划化号为空无法删除！");
        }
        if (StringUtils.isEmpty(tsder04.getTalkType())){
            throw new Exception("访谈类型为空无法删除！");
        }
        UpdateWrapper<Tsder04> wrapper=new UpdateWrapper<>();
        wrapper.eq("MEMBER_ID",tsder04.getMemberId());
        wrapper.eq("TALK_NO",tsder04.getTalkNo());
        wrapper.eq("TALK_TYPE",tsder04.getTalkType());
        tsder04Mapper.delete(wrapper);

        eiINfo.setSuccess("1");
        eiINfo.setMessage("删除成功！");

        return eiINfo;
    }

    @Override
    public EiINfo updateTsder04(Tsder04 tsder04)throws Exception {
        EiINfo eiINfo=new EiINfo();
        if (StringUtils.isEmpty(tsder04.getMemberId())){
            throw new Exception("人员编号为空无法修改！");
        }
        if (StringUtils.isEmpty(tsder04.getTalkNo())){
            throw new Exception("访谈计划化号为空无法修改！");
        }
        if (StringUtils.isEmpty(tsder04.getTalkType())){
            throw new Exception("访谈类型为空无法修改！");
        }
        UpdateWrapper<Tsder04> wrapper=new UpdateWrapper<>();
        wrapper.eq("MEMBER_ID",tsder04.getMemberId());
        wrapper.eq("TALK_NO",tsder04.getTalkNo());
        wrapper.eq("TALK_TYPE",tsder04.getTalkType());
        Tsder04 tsder04Up=new Tsder04();
        tsder04Up.setTalkDate(tsder04.getTalkDate());
        tsder04Up.setTalkContent1(tsder04.getTalkContent1());
        tsder04Up.setTalkContent2(tsder04.getTalkContent2());
        tsder04Up.setTalkContent3(tsder04.getTalkContent3());
        tsder04Up.setTalkContent4(tsder04.getTalkContent4());
        tsder04Up.setTalkContent5(tsder04.getTalkContent5());
        tsder04Up.setTalkContent6(tsder04.getTalkContent6());
        tsder04Up.setTalkContent7(tsder04.getTalkContent7());
        tsder04Up.setTalkContent8(tsder04.getTalkContent8());
        tsder04Up.setTalkContent9(tsder04.getTalkContent9());
        tsder04Up.setTalkContent10(tsder04.getTalkContent10());
        tsder04Up.setTalkContent11(tsder04.getTalkContent11());
        tsder04Up.setTalkContent12(tsder04.getTalkContent12());
        tsder04Up.setTalkContent13(tsder04.getTalkContent13());
        tsder04Up.setTalkContent14(tsder04.getTalkContent14());
        tsder04Up.setTalkContent15(tsder04.getTalkContent15());
        tsder04Up.setDailyNum(tsder04.getDailyNum());
        tsder04Up.setDailyNumFin(tsder04.getDailyNumFin());
        tsder04Up.setPmNameNow(tsder04.getPmNameNow());
        tsder04Up.setPmFeedback(tsder04.getPmFeedback());
        tsder04Up.setPmScore(tsder04.getPmScore());
        tsder04Up.setIsFormal(tsder04.getIsFormal());
        tsder04Up.setRemark(tsder04.getRemark());

        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsder04Up.setRecModifyName(userName);
        tsder04Up.setRecModifier(userId);
        tsder04Up.setRecModifyTime(curDateTime);
        tsder04Mapper.update(tsder04Up,wrapper);

        eiINfo.setSuccess("1");
        eiINfo.setMessage("修改成功！");

        return eiINfo;
    }

    @Override
    public EiINfo getEnMonthStatByTalk(Tsder04 tsder04) {
        EiINfo eiINfo=new EiINfo();
        try {
            //获取当前年份
            Calendar calendar = Calendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            List<Map> maps=tsder04DefinedMapper.getTsder04ForOffica(year);
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
}
