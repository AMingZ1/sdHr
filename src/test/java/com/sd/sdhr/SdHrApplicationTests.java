package com.sd.sdhr;



import com.sd.sdhr.mapper.sd.st.Tsdst09DefinedMapper;
import com.sd.sdhr.pojo.sd.er.Tsder03;
import com.sd.sdhr.pojo.sd.er.Tsder04;
import com.sd.sdhr.pojo.sd.hr.Tsdhr01;
import com.sd.sdhr.pojo.sd.hr.Tsdhr04;
import com.sd.sdhr.pojo.sd.hr.common.Tsdhr01Request;
import com.sd.sdhr.pojo.sd.hr.respomse.EiINfo;
import com.sd.sdhr.pojo.sd.of.Tsdof01;
import com.sd.sdhr.pojo.sd.st.common.Tsdst03Request;
import com.sd.sdhr.service.common.EmailSendUtil;
import com.sd.sdhr.service.sd.er.Tsder03Service;
import com.sd.sdhr.service.sd.er.Tsder04Service;
import com.sd.sdhr.service.sd.hr.Tsdhr01Service;
import com.sd.sdhr.service.sd.hr.Tsdhr04Service;
import com.sd.sdhr.service.sd.hr.impl.Tsdhr03ServiceImpl;
import com.sd.sdhr.service.sd.of.Tsdof01Service;
import com.sd.sdhr.service.sd.st.Tsdst03Service;
import com.sd.sdhr.service.sd.st.Tsdst09Service;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//单元测试
@SpringBootTest
class SdHrApplicationTests {

    @Autowired
    Tsdhr01Service tsdhr01Service;

    @Autowired
    Tsdst09DefinedMapper tsdst09DefinedMapper;

    @Autowired
    Tsdst03Service tsdst03Service;

    @Autowired
    private Tsdhr04Service tsdhr04Service;
    @Autowired
    Tsdof01Service tsdof01Service;


    @Test
    void contextLoads() {
        Tsdhr01Request tsdhr01Res=new Tsdhr01Request();
        tsdhr01Res.setPageSize(2);
        EiINfo eiINfo=tsdhr01Service.getAllTsdhr01Test(tsdhr01Res);
        System.out.print(eiINfo.toString());
        //System.out.println(dog);
      //  System.out.println(person);
    }


    @Test
    void contextLoadExper() throws Exception {
        String timeStr="2022-12-21T16:00:00.000Z";
        if (timeStr.contains("T")) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.ENGLISH);
            LocalDateTime ldt = LocalDateTime.parse(timeStr, df);
            ZoneId currentZone = ZoneId.of("UTC");
            ZoneId newZone = ZoneId.of("Asia/Shanghai");
            timeStr = ldt.atZone(currentZone).withZoneSameInstant(newZone).toLocalDateTime().toString();
        }
        if (timeStr.length() >= 10) {
            System.out.println(timeStr.substring(0, 10));
        }

        //emailSendUtil.sendSimpleHtmlMail("lkn301415@163.com","这是一个测试邮件下好大的哈");
        //emailSendUtil.resceiveMail("lkn301415@163.com","12313");
    }


    @Test
    void queryUnfinishedTalk(){
        Tsder03 tsder03=new Tsder03();
        tsder03.setMemberType("0");
        tsder03.setTalkPlanNow("01");
        tsder03.setTalkWeek("20221217");
        tsder03.setRemark("T02");// remark 字段借用，T01:一周访谈、T02：月访谈、T03：转正访谈、T04：年度访谈
        List<Tsder03> list=tsdst09DefinedMapper.getTsder03ForTalk(tsder03);
        System.out.println(list.toString());
        /*for (int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }*/

    }

    @Test
    void saveTsdst09() {
        try {
            /*Tsdof01 tsdof01 = new Tsdof01();
            tsdof01.setOfferNo("OF2320001");
            EiINfo eiINfo = tsdof01Service.initiateApproval(tsdof01);
            System.out.println(eiINfo.toString());*/

            Tsdhr04 tsdhr04 = new Tsdhr04();
            tsdhr04.setItvNo("SD2200002");
            EiINfo eiINfo = tsdhr04Service.insertOffer(tsdhr04);
            System.out.println(eiINfo.toString());
        }catch (Exception e){
            System.out.println("访问出错！"+e.getMessage());
        }



    }


}
