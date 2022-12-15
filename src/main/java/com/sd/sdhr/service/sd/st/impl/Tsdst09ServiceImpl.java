package com.sd.sdhr.service.sd.st.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.sd.sdhr.mapper.sd.st.Tsdst09DefinedMapper;
import com.sd.sdhr.mapper.sd.st.Tsdst09Mapper;
import com.sd.sdhr.mapper.sd.st.Tsdst11Mapper;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.st.Tsdst09;
import com.sd.sdhr.pojo.sd.st.Tsdst11;
import com.sd.sdhr.pojo.sd.st.common.Tsdst09Request;
import com.sd.sdhr.service.sd.st.Tsdst09Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Tsdst09ServiceImpl implements Tsdst09Service {

    @Autowired
    Tsdst09Mapper tsdst09Mapper;

    @Autowired
    Tsdst09DefinedMapper tsdst09DefinedMapper;

    @Autowired
    Tsdst11Mapper tsdst11Mapper;


    @Override
    public List<Tsdst09> getAllTsdst09(Tsdst09Request tsdst09) {
        List<Tsdst09> list=new ArrayList();
        try {
            QueryWrapper<Tsdst09> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq(!StringUtils.isEmpty(tsdst09.getMemberId()),"MEMBER_ID",tsdst09.getMemberId());
            queryWrapper.eq(!StringUtils.isEmpty(tsdst09.getMessageId()),"MESSAGE_ID",tsdst09.getMessageId());
            queryWrapper.eq(!StringUtils.isEmpty(tsdst09.getMessageStatus()),"MESSAGE_STATUS",tsdst09.getMessageStatus());

            PageHelper.startPage(tsdst09.getPageNum(),tsdst09.getPageSize());
            list=tsdst09Mapper.selectList(queryWrapper);


        }catch (Exception e){

        }

        return list;
    }

    //供定时任务调用
    @Override
    public int insertTsdst09ByTalk(){
        //查询周访谈是否需要创建代办 提前两天

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String currDate = formatter.format(new Date());
        Calendar calendar = Calendar.getInstance();
        String talkWeek="";
        String talkMonth="";
        String formalDare="";
        try {
            Date talkWeekDate=formatter.parse(currDate);
            calendar.setTime(talkWeekDate);
            calendar.add(Calendar.DATE,2);
            talkWeek=formatter.format(calendar.getTime());
            //月
            calendar.add(Calendar.DATE,5);//2+5
            talkMonth=formatter.format(calendar.getTime());
            //转正
            calendar.add(Calendar.DATE,7);//2+5+7
            formalDare=formatter.format(calendar.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Tsder03 tsder03=new Tsder03();
        tsder03.setMemberType("0");
        tsder03.setTalkPlanNow("00");
        tsder03.setTalkWeek(talkWeek);//"20221217"
        tsder03.setRemark("T01");// remark 字段借用，T01:一周访谈、T02：月访谈、T03：转正访谈、T04：年度访谈
        List<Tsder03> listTalkWeek=tsdst09DefinedMapper.getTsder03ForTalk(tsder03);
        this.saveTsdst09BySder03(listTalkWeek,"的周访谈时间快到了，请尽快安排！","GRP1004","T01");

        //月
        tsder03.setTalkPlanNow("01");
        tsder03.setTalkWeek("");
        tsder03.setTalkMonth(talkMonth);
        tsder03.setRemark("T02");
        List<Tsder03> listTalkMonth=tsdst09DefinedMapper.getTsder03ForTalk(tsder03);
        this.saveTsdst09BySder03(listTalkMonth,"的月访谈时间快到了，请尽快安排！","GRP1004","T02");

        //转正
        tsder03.setTalkPlanNow("02");
        tsder03.setTalkWeek("");
        tsder03.setTalkMonth("");
        tsder03.setFormalDare(formalDare);
        tsder03.setRemark("T03");
        List<Tsder03> listTalkFormal=tsdst09DefinedMapper.getTsder03ForTalk(tsder03);
        this.saveTsdst09BySder03(listTalkFormal,"的转正访谈时间快到了，请尽快安排！","GRP1004","T03");

        return 0;
    }

    public void saveTsdst09BySder03(List<Tsder03> tsder03List,String msgBody,String roleCode,String businessType){
        Tsdst09 dst09Save=new Tsdst09();
        StringBuffer messageRemark=new StringBuffer();//消息说明
        String memberId="";
        String memberName="";

        for(int i=0;i<tsder03List.size();i++){
            Tsder03 tsder03=tsder03List.get(i);
            dst09Save.setBusinessNo(tsder03.getMemberId());
            dst09Save.setBusinessType(businessType);
            //"xxx 的周访谈时间快到了，请尽快安排！"
            dst09Save.setMessageRemark(messageRemark.append(tsder03.getMemberName()).append(msgBody).toString());

            //通过roleCode获取到对应的人员清单
            QueryWrapper<Tsdst11> st11Wrapper=new QueryWrapper<>();
            st11Wrapper.eq("ROLE_CODE",roleCode);
            List<Tsdst11> st11List=tsdst11Mapper.selectList(st11Wrapper);
            st11List.forEach(st11 ->{
                dst09Save.setMemberId(st11.getMemberId());
                dst09Save.setMemberName(st11.getMemberName());
                this.saveTsdst09(dst09Save);
            });

        }


    }



    public void saveTsdst09(Tsdst09 tsdst09){

        //拿到当前年月日；
        Calendar calendar = Calendar.getInstance();
        // 获取当前年
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.format(new Date());
        tsdst09.setMessageStatus("00");
        tsdst09.setYear(year);
        tsdst09.setRecCreator("admin");
        tsdst09.setRecCreateTime(curDateTime);
        tsdst09.setRecModifier("admin");
        tsdst09.setRecModifyName("admin");
        tsdst09.setRecModifyTime(curDateTime);
        tsdst09.setMessageId(UUID.randomUUID().toString());//或者UUID.randomUUID().toString().replaceAll("-", "")
        tsdst09Mapper.insert(tsdst09);

    }



}
