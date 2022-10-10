package com.sd.sdhr;


import com.sd.sdhr.pojo.Tsdhr01;
import com.sd.sdhr.service.impl.Tsdhr01ServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//单元测试
@SpringBootTest
class SdHrApplicationTests {
    //@Autowired
    //Dog dog;
   //private Person person;

    @Autowired
    Tsdhr01ServiceImpl tsdhr01ServiceImpl;

    @Test
    void contextLoads() {
        Tsdhr01 tsdhr01=tsdhr01ServiceImpl.queryTsdhr01ByReqNo("SD22A0001");
        System.out.print(tsdhr01.toString());
        //System.out.println(dog);
      //  System.out.println(person);
    }

}
