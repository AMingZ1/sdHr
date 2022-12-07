package com.sd.sdhr;



import com.sd.sdhr.service.common.EmailSendUtil;
import com.sd.sdhr.service.sd.hr.impl.Tsdhr03ServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.SQLException;

//单元测试
@SpringBootTest
class SdHrApplicationTests {



    @Test
    void contextLoads() {

        System.out.print("");
        //System.out.println(dog);
      //  System.out.println(person);
    }


    /*@Test
    void contextLoadExper() throws Exception {
        //emailSendUtil.sendSimpleHtmlMail("lkn301415@163.com","这是一个测试邮件下好大的哈");
        emailSendUtil.resceiveMail("lkn301415@163.com","12313");
    }*/

}
